package nl.schutte.solemate.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NoRunningException extends RuntimeException {

    String message;

}
