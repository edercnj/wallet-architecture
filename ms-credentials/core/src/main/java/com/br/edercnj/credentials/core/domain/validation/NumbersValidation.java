package com.br.edercnj.credentials.core.domain.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumbersValidation {

    protected NumbersValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String value) {
        String regexToFindNumbers = "[0-9]";
        Pattern pattern = Pattern.compile(regexToFindNumbers);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
