package com.api.proyectos.proyectManager;

import com.api.proyectos.proyectManager.entities.LocalUser;
import com.api.proyectos.proyectManager.entities.PermissionEntity;
import com.api.proyectos.proyectManager.entities.RoleEntity;
import com.api.proyectos.proyectManager.entities.RoleEnum;
import com.api.proyectos.proyectManager.repositories.PermissionRepository;
import com.api.proyectos.proyectManager.repositories.RoleRepository;
import com.api.proyectos.proyectManager.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ProyectManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectManagerApplication.class, args);
	}

//	@Bean
//	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository, RoleRepository roleRepository) {
//		return args -> {
//			/* Create PERMISSIONS */
//			PermissionEntity createPermission = PermissionEntity.builder()
//					.name("CREATE")
//					.build();
//
//			PermissionEntity readPermission = PermissionEntity.builder()
//					.name("READ")
//					.build();
//
//			PermissionEntity updatePermission = PermissionEntity.builder()
//					.name("UPDATE")
//					.build();
//
//			PermissionEntity deletePermission = PermissionEntity.builder()
//					.name("DELETE")
//					.build();
//
//			PermissionEntity refactorPermission = PermissionEntity.builder()
//					.name("REFACTOR")
//					.build();
//
////			permissionRepository.saveAll(List.of(createPermission,readPermission,updatePermission,deletePermission,refactorPermission));
//
//			/* Create ROLES */
//			RoleEntity roleAdmin = RoleEntity.builder()
//					.roleEnum(RoleEnum.ADMIN)
//					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
//					.build();
//
//			RoleEntity roleUser = RoleEntity.builder()
//					.roleEnum(RoleEnum.USER)
//					.permissionList(Set.of(createPermission, readPermission))
//					.build();
//
//			RoleEntity roleInvited = RoleEntity.builder()
//					.roleEnum(RoleEnum.INVITED)
//					.permissionList(Set.of(readPermission))
//					.build();
//
//			RoleEntity roleDeveloper = RoleEntity.builder()
//					.roleEnum(RoleEnum.DEVELOPER)
//					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
//					.build();
//
////			roleRepository.saveAll(List.of(roleAdmin, roleUser, roleInvited, roleDeveloper));
//			LocalUser userAndrea = LocalUser.builder()
//					.username("andrea")
//					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialNoExpired(true)
//					.roles(Set.of(roleInvited, roleUser))
//					.build();
//
//			LocalUser userAnyi = LocalUser.builder()
//					.username("anyi")
//					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialNoExpired(true)
//					.roles(Set.of(roleDeveloper, roleAdmin))
//					.build();
//
//			userRepository.saveAll(List.of(userAndrea, userAnyi));
//
//		};
//	}

}
