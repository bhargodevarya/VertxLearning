package com.model.db;

public class DBModel {

    private String value;

    public DBModel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
