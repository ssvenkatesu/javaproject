package main.exceptions;

public class MedicationNotFoundException extends Exception {
    public MedicationNotFoundException(String message) {
        super(message);
    }
}
