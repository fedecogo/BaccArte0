package fedeCapiz.BaccArte0.service;

import fedeCapiz.BaccArte0.entities.*;
import fedeCapiz.BaccArte0.exceptions.NotFoundException;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleResponseDTO;
import fedeCapiz.BaccArte0.payload.cart.AddToCartDTO;
import fedeCapiz.BaccArte0.payload.cart.AddToCartResponseDTO;
import fedeCapiz.BaccArte0.repositories.BottleDAO;
import fedeCapiz.BaccArte0.repositories.CartDAO;
import fedeCapiz.BaccArte0.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BottleDAO bottleDAO;
    @Autowired
    private CartDAO cartDAO;

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public User findById(long id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public User findByEmail(String email) throws NotFoundException {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }


    //save custum bottle
    public NewCSBottleResponseDTO saveCustomBottle(NewCSBottleDTO body, Long userID) throws IOException {
        Bottle newBottle = new Bottle();
        newBottle.setCustom(true);
        newBottle.setSizeBottle(body.sizeBottle());
        newBottle.setBottleContents(body.bottleContents());
        if (newBottle.getSizeBottle() == SizeBottle.DIECI_CL) {
            if (newBottle.getBottleContents() == BottleContents.RED_BERRY_GIN) {
                newBottle.setPrice(5);
            } else if (newBottle.getBottleContents() == BottleContents.ITALIAN_BOUQUET) {
                newBottle.setPrice(5);
            }
        } else if (newBottle.getSizeBottle() == SizeBottle.SETTANTA_CL) {
            if (newBottle.getBottleContents() == BottleContents.RED_BERRY_GIN) {
                newBottle.setPrice(40);
            } else if (newBottle.getBottleContents() == BottleContents.ITALIAN_BOUQUET) {
                newBottle.setPrice(40);
            }
        }
        User found = userDAO.findById(userID).orElseThrow(() -> new NotFoundException("User not found with"));
        newBottle.setLogoUser(found.getAvatar());
        newBottle.setUser(found);
       /* if(body.artist() != null ){*/
        newBottle.setArtist(body.artist());
        bottleDAO.save(newBottle);
        return new NewCSBottleResponseDTO(newBottle.getId_bottle());

    }
    //metodo che aggiunge una bottiglia al carello

    public AddToCartResponseDTO addBottleToCart(AddToCartDTO body, Long userId, Long bottleId) {
        User user = userDAO.findById(userId).orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + userId));
        Cart cart = user.getCart();
        Bottle bottle = bottleDAO.findById(bottleId).orElseThrow(() -> new NotFoundException("Bottiglia non trovata con ID: " + bottleId));
        //se la bottiglia è custom
        if (bottle.isCustom()) {
            //prezzo in base alla dimensione della bottiglia e al contenuto
            int pricePerBottle = 0;
            if (bottle.getSizeBottle() == SizeBottle.DIECI_CL) {
                if (bottle.getBottleContents() == BottleContents.RED_BERRY_GIN || bottle.getBottleContents() == BottleContents.ITALIAN_BOUQUET) {
                    pricePerBottle = 5;
                }
            } else if (bottle.getSizeBottle() == SizeBottle.SETTANTA_CL) {
                if (bottle.getBottleContents() == BottleContents.RED_BERRY_GIN) {
                    pricePerBottle = 40;
                }
            }
            List<Bottle> bottlesToAdd = new ArrayList<>();
            for (int i = 0; i < body.quantity(); i++) {
                bottlesToAdd.add(bottle);
            }
            cart.getBottles().addAll(bottlesToAdd);
            cart.setTotCartPrice(cart.getTotCartPrice() + (body.quantity() * pricePerBottle));
        } else {
            //se non è custum
            List<Bottle> bottlesToAdd = new ArrayList<>();
            for (int i = 0; i < body.quantity(); i++) {
                bottlesToAdd.add(bottle);
            }
            cart.getBottles().add(bottle);
        }
        cartDAO.save(cart);
       return new AddToCartResponseDTO();
    }
}
