package fedeCapiz.BaccArte0.service;

import fedeCapiz.BaccArte0.entities.*;
import fedeCapiz.BaccArte0.exceptions.NotFoundException;
import fedeCapiz.BaccArte0.payload.bottle.DeleteCSBottleDTO;
import fedeCapiz.BaccArte0.payload.bottle.DeleteCSBottleResponseDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleResponseDTO;
import fedeCapiz.BaccArte0.payload.cart.AddToCartDTO;
import fedeCapiz.BaccArte0.payload.cart.AddToCartResponseDTO;
import fedeCapiz.BaccArte0.payload.user.*;
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
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException("User not found with this id:" + id));
    }
    public User findByEmail(String email) throws NotFoundException {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found with this email:" + email));
    }


    //SAVE CUSTUM BOTTLE
    public NewCSBottleResponseDTO saveCustomBottle(NewCSBottleDTO body, Long userID) throws IOException {
        Bottle newBottle = new Bottle();
        newBottle.setCustom(true);
        newBottle.setCSDeleted(false);
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
        User found = userDAO.findById(userID).orElseThrow(() -> new NotFoundException("User not found with id:"+userID));
        newBottle.setLogoUser(found.getAvatar());
        newBottle.setUser(found);
       /* if(body.artist() != null ){*/
        newBottle.setArtist(body.artist());
        bottleDAO.save(newBottle);
        return new NewCSBottleResponseDTO(newBottle.getId_bottle());

    }

    //DELETE CUSTUM BOTTLE
    public DeleteCSBottleResponseDTO deleteCustomBottle(Long userID, Long bottleId) {
        User user = userDAO.findById(userID).orElseThrow(() -> new NotFoundException("User not found with id : " + userID));
        Bottle bottle = bottleDAO.findById(bottleId).orElseThrow(() -> new NotFoundException("Bottle not found with id: " + bottleId));
        if(bottle.getUser().getId() == user.getId()) {
            bottle.setCSDeleted(true);
            bottleDAO.save(bottle);
            return new DeleteCSBottleResponseDTO("You have deleted the custom bottle");
        }else if(bottle.isCSDeleted()){
            return new DeleteCSBottleResponseDTO("This custom bottle has already been deleted");
        }else {
            return new DeleteCSBottleResponseDTO("You are not the owner of this custom bottle");
        }

    }


    //ADD BOTTLE TO CART
    public AddToCartResponseDTO addBottleToCart(AddToCartDTO body, Long userId) {
        User user = userDAO.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id : " + userId));
        Cart cart = user.getCart();
        Bottle bottle = bottleDAO.findById(body.bottleId()).orElseThrow(() -> new NotFoundException("Bottle not found with id: " + body.bottleId()));


        //se la bottiglia è custom
        if (bottle.isCustom()) {
            //prezzo in base alla dimensione della bottiglia e al contenuto
            int pricePerBottle = 0;
            if (bottle.getSizeBottle() == SizeBottle.DIECI_CL) {
                if (bottle.getBottleContents() == BottleContents.RED_BERRY_GIN || bottle.getBottleContents() == BottleContents.ITALIAN_BOUQUET) {
                    pricePerBottle = 5;
                }
            } else if (bottle.getSizeBottle() == SizeBottle.SETTANTA_CL) {
                if (bottle.getBottleContents() == BottleContents.RED_BERRY_GIN || bottle.getBottleContents() == BottleContents.ITALIAN_BOUQUET) {
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
            cart.getBottles().addAll(bottlesToAdd);
            cart.setTotCartPrice(cart.getTotCartPrice() + (body.quantity() * bottle.getPrice() ));

        }
        cartDAO.save(cart);
       return new AddToCartResponseDTO(cart.getTotCartPrice());
    }



    //DELETE USER
    //metodo che prende lo user e gli dice che il suo account è stato eliminato
    //anche se in realtà setta soltanto lo user con un boolean eliminato , nel caso si volesse recuperare l'account.
    public DeleteUserResponseDTO deleteUser(Long id, DeleteUserDTO body) throws NotFoundException {
        if (body.doYouWantDeleteYourAccount()) {
            User user = userDAO.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + id));
            user.setAccountDeleted(true);
            userDAO.save(user);
            //eliminare il token del local storage
            return new DeleteUserResponseDTO("Utente eliminato correttamente2");
        }else {
            return new DeleteUserResponseDTO("Utente non eliminato ");
        }
    }


    //UPDATE USER INFO
    public UpdateUserInfoResponseDTO updateUserInfo(Long id, UpdateUserInfoDTO body) throws NotFoundException {
        User user = userDAO.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + id));
        if(body.name()!= null) {
            user.setName(body.name());
        }
        if(body.surname()!= null) {
            user.setSurname(body.surname());
        }
        if(body.username()!= null) {
            user.setUsername(body.username());
        }
        if(body.email()!= null) {
            user.setEmail(body.email());
        }
        if(body.phoneNumber()!= null) {
            user.setPhoneNumber(body.phoneNumber());
        }
       userDAO.save(user);
        return new UpdateUserInfoResponseDTO("User info updated");
    }

    //get my cart
    public GetMyCardResponseDTO getMyCart(Long id) throws NotFoundException {
        User user = userDAO.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + id));
        Cart cart = user.getCart();
        return new GetMyCardResponseDTO(cart.getTotCartPrice(), cart.getBottles());
    }
}
