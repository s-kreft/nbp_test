package com.example.nbp_test.model;

public class AverageRate {
    private String currency;
    private int days;
    private double value;

    public AverageRate(String currency, int days, double value) {
        this.currency = currency;
        this.days = days;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
