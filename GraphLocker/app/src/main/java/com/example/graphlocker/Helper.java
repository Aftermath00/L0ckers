package com.example.graphlocker;

public class Helper {
    String otpNumber, cipherText;

    public Helper(String otpNumber, String cipherText){
        this.otpNumber = otpNumber;
        this.cipherText = cipherText;
    }

    public String getOtpNumber() {
        return otpNumber;
    }

    public void setOtpNumber(String otpNumber) {
        this.otpNumber = otpNumber;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public Helper(){

    }
}
