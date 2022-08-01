package com.misabitencourt.web.todolist.todolistreactive.exception;

public class ValidationError extends Exception {
    private String field;
    private String error;

    public static String ERROR_REQUIRED = "REQUIRED";

    public ValidationError(String error, String field) {
        this.field = field;
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
