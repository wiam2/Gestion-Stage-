package com.retaiontest.relationtest.EXception;

import org.hibernate.exception.DataException;

import java.sql.SQLException;

public class DataInvalid extends Exception {
    public DataInvalid(String message) {
        super(message);
    }
}
