/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.service;

import java.util.List;
import java.util.Set;

import nl.schutte.solemate.model.Schoen;

/**
 * SoleMate - Description.
 */
public interface SchoenService {

    public Set<Schoen> getAlleSchoenen();

    public void saveSchoen();

}
