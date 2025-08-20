package com.retaiontest.relationtest.EXception;

import java.net.ConnectException;

public class ConnectBdException extends ConnectException {
    public ConnectBdException(String msg) {
        super(msg);
    }
}
