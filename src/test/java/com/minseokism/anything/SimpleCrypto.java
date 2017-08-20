package com.minseokism.anything;

/**
 * Created by Minseokism on 2017-08-12.
 */
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SimpleCrypto {

    private final static String HEX = "0123456789ABCDEF";


    public static String encrypt(String seed, String cleartext) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] result = encrypt(rawKey, cleartext.getBytes());
        return toHex(result);
    }

    public static String decrypt(String seed, String encrypted) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr);
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }


    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }
    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length()/2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2*buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
    }

    public static void main(String args[]) {
        SimpleCrypto cr = new SimpleCrypto();

        // 암호화 Key 설정
        String key = "EncTestByKimByoungKi";

        try {
            System.out.println("Encryped ID1 : " + cr.encrypt(key, "tds"));
            System.out.println("Encryped PW1 : " + cr.encrypt(key, "1qaz@WSX"));
            System.out.println("Encryped ID2 : " + cr.encrypt(key, "tds_dev2"));
            System.out.println("Encryped PW2 : " + cr.encrypt(key, "dnsdud123!"));

            System.out.println("Encryped ID1 : " + cr.decrypt(key, "A81F368A9771CE2E8DB7D98A50B52068"));
            System.out.println("Encryped PW1 : " + cr.decrypt(key, "2642B0D7F2B453DFE51F4A2F5181382D"));
            System.out.println("Encryped ID2 : " + cr.decrypt(key, "244757452F64368B8F238C66E7B65061"));
            System.out.println("Encryped PW2 : " + cr.decrypt(key, "B84D1F51DA7B7A30559208C54C519971"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
