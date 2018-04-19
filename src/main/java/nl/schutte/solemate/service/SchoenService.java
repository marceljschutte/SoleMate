/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.service;

import java.util.List;

import nl.schutte.solemate.model.Schoen;

/**
 * SoleMate - Description.
 */
public interface SchoenService {

    public List<Schoen> getAlleSchoen();

    public void saveSchoen();

}
