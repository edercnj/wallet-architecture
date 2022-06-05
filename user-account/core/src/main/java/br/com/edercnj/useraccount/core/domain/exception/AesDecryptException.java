package br.com.edercnj.useraccount.core.domain.exception;

public class AesDecryptException extends Exception {
    public AesDecryptException(Exception e) {
        super(e.getMessage());
    }
}
