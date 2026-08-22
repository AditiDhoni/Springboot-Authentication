package security.springboot.security.User;

import org.springframework.stereotype.Service;

@Service
public class userService {

    private final userRepository userRepository;

    public userService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    public user userSignUp(String username, String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        user newUser = new user();

        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);

        return userRepository.save(newUser);
    }

    public user login(String identifier, String password) {

        user user = userRepository.findByUsername(identifier)
                .orElseGet(() -> userRepository.findByEmail(identifier)
                        .orElseThrow(() ->
                                new RuntimeException("Invalid username/email or password")
                        )
                );

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}