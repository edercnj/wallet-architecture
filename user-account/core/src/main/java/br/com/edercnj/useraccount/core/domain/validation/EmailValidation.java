package br.com.edercnj.useraccount.core.domain.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    protected EmailValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String email) {
        String regexValidEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexValidEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
