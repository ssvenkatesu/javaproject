package main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.entities.*;

public class CRUDOperations {

    private DatabaseConnection dbConnection;

    public CRUDOperations(DatabaseConnection dbk) {
    	 this.dbConnection=dbk;
    }


    public void addMedication(Medication medication) {
        try (Connection connection = dbConnection.getConnection()) {
            String query = "INSERT INTO medications (name, price) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, medication.getName());
            statement.setDouble(2, medication.getPrice());
            statement.executeUpdate();
            System.out.println("Medication added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding medication: " + e.getMessage());
        }
    }


    public void addPrescription(Prescription prescription) {
        try (Connection connection = dbConnection.getConnection()) {
            String query = "INSERT INTO prescriptions (patient_id, medication_id, quantity, date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, prescription.getPatientId());
            statement.setInt(2, prescription.getMedicationId());
            statement.setInt(3, prescription.getQuantity());
            statement.setString(4, prescription.getDate());
            statement.executeUpdate();
            System.out.println("Prescription added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding prescription: " + e.getMessage());
        }
    }


    public void updateInventory(int medicationId, int quantity) {
        try (Connection connection = dbConnection.getConnection()) {
            String query = "UPDATE inventory SET stock_quantity = stock_quantity + ? WHERE medication_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, quantity);
            statement.setInt(2, medicationId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Inventory updated successfully.");
            } else {
                System.out.println("Medication not found in inventory.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating inventory: " + e.getMessage());
        }
    }


    public Medication getMedicationById(int medicationId) {
        Medication medication = null;
        try (Connection connection = dbConnection.getConnection()) {
            String query = "SELECT * FROM medications WHERE medication_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, medicationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                medication = new Medication(medicationId, name, price);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving medication: " + e.getMessage());
        }
        return medication;
    }


    public Inventory getInventoryByMedicationId(int medicationId) {
        Inventory inventory = null;
        try (Connection connection = dbConnection.getConnection()) {
            String query = "SELECT * FROM inventory WHERE medication_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, medicationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int stockQuantity = resultSet.getInt("stock_quantity");
                inventory = new Inventory(medicationId, stockQuantity);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving inventory: " + e.getMessage());
        }
        return inventory;
    }


    public List<Prescription> viewPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>(); 

        try (Connection connection = dbConnection.getConnection()) {
            String query = "SELECT * FROM prescriptions";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int prescriptionId = resultSet.getInt("prescription_id");
                int patientId = resultSet.getInt("patient_id");
                int medicationId = resultSet.getInt("medication_id");
                int quantity = resultSet.getInt("quantity");
                String date = resultSet.getString("date");


                Prescription prescription = new Prescription(prescriptionId, patientId, medicationId, quantity, date);
                prescriptions.add(prescription); // Add each prescription to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prescriptions; 
    }


    public void deleteMedication(int medicationId) {
        try (Connection connection = dbConnection.getConnection()) {
            String query = "DELETE FROM medications WHERE medication_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, medicationId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Medication deleted successfully.");
            } else {
                System.out.println("Medication not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting medication: " + e.getMessage());
        }
    }


    public void deletePrescription(int prescriptionId) {
        try (Connection connection = dbConnection.getConnection()) {
            String query = "DELETE FROM prescriptions WHERE prescription_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, prescriptionId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Prescription deleted successfully.");
            } else {
                System.out.println("Prescription not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting prescription: " + e.getMessage());
        }
    }


    public void updateStock(Inventory inventory) {
        try (Connection connection = dbConnection.getConnection()) {
            String query = "UPDATE inventory SET stock_quantity = ? WHERE medication_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, inventory.getStockQuantity());
            statement.setInt(2, inventory.getMedicationId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Stock updated successfully.");
            } else {
                System.out.println("No medication found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating stock: " + e.getMessage());
        }
    }
}
