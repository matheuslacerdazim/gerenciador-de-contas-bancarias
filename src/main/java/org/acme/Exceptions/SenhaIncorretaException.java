package org.acme.Exceptions;

public class SenhaIncorretaException extends Exception {
    public SenhaIncorretaException(String mensagem){
        super(mensagem);
    }
}
