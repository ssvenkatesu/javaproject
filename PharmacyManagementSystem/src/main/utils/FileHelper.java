package main.utils;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import main.entities.Prescription;

public class FileHelper {

    private static final String FILE_PATH = "prescriptions.txt";

    // Method to write prescription details to a file
    public static void writePrescriptionsToFile(List<Prescription> prescriptions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Prescription p : prescriptions) {
                writer.write(p.getPrescriptionDetails()); // Write prescription details
                writer.newLine(); // Add a new line after each prescription
            }
            System.out.println("Prescription details successfully written to " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
