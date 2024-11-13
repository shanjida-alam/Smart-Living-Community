package com.example.createbill.data.model;

/**
 *
 * This class represents the bill details for a user in the smart apartment system.
 * It contains fields for water, gas, electricity, WiFi bills, and the service fee.
 * It also calculates the total bill by summing these individual bills.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 13-11-2024
 */
public class CreateBillModel {

    // Fields for each bill type
    private double waterBill;
    private double gasBill;
    private double electricityBill;
    private double wifiBill;
    private double serviceFee;
    private double totalBill;

    /**
     * No-argument constructor for CreateBillModel.
     * Initializes the object without any predefined bill amounts.
     */
    public CreateBillModel() {
    }

    /**
     * Constructor with predefined bill amounts. This constructor is typically used for testing
     * or initializing demo values.
     *
     * @param waterBill      The water bill amount.
     * @param gasBill        The gas bill amount.
     * @param electricityBill The electricity bill amount.
     * @param wifiBill       The Wi-Fi bill amount.
     * @param serviceFee     The service fee amount.
     */
    public CreateBillModel(double waterBill, double gasBill, double electricityBill, double wifiBill, double serviceFee) {
        this.waterBill = waterBill;
        this.gasBill = gasBill;
        this.electricityBill = electricityBill;
        this.wifiBill = wifiBill;
        this.serviceFee = serviceFee;
        calculateTotalBill(); // Automatically calculates the total bill when initialized.
    }

    /**
     * Calculates the total bill by summing water, gas, electricity, Wi-Fi, and service fee.
     * This method updates the totalBill field.
     */
    public void calculateTotalBill() {
        this.totalBill = waterBill + gasBill + electricityBill + wifiBill + serviceFee;
    }

    // Getter and Setter methods for each bill type and total bill

    /**
     * Gets the water bill amount.
     *
     * @return The water bill amount.
     */
    public double getWaterBill() {
        return waterBill;
    }

    /**
     * Sets the water bill amount.
     *
     * @param waterBill The water bill amount to set.
     */
    public void setWaterBill(double waterBill) {
        this.waterBill = waterBill;
    }

    /**
     * Gets the gas bill amount.
     *
     * @return The gas bill amount.
     */
    public double getGasBill() {
        return gasBill;
    }

    /**
     * Sets the gas bill amount.
     *
     * @param gasBill The gas bill amount to set.
     */
    public void setGasBill(double gasBill) {
        this.gasBill = gasBill;
    }

    /**
     * Gets the electricity bill amount.
     *
     * @return The electricity bill amount.
     */
    public double getElectricityBill() {
        return electricityBill;
    }

    /**
     * Sets the electricity bill amount.
     *
     * @param electricityBill The electricity bill amount to set.
     */
    public void setElectricityBill(double electricityBill) {
        this.electricityBill = electricityBill;
    }

    /**
     * Gets the Wi-Fi bill amount.
     *
     * @return The Wi-Fi bill amount.
     */
    public double getWifiBill() {
        return wifiBill;
    }

    /**
     * Sets the Wi-Fi bill amount.
     *
     * @param wifiBill The Wi-Fi bill amount to set.
     */
    public void setWifiBill(double wifiBill) {
        this.wifiBill = wifiBill;
    }

    /**
     * Gets the service fee amount.
     *
     * @return The service fee amount.
     */
    public double getServiceFee() {
        return serviceFee;
    }

    /**
     * Sets the service fee amount.
     *
     * @param serviceFee The service fee amount to set.
     */
    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    /**
     * Gets the total bill amount.
     *
     * @return The total bill amount.
     */
    public double getTotalBill() {
        return totalBill;
    }
}
