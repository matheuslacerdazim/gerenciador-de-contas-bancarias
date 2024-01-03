package org.acme.Exceptions;

public class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException(String mensagem){
        super(mensagem);
    }
}
