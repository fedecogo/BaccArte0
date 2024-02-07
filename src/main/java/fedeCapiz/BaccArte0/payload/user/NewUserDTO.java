package fedeCapiz.BaccArte0.payload.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewUserDTO(
        @NotEmpty(message = "Il nome è obbligatorio")
        String name,
        @NotEmpty(message = "Il cognome è obbligatorio")
        String surname,
        @NotEmpty(message = "Lo username è obbligatorio")
        String username,
        @NotEmpty(message = "l'email è obbligatoria")
        String email,
        @NotEmpty(message = "La password è obbligatoria")
        String password,
        @NotNull(message = "Il nu di telefono �� obbligatorio")
        Long phoneNumber





) {
}