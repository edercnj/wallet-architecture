package br.com.edercnj.useraccount.core.domain.validation;

import br.com.edercnj.useraccount.core.domain.exception.InvalidPasswordExpcetion;

public class PasswordValidator {

    protected PasswordValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validate(String value) throws InvalidPasswordExpcetion {
        boolean passwordIsvalid = MinimumCharactersValidation.isValid(value)
                && MaximumCharactersValidation.isValid(value)
                && NumbersValidation.isValid(value)
                && LettersValidation.isValid(value)
                && UppercaseValidation.isValid(value)
                && LowercaseValidation.isValid(value)
                && SpecialCharactersValidation.isValid(value);

        if (!passwordIsvalid) {
            throw new InvalidPasswordExpcetion();
        }
    }
}
