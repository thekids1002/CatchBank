package com.thekids1002.catchbank.Utils;

public enum HttpStatusCode {
    // Thêm các mã HTTP phổ biến ở đây
    CONTINUE(100, "Continue"),
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    NO_CONTENT(204, "No Content"),
    RESET_CONTENT(205, "Reset Content"),
    PARTIAL_CONTENT(206, "Partial Content"),
    // Thêm mã HTTP khác ở đây...
    ;

    private final int code;
    private final String message;

    HttpStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static HttpStatusCode getByCode(int code) {
        for (HttpStatusCode status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

}