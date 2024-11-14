package models;

public class AppointmentType {
    private int typeId;
    private String name;
    private int durationMinutes;
    private double cost;

    public AppointmentType(int typeId, String name, int durationMinutes, double cost) {
        this.typeId = typeId;
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.cost = cost;
    }

    public int getTypeId() { return typeId; }
    public void setTypeId(int typeId) { this.typeId = typeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
}
