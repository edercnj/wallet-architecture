package com.br.edercnj.credentials.core.domain.exception;

public class RecoveryCodeExpiredException extends Exception {

    public RecoveryCodeExpiredException() {
        super("Recovery code Expired");
    }
}
