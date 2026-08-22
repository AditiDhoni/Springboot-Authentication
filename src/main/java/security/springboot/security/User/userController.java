package security.springboot.security.User;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import security.springboot.security.User.dto.loginRequest;
import security.springboot.security.User.dto.signUpRequest;

@RestController
@RequestMapping("/user")
public class userController {

    private final userService userService;

    public userController(userService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public user signup( @Valid @RequestBody signUpRequest body) {

        return userService.userSignUp(
                body.getUsername(),
                body.getEmail(),
                body.getPassword()
        );
    }

    @PostMapping("/login")
    public user login(@RequestBody loginRequest body) {
        return userService.login(
                body.getIdentifier(),
                body.getPassword()
        );
    }
}