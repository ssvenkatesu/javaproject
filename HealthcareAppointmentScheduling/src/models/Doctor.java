package models;

public class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private String email;
    private String phone;
    private String availableHours;

    public Doctor(int doctorId, String name, String specialization, String email, String phone, String availableHours) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.availableHours = availableHours;
    }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAvailableHours() { return availableHours; }
    public void setAvailableHours(String availableHours) { this.availableHours = availableHours; }
}
