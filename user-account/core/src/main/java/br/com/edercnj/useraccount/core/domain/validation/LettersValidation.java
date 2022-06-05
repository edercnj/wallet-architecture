package br.com.edercnj.useraccount.core.domain.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LettersValidation  {

    protected LettersValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String value) {
        String regexToFindLetters = "[a-zA-Z]";
        Pattern pattern = Pattern.compile(regexToFindLetters);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
