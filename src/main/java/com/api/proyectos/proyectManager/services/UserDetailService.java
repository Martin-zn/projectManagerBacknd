package com.api.proyectos.proyectManager.services;

import com.api.proyectos.proyectManager.dto.AuthCreateUser;
import com.api.proyectos.proyectManager.dto.AuthLoginRequest;
import com.api.proyectos.proyectManager.dto.AuthResponse;
import com.api.proyectos.proyectManager.entities.LocalUser;
import com.api.proyectos.proyectManager.entities.RoleEntity;
import com.api.proyectos.proyectManager.repositories.RoleRepository;
import com.api.proyectos.proyectManager.repositories.UserRepository;
import com.api.proyectos.proyectManager.util.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {

    //Inyeccion de dependencias
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    //MEtodos ->

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LocalUser localUser = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("No se encontro el usuario: " + username));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        localUser.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(localUser.getUsername(),
                localUser.getPassword(),
                localUser.isEnabled(),
                localUser.isAccountNoExpired(),
                localUser.isCredentialNoExpired(),
                localUser.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accesToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "Usuario logeado exitosamente", accesToken, true);
        return authResponse;
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null){
            throw new BadCredentialsException("USername equivocados");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("password equivocados");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse regiterUser(AuthCreateUser authCreateUser){
        String username = authCreateUser.username();
        String name = authCreateUser.name();
        String lastname = authCreateUser.lastname();
        String email = authCreateUser.email();
        Integer number = authCreateUser.number();
        String password = authCreateUser.password();
        List<String> roleRequest = authCreateUser.roleRequest().roleListName();

        Set<RoleEntity> roleEntitySet = roleRepository.findRoleEntitiesByRoleEnumIn(roleRequest).stream().collect(Collectors.toSet());

        if(roleEntitySet.isEmpty()){
            throw new IllegalArgumentException("Los roles indicados no existen");
        }

        LocalUser localUser = LocalUser.builder()
                .username(username)
                .name(name)
                .lastname(lastname)
                .email(email)
                .number(number)
                .password(passwordEncoder.encode(password))
                .roles(roleEntitySet)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();
        LocalUser userCreated = userRepository.save(localUser);

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles().forEach(role-> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userCreated.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

//        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getUsername(), userCreated.getPassword(), authorityList);
        String accesToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(userCreated.getUsername(), "Usuario creado exitosamente", accesToken, true );
        return authResponse;
    }
    public LocalUser findUserByJwt(String jwt){
        DecodedJWT decode = jwtUtils.validateToken(jwt);
        String username = jwtUtils.extractUsername(decode);
        LocalUser user = userRepository.findByUsername(username).get();
        return user;
    }
}
