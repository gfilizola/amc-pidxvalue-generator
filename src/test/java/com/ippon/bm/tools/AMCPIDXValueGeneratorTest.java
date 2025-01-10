package com.ippon.bm.tools;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class AMCPIDXValueGeneratorTest {

    @Test
    void generatePIDs() throws Exception {
        String keyHex = "12345678876543219ABCDEF00FEDCBA9";
        String vHex = "000ABC12";
        String[] PIDs = AMCPIDXValueGenerator.generatePIDs(keyHex, vHex);

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

}
