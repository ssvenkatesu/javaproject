package main.entities;

public class Patient {
    private int patientId;
    private String name;
    private String address;
    private String phoneNumber;

    public Patient(int patientId, String name, String address, String phoneNumber) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
