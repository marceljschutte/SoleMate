package nl.schutte.solemate.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HardloopData {
    private Map<Long, Schoen> schoenenLijst = new HashMap<>();
    private Map<Long, Wedstrijd> wedstrijdLijst = new HashMap<>();
    private Map<Wedstrijd.WedstrijdType, PersoonlijkRecord> persoonlijkRecordLijst = new HashMap<>();
    private List<Training> trainingLijst = new ArrayList<>();
    public boolean isEmpty(){
        return schoenenLijst.isEmpty() && wedstrijdLijst.isEmpty() && persoonlijkRecordLijst.isEmpty() &&  trainingLijst.isEmpty();
    }
}
