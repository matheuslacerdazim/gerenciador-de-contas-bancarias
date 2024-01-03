package org.acme.Util;

import java.text.NumberFormat;
import java.util.Locale;

public class ConversorUtil {

    public static double ConverterParaDouble(String saldo){

        String saldoString = saldo;
        String saldoStringFormatado = saldo.replace(".","").replace(",",".");
        double saldoDouble = Double.parseDouble(saldoStringFormatado);

        return saldoDouble;
    }

    public static String ConverterParaReais(Double value) {
        Locale locale = new Locale("pt", "BR");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(value);
    }
}
