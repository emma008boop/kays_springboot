package com.myapp.gestor;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.myapp.gestor.model.Permission;
import com.myapp.gestor.model.RoleEntity;
import com.myapp.gestor.model.RoleEnum;
import com.myapp.gestor.model.User;
import com.myapp.gestor.repository.UserRepository;

@SpringBootApplication
public class GestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository repository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (repository.findByUsername("emma").isPresent())
				return;
			Permission createPermission = Permission.builder()
					.name("CREATE")
					.build();
			Permission readPermission = Permission.builder()
					.name("READ")
					.build();
			Permission detPermission = Permission.builder()
					.name("DELETE")
					.build();
			Permission readUsersPermission = Permission.builder()
					.name("READ_USERS")
					.build();
			Permission delUserPermission = Permission.builder()
					.name("DELETE_USER")
					.build();
			Permission createUserPermission = Permission.builder()
					.name("CREATE_USER")
					.build();
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(readUsersPermission, delUserPermission, createUserPermission))
					.build();
			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission, detPermission))
					.build();
			User userEmma = User.builder()
					.username("emma")
					.email("emma@gmail.com")
					.passwordHash(passwordEncoder.encode("emma123"))
					.roles(Set.of(roleAdmin, roleUser))
					.isEnable(true)
					.accountNoBlocked(true)
					.isAccountNoExpired(true)
					.isCredentialsNoExpired(true)
					.build();
			User userMarshall = User.builder()
					.username("marshall")
					.email("marshall@gmail.com")
					.passwordHash(passwordEncoder.encode("marshall123"))
					.roles(Set.of(roleUser))
					.isEnable(true)
					.accountNoBlocked(true)
					.isAccountNoExpired(true)
					.isCredentialsNoExpired(true)
					.build();

			repository.saveAll(List.of(userEmma, userMarshall));

			// DELETE
			System.out.println("Usuarios de prueba creados correctamente");
		};
	}

}
