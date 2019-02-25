package nl.schutte.solemate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersoonlijkRecord {

    private Long id;
    private Wedstrijd wedstrijd;
}
