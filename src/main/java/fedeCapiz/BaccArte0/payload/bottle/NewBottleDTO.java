package fedeCapiz.BaccArte0.payload.bottle;

import fedeCapiz.BaccArte0.entities.BottleContents;
import fedeCapiz.BaccArte0.entities.SizeBottle;
import fedeCapiz.BaccArte0.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewBottleDTO (
        @NotNull(message = "La misura è obbligatoria")
         SizeBottle sizeBottle,
         @NotNull(message = "E' obbligatorio scegliere il distillato desiderato")
         BottleContents bottleContents,
         @NotNull(message = "Il prezzo è obbligatorio")
         Double price,
         @NotEmpty(message = "Fornisci una foto della bottiglia è obbligatorio")
         String bottigliCompleta,
         @NotEmpty(message = "Fornisci una foto dell'immagine dello logo user")
         String logoUser
       /*  @NotNull(message = "L'id dell'utente è obbligatorio")
        User userId*/

){
}
