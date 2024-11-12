package main.entities;
public class Medication {

	private int medicationId;
    private String name;
    private double price;

    public Medication(int medicationId, String name, double price) {
        this.medicationId = medicationId;
        this.name = name;
        this.price = price;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
