package com.example.agenda.model.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validar {

    public static boolean validarTelefono(String tlf) {
        return tlf.length() == 9 && tlf.charAt(0) >= '6' && tlf.charAt(0) <= '9';
    }

    public static boolean validarString(String cad) {
        boolean error = false;

        if(cad.length() > 15) {
            error = true;
        } else {

            for (int i = 0; i < cad.length(); i++) {
                if (!Character.isAlphabetic(cad.charAt(i)) && cad.charAt(i) != ' ') {
                    error = true;
                }
            }
        }
        return error;
    }

    public static boolean validarStringAp(String cad) {
        boolean error = false;

        if(cad.length() > 25) {
            error = true;
        } else {

            for (int i = 0; i < cad.length(); i++) {
                if (!Character.isAlphabetic(cad.charAt(i)) && cad.charAt(i) != ' ') {
                    error = true;
                }
            }
        }
        return error;
    }

    public static boolean validarFecha(String fecha) {
        return fecha.length() == 10 && fecha.charAt(2) >= '/' && fecha.charAt(5) <= '/';
    }

    public static boolean lenCalle(String cad) {
        return cad.length() <= 15;
    }

    public static boolean lenNumero(String num) {
        return num.length() <= 3;
    }

}
