package com.br.edercnj.credentials.core.domain.validation;

public class MinimumCharactersValidation {

    protected MinimumCharactersValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String value) {
        short minimumCharacters = 8;
        return value.length() >= minimumCharacters;
    }
}
