package com.ippon.bm.tools;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AMCPIDXValueGeneratorTest {

    private static AMCPIDXValueGenerator amcPIDXValueGenerator;

    @BeforeAll
    static void setup() {
        String keyHex = "12345678876543219ABCDEF00FEDCBA9";
        String vHex = "000ABC12";
        amcPIDXValueGenerator = new AMCPIDXValueGenerator(keyHex, vHex);
    }

    @Test
    void shouldGeneratePIDsForSectorsFrom1To35() throws Exception {
        String[] PIDs = amcPIDXValueGenerator.generatePIDs();

        System.out.println("Generated PIDs: " + Arrays.toString(PIDs));
        Arrays.stream(PIDs).forEach(System.out::println);
        assertThat(PIDs).isNotEmpty();
        assertThat(PIDs).hasSize(35);
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

    @ParameterizedTest
    @CsvSource({"1, 01EDEE43", "2, 014C8CCC", "3, 7406FB78", "4, A40CF9A1", "5, 357194EC",
            "6, E693FC8A", "7, 25102112", "8, B0F5586B", "9, CAFD8AFC", "10, 963458A2"})
    void shouldGeneratePIDForSector(int sector, String expectedPID) throws Exception {
        String PID = amcPIDXValueGenerator.generateSectorPID(sector);

        System.out.println("Generated PID for sector " + sector + ": " + PID);
        assertThat(PID).isEqualTo(expectedPID);
    }

}
