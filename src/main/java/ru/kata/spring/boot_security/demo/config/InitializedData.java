package ru.kata.spring.boot_security.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@Configuration
public class InitializedData {
    @Bean
    public CommandLineRunner initData(UserServiceImpl userServiceImpl, RoleRepository  roleRepository) {
        return args -> {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            User user1 = new User();
            user1.setAge(54);
            user1.setPassword("123");
            user1.setFirstName("first name 1");
            user1.setLastName("last name 1");
            user1.setEmail("email1@gmail.com");
            userServiceImpl.addUser(user1, List.of(userRole.getId()));

            User user2 = new User();
            user2.setAge(17);
            user2.setPassword("123");
            user2.setFirstName("first name 2");
            user2.setLastName("last name 2");
            user2.setEmail("email2@gmail.com");
            userServiceImpl.addUser(user2, List.of(userRole.getId()));

            User admin = new User();
            admin.setPassword("123");
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setAge(33);
            admin.setEmail("admin@gmail.com");
            userServiceImpl.addUser(admin, List.of(adminRole.getId(), userRole.getId()));

            User admin1 = new User();
            admin1.setAge(29);
            admin1.setPassword("123");
            admin1.setFirstName("admin");
            admin1.setLastName("admin");
            admin1.setEmail("admin1@gmail.com");
            userServiceImpl.addUser(admin1, List.of(adminRole.getId(), userRole.getId()));
        };
    }
}
