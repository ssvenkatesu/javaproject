package main.entities;

public class Inventory {
    private int medicationId;
    private int stockQuantity;

    public Inventory(int medicationId, int stockQuantity) {
        this.medicationId = medicationId;
        this.stockQuantity = stockQuantity;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getInventoryData() {
        return "Medication ID: " + medicationId + ", Stock Quantity: " + stockQuantity;
    }
}
