package com.br.edercnj.credentials.core.domain.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UppercaseValidation {

    protected UppercaseValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String value) {
        String regexToFindUppercase = "[A-Z]";
        Pattern pattern = Pattern.compile(regexToFindUppercase);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
