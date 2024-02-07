package fedeCapiz.BaccArte0.service;

import fedeCapiz.BaccArte0.entities.Order;
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
    private UserDAO usersDAO;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private JWTTools jwtTools;

    public NewUserResponseDTO save(NewUserDTO body) throws BadRequestException {
        try {
            Optional<User> found = usersDAO.findByEmail(body.email());
            if (found.isEmpty()) {
     /*   Order newOrder = new Order();
        orderDAO.save(newOrder);*/
        Cart newCart = new Cart();
/*        newCart.setOrder(newOrder);*/
        cartDAO.save(newCart);
        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTFKYn65xn1DBoxENqRfmw7gYoInFtNidmgEDJYfVSgg&s");
        newUser.setPhoneNumber(body.phoneNumber());
        newUser.setTypeOfUser(TypeOfUser.USER);
        newUser.setCart(newCart);
        usersDAO.save(newUser);
        return new NewUserResponseDTO(newUser.getId());
            } else {
                throw new BadRequestException("L'email " + body.email() + " è già in uso!");
            }
        } catch (RuntimeException e) {
            throw new BadRequestException("L'email " + body.email() + " è già in uso!");
        }

    }


    public String authenticateUser(UserLoginDTO body) {
        System.out.println(body.email() + body.password());
        User user = usersService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}