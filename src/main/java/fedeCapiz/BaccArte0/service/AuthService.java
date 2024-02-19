package fedeCapiz.BaccArte0.service;

import fedeCapiz.BaccArte0.entities.Cart;
import fedeCapiz.BaccArte0.entities.TypeOfUser;
import fedeCapiz.BaccArte0.entities.User;
import fedeCapiz.BaccArte0.exceptions.BadRequestException;
import fedeCapiz.BaccArte0.exceptions.UnauthorizedException;
import fedeCapiz.BaccArte0.payload.login.UserLoginDTO;
import fedeCapiz.BaccArte0.payload.user.NewUserDTO;
import fedeCapiz.BaccArte0.payload.user.NewUserResponseDTO;
import fedeCapiz.BaccArte0.repositories.CartDAO;
import fedeCapiz.BaccArte0.repositories.OrderDAO;
import fedeCapiz.BaccArte0.repositories.UserDAO;
import fedeCapiz.BaccArte0.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private JWTTools jwtTools;

    //registrazione
    public NewUserResponseDTO save(NewUserDTO body) throws BadRequestException {
        Optional<User> found = userDAO.findByEmail(body.email());
        if (found.isPresent()) {
            throw new BadRequestException("Email già utilizzata!");
        }

        // Crea un nuovo utente
        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTFKYn65xn1DBoxENqRfmw7gYoInFtNidmgEDJYfVSgg&s");
        newUser.setPhoneNumber(body.phoneNumber());
        newUser.setTypeOfUser(TypeOfUser.USER);
        newUser.setAccountDeleted(false);

        // Salva il nuovo utente nel database
        User savedUser = userDAO.save(newUser);

        // Crea un nuovo carrello e associa l'utente ad esso
        Cart newCart = new Cart();
        newCart.setUser(savedUser);
        cartDAO.save(newCart);
        newUser.setCart(newCart);
        userDAO.save(newUser);
        // Ritorna la risposta con l'ID
        return new NewUserResponseDTO(savedUser.getId());
    }

    //login

    public String authenticateUser(UserLoginDTO body) throws BadRequestException {
        User user = usersService.findByEmail(body.email());
        if (user.isAccountDeleted()) {
            throw new UnauthorizedException("Hai eliminato il tuo account");
        }
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}
