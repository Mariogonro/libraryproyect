package com.ada.library.dto;

public class AuthenticationRequest {
    private String username;
    private String password;
    private String dbType;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password, String dbType) {
        this.username = username;
        this.password = password;
        this.dbType = dbType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
}
