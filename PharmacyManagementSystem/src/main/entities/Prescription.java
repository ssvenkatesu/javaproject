package main.entities;

public class Prescription {
    private int prescriptionId;
    private int patientId;
    private int medicationId;
    private int quantity;
    private String date;

    public Prescription(int prescriptionId, int patientId, int medicationId, int quantity, String date) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.date = date;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrescriptionDetails() {
        return "Prescription ID: " + prescriptionId + ", Patient ID: " + patientId + ", Medication ID: " + medicationId + ", Quantity: " + quantity + ", Date: " + date;
    }
}
