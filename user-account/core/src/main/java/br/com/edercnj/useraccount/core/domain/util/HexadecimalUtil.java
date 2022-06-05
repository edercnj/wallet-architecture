package br.com.edercnj.useraccount.core.domain.util;

public class HexadecimalUtil {

    protected HexadecimalUtil() {throw new IllegalStateException("Utility class");}

    public static String byteArrayToHexa(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}