package fedeCapiz.BaccArte0.payload.user;

public record UpdateUserInfoDTO(
         String name,
         String surname,
         String username,
         String email,
    /* devo studiare come si fa
    String password,*/
         Long phoneNumber
) {
}
