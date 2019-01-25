package com.rutgeruijtendaal.exceptions;

public class DropwizardException extends Throwable {

    private int code;

    public DropwizardException() {
        this(500);
    }

    public DropwizardException(int code) {
        this(code, "Error while processing the request", null);
    }
    public DropwizardException(int code, String message) {
        this(code, message, null);
    }
    public DropwizardException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
