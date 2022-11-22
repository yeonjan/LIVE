package com.ssafy.live.exception.entity;
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400,  " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, " Invalid Input Value"),

    HANDLE_ACCESS_DENIED(403,  "Access is Denied"),

    // Member
    EMAIL_DUPLICATION(400, "Email is Duplication"),
    LOGIN_INPUT_INVALID(400,  "Login input is invalid"), 

;
    private final String message;
    private int status;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}