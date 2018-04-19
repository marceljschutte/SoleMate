/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.service;

import java.util.List;

import nl.schutte.solemate.model.PersoonlijkRecord;
import nl.schutte.solemate.model.WedstrijdAfstand;

/**
 * SoleMate - Description.
 */
public interface PersoonlijkRecordService {

    public List<PersoonlijkRecord> getAllePersoonlijkeRecords();

    public PersoonlijkRecord getPersoonlijkRecordVoorWedstrijdAfstand(WedstrijdAfstand wedstrijdAfstand);
}
