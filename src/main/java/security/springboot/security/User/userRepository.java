package security.springboot.security.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<user, Integer> {
    Optional<user> findByUsername(String username);
    Optional<user> findByEmail(String email);

}
