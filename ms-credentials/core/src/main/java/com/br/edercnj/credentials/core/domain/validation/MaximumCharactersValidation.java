package com.br.edercnj.credentials.core.domain.validation;

public class MaximumCharactersValidation {

    protected MaximumCharactersValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String value) {
        short maximumCharacters = 50;
        return value.length() <= maximumCharacters;
    }
}
