package nl.schutte.solemate.model;

import java.time.Duration;
import java.time.Year;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingGegevens {

    private Year jaar;
    private List<Training> trainingen;

}
