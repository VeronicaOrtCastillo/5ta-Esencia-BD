package com.generation._taEsencia.dto;

public class AuthResponse {

    private String token;
    private String tokenType;
    private long expiresInMs;
    private String correo;
    private String rol;

    public AuthResponse() {
    }

    public AuthResponse(String token, String tokenType, long expiresInMs, String correo, String rol) {
        this.token = token;
        this.tokenType = tokenType;
        this.expiresInMs = expiresInMs;
        this.correo = correo;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresInMs() {
        return expiresInMs;
    }

    public void setExpiresInMs(long expiresInMs) {
        this.expiresInMs = expiresInMs;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}