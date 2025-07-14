package com.openclassrooms.mddapi.dto;

public class MessageToReturn {
    private String message;

    public MessageToReturn(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
