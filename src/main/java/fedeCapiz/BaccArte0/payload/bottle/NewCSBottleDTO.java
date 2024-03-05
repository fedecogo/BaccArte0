package fedeCapiz.BaccArte0.payload.bottle;

import fedeCapiz.BaccArte0.entities.Artist;
import fedeCapiz.BaccArte0.entities.BottleContents;
import fedeCapiz.BaccArte0.entities.SizeBottle;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record NewCSBottleDTO(
        @NotNull(message = "La misura è obbligatoria")
        SizeBottle sizeBottle,
        @NotNull(message = "E' obbligatorio scegliere il distillato desiderato")
        BottleContents bottleContents,
        @NotNull
        Artist artist,
        @NotEmpty(message = "Fornisci una foto della bottiglia è obbligatorio")
        String bottigli_completa


) {
}
