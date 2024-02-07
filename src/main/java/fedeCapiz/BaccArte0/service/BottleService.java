package fedeCapiz.BaccArte0.service;

import fedeCapiz.BaccArte0.entities.Bottle;
import fedeCapiz.BaccArte0.entities.BottleContents;
import fedeCapiz.BaccArte0.entities.SizeBottle;
import fedeCapiz.BaccArte0.entities.User;
import fedeCapiz.BaccArte0.exceptions.NotFoundException;
import fedeCapiz.BaccArte0.payload.bottle.NewBottleDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewBottleResponseDTO;
import fedeCapiz.BaccArte0.repositories.BottleDAO;
import fedeCapiz.BaccArte0.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BottleService {
    @Autowired
    private BottleDAO bottleDAO;
    @Autowired
    private UserDAO userDAO;



    //Save NORMAL BOTTLE
public NewBottleResponseDTO saveBottle(NewBottleDTO body) {
    Bottle newBottle = new Bottle();
    newBottle.setSizeBottle(body.sizeBottle());
    newBottle.setBottleContents(body.bottleContents());
    newBottle.setPrice(body.price());
    newBottle.setBottigliCompleta("https://globaluserfiles.com/media/132217_0528c33e6de48810a996f76c6553fe7ac98574df.png/v1/w_0,h_441/mani%20mock%20up%20gin%20x%20sito.png");
    newBottle.setLogoUser("no logo custom per questa bottiglia");
    // troviamo user che ha creato questa bottiglia
    //prova non definitiva
    Long userId = Long.valueOf(8);
    User found = userDAO.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    newBottle.setUser(found);
    bottleDAO.save(newBottle);
    return new NewBottleResponseDTO(newBottle.getId_bottle());
}

//save custum bottle
    public NewCSBottleResponseDTO saveCustomBottle(NewCSBottleDTO body) {
    Bottle newBottle = new Bottle();
    newBottle.set
    return new NewCSBottleResponseDTO();
    }
}
