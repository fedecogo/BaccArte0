package fedeCapiz.BaccArte0.service;

import fedeCapiz.BaccArte0.entities.Bottle;
import fedeCapiz.BaccArte0.entities.BottleContents;
import fedeCapiz.BaccArte0.entities.SizeBottle;
import fedeCapiz.BaccArte0.entities.User;
import fedeCapiz.BaccArte0.exceptions.NotFoundException;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleResponseDTO;
import fedeCapiz.BaccArte0.repositories.BottleDAO;
import fedeCapiz.BaccArte0.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BottleDAO bottleDAO;

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
}
