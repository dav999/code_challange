package com.dawid.response;

public class ResponseMessage {

    private boolean success;
    private String message;

    public ResponseMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseMessage(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
