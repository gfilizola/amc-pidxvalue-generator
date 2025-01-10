package com.ippon.bm.tools;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class AMCPIDXValueGeneratorTest {

    @Test
    void generatePIDs() throws Exception {
        String keyHex = "12345678876543219ABCDEF00FEDCBA91234567887654321";
        byte[] keyBytes = hexStringToByteArray(keyHex);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        BigInteger V = new BigInteger("000ABC12", 16); // Example unique value
        String[] PIDs = AMCPIDXValueGenerator.generatePIDs(key, V);

        System.out.println("Generated PIDs: " + Arrays.toString(PIDs));
        Arrays.stream(PIDs).forEach(System.out::println);
        assertThat(PIDs).isNotEmpty();
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
