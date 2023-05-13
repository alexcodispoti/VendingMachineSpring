package com.distributore.distributore;

public class BevandaNonTrovataException extends Exception {

    @Override
    public String getMessage() {
        return "Bevanda non trovata";
    }   
}
