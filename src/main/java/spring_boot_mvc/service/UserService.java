package spring_boot_mvc.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring_boot_mvc.model.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Iterable<User> allUsers();
    void save(User user);
    void delete(User user);
    User getById(Long id);
    Optional<User> getUserByEmail(String name);
    void deleteById (Long id);
}
