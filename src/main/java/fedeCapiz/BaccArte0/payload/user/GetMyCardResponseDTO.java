package fedeCapiz.BaccArte0.payload.user;

import fedeCapiz.BaccArte0.entities.Bottle;

import java.util.List;

public record GetMyCardResponseDTO(
        double totCartPrice,
        List<Bottle> bottles
) {

}
