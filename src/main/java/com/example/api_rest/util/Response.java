package com.example.api_rest.util;

import lombok.Data;

@Data
public class Response {
    private boolean status;
    private String details;

    public Response() {
        super();
        this.status = true;
    }

    public Response(boolean status, String details) {
        super();
        this.status = status;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Response [status=" + status + ", details=" + details + "]";
    }
}
