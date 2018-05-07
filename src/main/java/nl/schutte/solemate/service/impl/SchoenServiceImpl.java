/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.service.impl;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import nl.schutte.solemate.model.Periode;
import nl.schutte.solemate.model.Schoen;
import nl.schutte.solemate.model.SchoenMerk;
import nl.schutte.solemate.model.SchoenType;
import nl.schutte.solemate.service.SchoenService;

import org.jboss.weld.util.collections.ImmutableSet;

/**
 * SoleMate - Description.
 */
public class SchoenServiceImpl implements SchoenService {
    @Override
    public Set<Schoen> getAlleSchoenen() {

        Set<Schoen> alleSchoenen = new HashSet<>();
        alleSchoenen.add(new Schoen("Gel Evate 3", new Periode(LocalDate.of(2017, Month.MAY, 2), null) , SchoenType.OUTDOOR, SchoenMerk.ASICS));
        alleSchoenen.add(new Schoen("Gel Nimbus 11", new Periode(LocalDate.of(2010, Month.APRIL, 2), LocalDate.of(2015, Month.JANUARY, 19)) , SchoenType.OUTDOOR, SchoenMerk.ASICS));
        alleSchoenen.add(new Schoen("Gel Nimbus 14", new Periode(LocalDate.of(2013, Month.JANUARY, 14), null) , SchoenType.OUTDOOR, SchoenMerk.ASICS));
        alleSchoenen.add(new Schoen("Gel Nimbus 16", new Periode(LocalDate.of(2015, Month.MAY, 18), null) , SchoenType.OUTDOOR, SchoenMerk.ASICS));
        alleSchoenen.add(new Schoen("Indoor Nimbus", new Periode(LocalDate.of(2011, Month.SEPTEMBER, 1), null) , SchoenType.INDOOR, SchoenMerk.ASICS));

        return Collections.unmodifiableSet(alleSchoenen);

    }

    @Override
    public void saveSchoen() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement method saveSchoen() --> void");

    }
}
