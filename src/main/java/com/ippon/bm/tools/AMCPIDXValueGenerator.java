package com.ippon.bm.tools;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.apache.commons.lang3.StringUtils;

public class AMCPIDXValueGenerator {

    public static String[] generatePIDs(String keyHex, String vHex) throws Exception {
        byte[] keyBytes = hexStringToByteArray(keyHex);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        BigInteger V = new BigInteger(vHex, 16); // Example unique value
        String[] PIDs = new String[35];

        BigInteger PL = V.shiftRight(16);
        BigInteger PR = V.and(new BigInteger("FFFF", 16));

        for (int sector = 1; sector <= 35; sector++) {
            PIDs[sector - 1] = generateSectorPID(key, PL, PR, sector);
        }

        return PIDs;
    }

    public static String generateSectorPID(SecretKey key, BigInteger PL, BigInteger PR, int sector) throws Exception {
        BigInteger currentPL = PL;
        BigInteger currentPR = PR;

        for (int n = 1; n <= 12; n++) {
            BigInteger T = currentPR.shiftLeft(48).add(BigInteger.valueOf(sector).shiftLeft(32)).add(BigInteger.valueOf(n));
            BigInteger F = TDESencryption(key, T).shiftRight(48);
            BigInteger X = currentPL.xor(F);
            currentPL = currentPR;
            currentPR = X;
        }

        BigInteger pid = currentPL.shiftLeft(16).add(currentPR);
        String pidHex = pid.toString(16).toUpperCase();
        return StringUtils.leftPad(pidHex, 8, '0');
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static BigInteger TDESencryption(SecretKey key, BigInteger data) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] dataBytes = ByteBuffer.allocate(8).putLong(data.longValue()).array();
        byte[] encryptedBytes = cipher.doFinal(dataBytes);

        return new BigInteger(1, encryptedBytes);
    }

}
