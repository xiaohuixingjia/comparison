package com.it.comparison.test.model;

public class InnerTestModel {
    private int id;
    private String nn;

    public InnerTestModel(int id, String nn) {
        this.id = id;
        this.nn = nn;
    }

    public InnerTestModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNn() {
        return nn;
    }

    public void setNn(String nn) {
        this.nn = nn;
    }
}
