package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAll();

    void addUser(User user, List<Long>roles);

    //void editUser(Long id, String password, String firstName, String lastName, int age, String email, List<Role> roles);
    void editUser(Long id, User user, Long[] selectedRoles);

    void deleteUser(long id);

    User showUser(long id);

    User findByEmail(String email);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
