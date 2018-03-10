package com.example.nathapong.app64_recievefirebasechildrendata;


public class Boxer {

    private String boxerName;
    private int boxerPunchPower;
    private int boxerPunchSpeed;


    public Boxer(){}

    public Boxer(String boxerName, int boxerPunchPower, int boxerPunchSpeed) {
        this.boxerName = boxerName;
        this.boxerPunchPower = boxerPunchPower;
        this.boxerPunchSpeed = boxerPunchSpeed;
    }

    public String getBoxerName() {
        return boxerName;
    }

    public int getBoxerPunchPower() {
        return boxerPunchPower;
    }

    public int getBoxerPunchSpeed() {
        return boxerPunchSpeed;
    }
}
