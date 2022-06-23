package br.com.edercnj.useraccount.core.domain.exception;

public class AesEncryptException extends Exception {
    public AesEncryptException(Exception e) {
        super(e.getMessage());
    }
}
