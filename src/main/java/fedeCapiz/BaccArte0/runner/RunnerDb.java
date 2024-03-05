package fedeCapiz.BaccArte0.runner;

import fedeCapiz.BaccArte0.entities.*;
import fedeCapiz.BaccArte0.repositories.BottleDAO;
import fedeCapiz.BaccArte0.repositories.CartDAO;
import fedeCapiz.BaccArte0.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RunnerDb implements CommandLineRunner {
    @Autowired
    private BottleDAO bottleDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private PasswordEncoder bcrypt;
    @Override
    public void run(String... args) throws Exception {
        /*
        // creazione 1 admin
        User newUser = new User();
        newUser.setName("Oliver");
        newUser.setSurname("Admin");
        newUser.setUsername("Oliver");
        newUser.setEmail("oli@gmail.com");
        newUser.setPassword(bcrypt.encode("1234"));
        newUser.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTFKYn65xn1DBoxENqRfmw7gYoInFtNidmgEDJYfVSgg&s");
        newUser.setPhoneNumber(1234L);
        newUser.setTypeOfUser(TypeOfUser.ADMIN);
        newUser.setAccountDeleted(false);
        User savedUser = userDAO.save(newUser);
        Cart newCart = new Cart();
        newCart.setUser(savedUser);
        cartDAO.save(newCart);
        newUser.setCart(newCart);
        userDAO.save(newUser);
        
        //crezione 2 admin
        User newUser1 = new User();
        newUser1.setName("Lorenzo");
        newUser1.setSurname("Admin");
        newUser1.setUsername("lo");
        newUser1.setEmail("lo@gmail.com");
        newUser1.setPassword(bcrypt.encode("1234"));
        newUser1.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTFKYn65xn1DBoxENqRfmw7gYoInFtNidmgEDJYfVSgg&s");
        newUser1.setPhoneNumber(1234L);
        newUser1.setTypeOfUser(TypeOfUser.ADMIN);
        newUser1.setAccountDeleted(false);
        User savedUser1 = userDAO.save(newUser1);
        Cart newCart1 = new Cart();
        newCart1.setUser(savedUser1);
        cartDAO.save(newCart1);
        newUser1.setCart(newCart1);
        userDAO.save(newUser1);

        //creazione Profilo artisti
        User newUser2 = new User();
        newUser2.setName("Anna");
        newUser2.setSurname("Artist");
        newUser2.setUsername("anna");
        newUser2.setEmail("anna@gmail.com");
        newUser2.setPassword(bcrypt.encode("1234"));
        newUser2.setAvatar("http://res.cloudinary.com/dorr4si5z/image/upload/v1709313070/jtbz78hkujiicqtef8rn.jpg");
        newUser2.setPhoneNumber(1234L);
        newUser2.setTypeOfUser(TypeOfUser.ARTIST);
        newUser2.setAccountDeleted(false);
        User savedUser2 = userDAO.save(newUser2);
        Cart newCart2 = new Cart();
        newCart2.setUser(savedUser2);
        cartDAO.save(newCart2);
        newUser2.setCart(newCart2);
        userDAO.save(newUser2);



        // creazione Bottiglia Red Berry
        Bottle newBottle = new Bottle();
        newBottle.setCSDeleted(false);
        newBottle.setSizeBottle(SizeBottle.SETTANTA_CL);
        newBottle.setBottleContents(BottleContents.RED_BERRY_GIN);
        newBottle.setPrice(32.50);
        newBottle.setCustom(false);
        newBottle.setBottigliCompleta("https://globaluserfiles.com/media/132217_0528c33e6de48810a996f76c6553fe7ac98574df.png/v1/w_0,h_441/mani%20mock%20up%20gin%20x%20sito.png");
        newBottle.setUser(newUser);
        bottleDAO.save(newBottle);

        //creazione Bottiglia Ib
        Bottle newBottle1 = new Bottle();
        newBottle1.setCSDeleted(false);
        newBottle1.setSizeBottle(SizeBottle.SETTANTA_CL);
        newBottle1.setBottleContents(BottleContents.ITALIAN_BOUQUET);
        newBottle1.setPrice(23.50);
        newBottle1.setCustom(false);
        newBottle1.setBottigliCompleta("");
        newBottle1.setUser(newUser);
        bottleDAO.save(newBottle1);

        //creazione Bottiglia custum di Anna
        Bottle newBottle2 = new Bottle();
        newBottle2.setCustom(true);
        newBottle2.setCSDeleted(false);
        newBottle2.setSizeBottle(SizeBottle.SETTANTA_CL);
        newBottle2.setBottleContents(BottleContents.RED_BERRY_GIN);
        newBottle2.setPrice(40.00);
        newBottle2.setBottigliCompleta("https://res.cloudinary.com/dorr4si5z/image/upload/v1709573857/mtjqhln2yd1t767umyfe.png");
        newBottle2.setUser(newUser2);
        newBottle2.setArtist(Artist.ANNA);
        bottleDAO.save(newBottle2);
        */

    }

}
