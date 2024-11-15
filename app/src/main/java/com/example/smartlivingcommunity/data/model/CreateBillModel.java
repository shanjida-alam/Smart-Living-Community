package com.example.smartlivingcommunity.data.model;

public class CreateBillModel {

    private double waterBill;
    private double gasBill;
    private double electricityBill;
    private double wifiBill;
    private double serviceFee;
    private double totalBill;

    public CreateBillModel(double waterBill, double gasBill, double electricityBill, double wifiBill, double serviceFee, double totalBill) {
        this.waterBill = waterBill;
        this.gasBill = gasBill;
        this.electricityBill = electricityBill;
        this.wifiBill = wifiBill;
        this.serviceFee = serviceFee;
        this.totalBill = totalBill;
    }

    public double getWaterBill() {
        return waterBill;
    }

    public void setWaterBill(double waterBill) {
        this.waterBill = waterBill;
    }

    public double getGasBill() {
        return gasBill;
    }

    public void setGasBill(double gasBill) {
        this.gasBill = gasBill;
    }

    public double getElectricityBill() {
        return electricityBill;
    }

    public void setElectricityBill(double electricityBill) {
        this.electricityBill = electricityBill;
    }

    public double getWifiBill() {
        return wifiBill;
    }

    public void setWifiBill(double wifiBill) {
        this.wifiBill = wifiBill;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }
}

