package fedeCapiz.BaccArte0.controllers;
import fedeCapiz.BaccArte0.entities.User;
import fedeCapiz.BaccArte0.exceptions.BadRequestException;
import fedeCapiz.BaccArte0.payload.login.UserLoginDTO;
import fedeCapiz.BaccArte0.payload.login.UserLoginResponseDTO;
import fedeCapiz.BaccArte0.payload.user.NewUserDTO;
import fedeCapiz.BaccArte0.payload.user.NewUserResponseDTO;
import fedeCapiz.BaccArte0.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO newUserPayload, BindingResult validation)
            throws BadRequestException {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Invalid request payload" + validation.getErrorCount());
        } else {
            return authService.save(newUserPayload);
        }
    }

}