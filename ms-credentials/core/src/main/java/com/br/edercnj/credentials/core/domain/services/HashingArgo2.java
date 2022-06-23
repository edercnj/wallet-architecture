package com.br.edercnj.credentials.core.domain.services;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.nio.charset.StandardCharsets;

public class HashingArgo2 {

    private static final int ITERATIONS = 22;
    private static final int MEMORY = 65536;
    private static final int PARALLELISM = 1;

    protected HashingArgo2() {throw new IllegalStateException("Utility class");}

    public static String hashPassword(String password) {
        Argon2 argon2 = Argon2Factory.create();
        try {
            return argon2.hash(ITERATIONS, MEMORY, PARALLELISM, password.toCharArray());
        } finally {
            argon2.wipeArray(password.toCharArray());
        }
    }

    public static boolean validatePassword(String password, String hashedPassoword) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hashedPassoword, password.getBytes(StandardCharsets.UTF_8));
    }

    public static boolean isHashedPassoword(String password) {
        String[] argon2Params = password.split("\\$");
        if (argon2Params.length != 6) {
            return false;
        } else {
            boolean containsArgon2i = argon2Params[1].contains("argon2i");
            boolean containsMemory = argon2Params[3].contains("m=");
            boolean containsIterations = argon2Params[3].contains("t=");
            boolean containsParallelism = argon2Params[3].contains("p=");
            return containsArgon2i && containsMemory && containsIterations && containsParallelism;
        }
    }
}
