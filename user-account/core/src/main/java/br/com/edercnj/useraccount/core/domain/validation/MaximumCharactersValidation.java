package br.com.edercnj.useraccount.core.domain.validation;

public class MaximumCharactersValidation {

    protected MaximumCharactersValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String value) {
        short maximumCharacters = 24;
        return value.length() <= maximumCharacters;
    }
}
