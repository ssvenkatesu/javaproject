package main;

import main.database.CRUDOperations;
import main.database.DatabaseConnection;
import main.entities.Medication;
import main.entities.Prescription;
import main.utils.FileHelper;
import main.entities.Inventory;


import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class PharmacyApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseConnection dbConnection = new DatabaseConnection();
        CRUDOperations crudOperations = new CRUDOperations(dbConnection);

        while (true) {
            System.out.println("\nPharmacy Management System");
            System.out.println("1. Add Prescription");
            System.out.println("2. View Prescriptions");
            System.out.println("3. Add Medication");
            System.out.println("4. Update Inventory");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    // Add Prescription
                    System.out.print("Enter patient ID: ");
                    int patientId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter medication ID: ");
                    int medicationId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); 
                    String date = LocalDate.now().toString();

                    Prescription prescription = new Prescription(0, patientId, medicationId, quantity, date);
                    crudOperations.addPrescription(prescription);
                    System.out.println("Prescription added successfully.");
                    break;

                case 2:
                    // View Prescriptions
                	System.out.println("Prescriptions:");
                    List<Prescription> prescriptions = crudOperations.viewPrescriptions();
                    for (Prescription p : prescriptions) {
                        System.out.println(p.getPrescriptionDetails());
                    }

                    // Write prescriptions to file
                    FileHelper.writePrescriptionsToFile(prescriptions);
                    break;


                case 3:
                    // Add Medication
                    System.out.print("Enter medication name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); 

                    Medication medication = new Medication(0, name, price);
                    crudOperations.addMedication(medication);
                    System.out.println("Medication added successfully.");
                    break;

                case 4:
                    // Update Inventory
                    System.out.print("Enter medication ID: ");
                    int medIdForUpdate = scanner.nextInt();
                    System.out.print("Enter stock quantity to update: ");
                    int stockQuantity = scanner.nextInt();
                    scanner.nextLine();

                    Inventory inventory = new Inventory(medIdForUpdate, stockQuantity);
                    crudOperations.updateStock(inventory);
                    System.out.println("Inventory updated successfully.");
                    break;

                case 5:
                    System.out.println("Exiting the system.");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }
}
