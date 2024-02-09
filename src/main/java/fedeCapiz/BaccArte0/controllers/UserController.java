package fedeCapiz.BaccArte0.controllers;

import fedeCapiz.BaccArte0.entities.Bottle;
import fedeCapiz.BaccArte0.entities.User;
import fedeCapiz.BaccArte0.exceptions.BadRequestException;
import fedeCapiz.BaccArte0.exceptions.NotFoundException;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleResponseDTO;
import fedeCapiz.BaccArte0.payload.cart.AddToCartDTO;
import fedeCapiz.BaccArte0.payload.cart.AddToCartResponseDTO;
import fedeCapiz.BaccArte0.repositories.UserDAO;
import fedeCapiz.BaccArte0.service.BottleService;
import fedeCapiz.BaccArte0.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BottleService bottleService;
    @Autowired
    private UserDAO userDAO;

    //salva il tuo logo
    @PostMapping("/me/saveImage")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveImage(@RequestParam("image") MultipartFile file, @AuthenticationPrincipal User id) throws IOException {
        User found = userDAO.findById(id.getId()).orElseThrow(() -> new NotFoundException("User not found with id: " + id.getId()));
        Long userId = Long.valueOf(found.getId());
        return bottleService.saveImage(file, userId );
    }
    // crera una bottiglia custum con l'avatar
    @PostMapping("/me/createYourBottle")
    @ResponseStatus(HttpStatus.CREATED)
    public NewCSBottleResponseDTO createYourBottle(@RequestBody @Validated NewCSBottleDTO newCSBottleDTO, BindingResult validation,@AuthenticationPrincipal User id) throws BadRequestException, IOException {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Invalid request payload" + validation.getErrorCount());
        } else {
            return userService.saveCustomBottle(newCSBottleDTO,id.getId());
        }
    }
    // metodo che mostratta tutte le bottigllie del singolo utente
    @GetMapping("/me/allMyBottles")
    public List<Bottle> getAllMyBottles(@AuthenticationPrincipal User user) {
        return bottleService.getAllMyBottles(user.getId());
    }

    // Aggiunge una bottiglia al carrello
    @PostMapping("/me/cart/addBottle")
    @ResponseStatus(HttpStatus.OK)
    public AddToCartResponseDTO addBottleToCart(@RequestBody AddToCartDTO body,@AuthenticationPrincipal User id) {
        return userService.addBottleToCart(body, id.getId(), body.bottleId());
    }

}
