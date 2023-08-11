package com.coherentsolutions.http;

import com.sun.net.httpserver.BasicAuthenticator;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Auth extends BasicAuthenticator {
    private final String user;
    private final String password;


    public Auth(String realm) {
        super(realm);
        try (InputStream input = Files.newInputStream(Paths.get("store/src/main/resources/application.properties"))) {
            Properties prop = new Properties();
            prop.load(input);
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("Something wrong with authentication" + e);
        }
    }

    @Override
    public boolean checkCredentials(String userName, String password) {
        return userName.equals(this.user) && password.equals(this.password);
    }
}
