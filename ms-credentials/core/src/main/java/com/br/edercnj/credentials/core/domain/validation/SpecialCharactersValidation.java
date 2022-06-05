package com.br.edercnj.credentials.core.domain.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharactersValidation  {

    protected SpecialCharactersValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String value) {
        String regexToFindSpecialCharacter = "[^\\w\\*]";
        Pattern pattern = Pattern.compile(regexToFindSpecialCharacter);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
