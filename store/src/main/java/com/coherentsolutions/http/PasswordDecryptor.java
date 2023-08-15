package com.coherentsolutions.http;

import com.coherentsolutions.store.Store;
import org.jasypt.util.text.AES256TextEncryptor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public class PasswordDecryptor {
    private static volatile PasswordDecryptor instance;
    public static PasswordDecryptor getInstance() {
        PasswordDecryptor result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Store.class) {
            if (instance == null) {
                instance = new PasswordDecryptor();
            }
            return instance;
        }
    }

    public static String getPassword() {
        String password;
        try (InputStream input = Files.newInputStream(Paths.get("store/src/main/resources/application.properties"))) {
            Properties prop = new Properties();
            prop.load(input);
            password = prop.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("Something wrong with loading password from properties: " + e);
        }
        return password;
    }

    public static String decryptPassword() {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword("secretkey");
        return encryptor.decrypt(getPassword());
    }
}
