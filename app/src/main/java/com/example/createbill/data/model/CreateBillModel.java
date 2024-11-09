package com.example.createbill.data.model;

public class CreateBillModel {

    // Fields for each bill
    private double waterBill;
    private double gasBill;
    private double electricityBill;
    private double wifiBill;
    private double serviceFee;
    private double totalBill;

    // No-argument constructor
    public CreateBillModel() {
    }

    // Constructor with demo amounts for non-service fees
    public CreateBillModel(double waterBill, double gasBill, double electricityBill, double wifiBill, double serviceFee) {
        this.waterBill = waterBill;
        this.gasBill = gasBill;
        this.electricityBill = electricityBill;
        this.wifiBill = wifiBill;
        this.serviceFee = serviceFee;
        calculateTotalBill();
    }

    // Method to calculate the total bill by adding all individual amounts
    public void calculateTotalBill() {
        this.totalBill = waterBill + gasBill + electricityBill + wifiBill + serviceFee;
    }

    // Getter methods for each bill and the total amount
    public double getWaterBill() {
        return waterBill;
    }

    public double getGasBill() {
        return gasBill;
    }

    public double getElectricityBill() {
        return electricityBill;
    }

    public double getWifiBill() {
        return wifiBill;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setWaterBill(double waterBill) {
        this.waterBill = waterBill;
    }

    public void setGasBill(double gasBill) {
        this.gasBill = gasBill;
    }

    public void setElectricityBill(double electricityBill) {
        this.electricityBill = electricityBill;
    }

    public void setWifiBill(double wifiBill) {
        this.wifiBill = wifiBill;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }
}
