package com.coherentsolutions.http;

import com.sun.net.httpserver.BasicAuthenticator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Auth extends BasicAuthenticator {
    public Auth(String input) {
        super(input);
    }
    @Override
    public boolean checkCredentials(String userName, String password) {
        try (InputStream input = Files.newInputStream(Paths.get("store/src/main/resources/config.properties"));) {
            Properties prop = new Properties();
            prop.load(input);
            return userName.equals(prop.getProperty("user")) && password.equals(prop.getProperty("password"));
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}
