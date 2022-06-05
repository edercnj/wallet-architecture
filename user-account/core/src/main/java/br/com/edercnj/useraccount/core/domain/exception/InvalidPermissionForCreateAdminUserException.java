package br.com.edercnj.useraccount.core.domain.exception;

public class InvalidPermissionForCreateAdminUserException extends Exception {

    public InvalidPermissionForCreateAdminUserException() {
        super("DefaultUser not is admin and not have permission for add new users.");
    }
}
