package com.br.edercnj.credentials.core.domain.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LowercaseValidation {

    protected LowercaseValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String value) {
        String regexToFindLowercase = "[a-z]";
        Pattern pattern = Pattern.compile(regexToFindLowercase);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
