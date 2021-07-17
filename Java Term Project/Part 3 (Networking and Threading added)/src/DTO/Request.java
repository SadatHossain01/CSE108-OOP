package DTO;

import java.io.Serializable;

public class Request implements Serializable {
    private String from;
    public Request.Type requestType;

    public enum Type{
        UpdatedListQuery, LogOut
    }

    public Request(String from, Request.Type type) {
        this.from = from;
        this.requestType = type;
    }

    public String getFrom() {
        return from;
    }
}
