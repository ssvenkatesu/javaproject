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
            System.out.println("5. View Medication by ID");
            System.out.println("6. View Inventory by Medication ID");
            System.out.println("7. Update Stock for Medication");
            System.out.println("8. Delete Prescription");
            System.out.println("9. Exit");
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
                    // View Medication by ID
                    System.out.print("Enter medication ID: ");
                    int medId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Medication med = crudOperations.getMedicationById(medId);
                    if (med != null) {
                        System.out.println("Medication Details:");
                        System.out.println("ID: " + med.getMedicationId());
                        System.out.println("Name: " + med.getName());
                        System.out.println("Price: " + med.getPrice());
                    } else {
                        System.out.println("Medication not found.");
                    }
                    break;

                case 6:
                    // View Inventory by Medication ID
                    System.out.print("Enter medication ID: ");
                    int invMedId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Inventory inv = crudOperations.getInventoryByMedicationId(invMedId);
                    if (inv != null) {
                        System.out.println("Inventory Details:");
                        System.out.println("Medication ID: " + inv.getMedicationId());
                        System.out.println("Stock Quantity: " + inv.getStockQuantity());
                    } else {
                        System.out.println("Inventory not found for the given medication ID.");
                    }
                    break;

                case 7:
                    // Update Stock for Medication
                    System.out.print("Enter medication ID: ");
                    int updateMedId = scanner.nextInt();
                    System.out.print("Enter new stock quantity: ");
                    int newStockQuantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Inventory updateInventory = new Inventory(updateMedId, newStockQuantity);
                    crudOperations.updateStock(updateInventory);
                    break;

                case 8:
                    // Delete Prescription
                    System.out.print("Enter prescription ID to delete: ");
                    int prescriptionId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    crudOperations.deletePrescription(prescriptionId);
                    break;

                case 9:
                    System.out.println("Exiting the system.");
                    return;
                

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }
}
