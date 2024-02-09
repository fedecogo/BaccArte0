package fedeCapiz.BaccArte0.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import fedeCapiz.BaccArte0.config.CloudinaryConfig;
import fedeCapiz.BaccArte0.entities.Bottle;
import fedeCapiz.BaccArte0.entities.BottleContents;
import fedeCapiz.BaccArte0.entities.SizeBottle;
import fedeCapiz.BaccArte0.entities.User;
import fedeCapiz.BaccArte0.exceptions.NotFoundException;
import fedeCapiz.BaccArte0.payload.bottle.NewBottleDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewBottleResponseDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleResponseDTO;
import fedeCapiz.BaccArte0.repositories.BottleDAO;
import fedeCapiz.BaccArte0.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BottleService {
    @Autowired
    private BottleDAO bottleDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Cloudinary cloudinary;


    //Save NORMAL BOTTLE
    public NewBottleResponseDTO saveBottle(NewBottleDTO body) {
    Bottle newBottle = new Bottle();
    newBottle.setSizeBottle(body.sizeBottle());
    newBottle.setBottleContents(body.bottleContents());
    newBottle.setPrice(body.price());
    newBottle.setCustom(false);
    newBottle.setBottigliCompleta("https://globaluserfiles.com/media/132217_0528c33e6de48810a996f76c6553fe7ac98574df.png/v1/w_0,h_441/mani%20mock%20up%20gin%20x%20sito.png");
    // troviamo user che ha creato questa bottiglia, questo metodo saveBottle lo puo fare solo oliver(per velocità ci metto già il suo id)
    Long userId = Long.valueOf(1);
    User found = userDAO.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    newBottle.setUser(found);

    bottleDAO.save(newBottle);
    return new NewBottleResponseDTO(newBottle.getId_bottle());
}

    // salvo l'immagine nel avatar dello user
  public String saveImage(MultipartFile file,Long id) throws IOException {
   User found = userDAO.findById(id).orElseThrow(() -> new NotFoundException("user not found with id: " + id));
   String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
   found.setAvatar(url);
   userDAO.save(found);
  return url;
    }
    public List<Bottle> getAllMyBottles(Long userId) {
        return bottleDAO.findByUserId(userId);
    }
}
