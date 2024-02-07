package fedeCapiz.BaccArte0.controllers;

import fedeCapiz.BaccArte0.exceptions.BadRequestException;
import fedeCapiz.BaccArte0.payload.bottle.NewBottleDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewBottleResponseDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleDTO;
import fedeCapiz.BaccArte0.payload.bottle.NewCSBottleResponseDTO;
import fedeCapiz.BaccArte0.service.BottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/bottles")
public class BottleControllers {
    @Autowired
    BottleService bottleService;

    //path per gli admin per crerare bottiglie
    @PostMapping("/admin/addOneBottle")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewBottleResponseDTO addOneBottle(@RequestBody @Validated  NewBottleDTO newBottleDTO, BindingResult validation) throws BadRequestException {
            System.out.println(validation);
            if (validation.hasErrors()) {
                System.out.println(validation.getAllErrors());
                throw new BadRequestException("Invalid request payload" + validation.getErrorCount());
            } else {
                return bottleService.saveBottle(newBottleDTO);
            }
    }


    @PostMapping("/crateYourBottle")
    @ResponseStatus(HttpStatus.CREATED)
    public NewCSBottleResponseDTO crateYourBottle(@RequestBody @Validated NewCSBottleDTO newCSBottleDTO, BindingResult validation) throws BadRequestException, IOException {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Invalid request payload" + validation.getErrorCount());
        } else {
            return bottleService.saveCustomBottle(newCSBottleDTO);
        }
    }




}
