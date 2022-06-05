package br.com.edercnj.useraccount.core.domain.validation;

public class MinimumCharactersValidation {

    protected MinimumCharactersValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String value) {
        short minimumCharacters = 6;
        return value.length() >= minimumCharacters;
    }
}
