package com.justech.mobile.mobileserver.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @ClassName AesUtil
 * @Author: xiaofeng.yang
 * @Date: Create in 9:27 2019/7/11
 * @Description : TODO
 * @Version: 1.0
 */
@Configuration
public class AesUtil {

    //加密方式
    public static String KEY_ALGORITHM = "AES";
    //数据填充方式
    @Value("${aes.algorithmStr}")
    private String algorithmStr;
    @Value("${aes.password}")
    private String password;
    @Value("${aes.ivp}")
    private String ivp;

    public String encrypt(String content) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(algorithmStr);
        SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), KEY_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivp.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
    }

    public String decrypt(String encryptCode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] bytes = Base64.getDecoder().decode(encryptCode);
        Cipher cipher = Cipher.getInstance(algorithmStr);
        SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), KEY_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivp.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return new String(cipher.doFinal(bytes));
    }

}
