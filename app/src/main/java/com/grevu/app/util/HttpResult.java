package com.grevu.app.util;

/**
 * Created by jason on 14. 11. 6..
 */
public class HttpResult {

    private int status;
    private String responseMsg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
