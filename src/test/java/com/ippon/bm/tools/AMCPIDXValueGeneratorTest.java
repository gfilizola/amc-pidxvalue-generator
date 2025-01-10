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
        assertThat(PIDs[0]).isEqualTo("01EDEE43");
        assertThat(PIDs[1]).isEqualTo("014C8CCC");
        assertThat(PIDs[2]).isEqualTo("7406FB78");
        assertThat(PIDs[3]).isEqualTo("A40CF9A1");
        assertThat(PIDs[4]).isEqualTo("357194EC");
        assertThat(PIDs[5]).isEqualTo("E693FC8A");
        assertThat(PIDs[6]).isEqualTo("25102112");
        assertThat(PIDs[7]).isEqualTo("B0F5586B");
        assertThat(PIDs[8]).isEqualTo("CAFD8AFC");
        assertThat(PIDs[9]).isEqualTo("963458A2");
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
