package com.bowling.demo.model;

public class Response<Optional> {

    private Optional message;

    public Response(Optional message) {
        this.message = message;
    }

    public Optional getMessage() {
        return message;
    }

    public void setMessage(Optional message) {
        this.message = message;
    }

}
