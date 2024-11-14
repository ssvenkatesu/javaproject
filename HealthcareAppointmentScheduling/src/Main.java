import services.AppointmentService;
import services.DatabaseConnection;
import models.Appointment;
import models.AppointmentDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
	   private static AppointmentService appointmentService;
	   static boolean adminloggedin=false,patientLoggedin=false,doctorloggedin=false;
	   private static Connection connection;
   	Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
    	try {
			 connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","sankara@2718");
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
   
    	System.out.println("\nWelcome to the Healthcare Appointment Scheduling System\n");
    	System.out.println("1.Admin Login\n2.patient Login\n3.doctor login");
    	System.out.println("\nEnter the choice");
    	Scanner sc=new Scanner (System.in);
    	int choice=sc.nextInt();
switch(choice) {
case 1:
	System.out.println("Enter the admin details to login");
	try (Scanner scanner = new Scanner(System.in)) {
		System.out.println("Enter the admin username:");
		String username=scanner.next();
		System.out.println("Enter the admin password:");
		String password=scanner.next();
		
        String query="select * from admin where username= ? and password= ?";
		
		try(PreparedStatement preparedStatement =connection.prepareStatement(query)){
			preparedStatement.setString(1,username);
			preparedStatement.setString(2, password);
			
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset.next()) {
				System.out.println("Admin login successful");

				adminloggedin=true;
				while(adminloggedin) {
					adminActions();
				}

			}else {
			    adminloggedin=false;
				System.out.println("Invalid admin details or credentials");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	break;
case 2:
	System.out.println("Enter the Patient details to login");
	try (Scanner scanner = new Scanner(System.in)) {
		System.out.println("Enter the patient_id:");
		int id=scanner.nextInt();

		
        String query="select * from Patients where patient_id= ?";
		
		try(PreparedStatement preparedStatement =connection.prepareStatement(query)){
			preparedStatement.setInt(1,id);
		
			
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset.next()) {
				System.out.println("Patient login successful");

				patientLoggedin=true;
				while(patientLoggedin) {
					patientActions();
				}

			}else {
				patientLoggedin=false;
				System.out.println("Invalid patient details or credentials");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
			
	}
case 3:
	System.out.println("Enter the Doctor details to login");
	try (Scanner scanner = new Scanner(System.in)) {
		System.out.println("Enter the Doctor username:");
		int id=scanner.nextInt();

		
        String query="select * from Doctors where doctor_id= ?";
		
		try(PreparedStatement preparedStatement =connection.prepareStatement(query)){
			preparedStatement.setInt(1,id);
	
			
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset.next()) { 
				System.out.println("Doctor login successful");

				doctorloggedin=true;
				while(doctorloggedin) {
					doctorActions();
				}

			}else {
			    adminloggedin=false;
				System.out.println("Invalid admin details or credentials");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	break;
default:
	System.out.println("Enter the valid choice!");
	break;
}


		
			

		
    }
    
    
 private static void patientActions() {
    	
        try (Connection connection = DatabaseConnection.getConnection()) {
            appointmentService = new AppointmentService(connection);
            
            
            // Retrieve all appointments
            
            boolean running = true;
            Scanner scanner = new Scanner(System.in);

            while (running) {
            	System.out.println();
               
                System.out.println("1. Book an appointment");
                System.out.println("2. Cancel an appointment");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        bookAppointment(scanner);
                        break;
                    case 2:
                        cancelAppointment(scanner);
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void adminActions() {
    	
        try (Connection connection = DatabaseConnection.getConnection()) {
            appointmentService = new AppointmentService(connection);
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            
            // Retrieve all appointments
            
            boolean running = true;
            Scanner scanner = new Scanner(System.in);

            while (running) {
            	System.out.println();
               
                System.out.println("1. Book an appointment");
                System.out.println("2. Cancel an appointment");
                System.out.println("3. Reschedule an appointment");
                System.out.println("4. View all appointments");
                System.out.println("5. Addpatient");
                System.out.println("6. RemovePatient");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        bookAppointment(scanner);
                        break;
                    case 2:
                        cancelAppointment(scanner);
                        break;
                    case 3:
                        rescheduleAppointment(scanner);
                        break;
                    case 4:
                    	viewAppointments(appointmentDAO);
                        break;
                    case 5:
                    	addPatient();
                    	break;
                    case 6:
                    	removePatient();
                    	break;
                    case 7:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
private static void doctorActions() {
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            appointmentService = new AppointmentService(connection);
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            
            // Retrieve all appointments
            
            boolean running = true;
            Scanner scanner = new Scanner(System.in);

            while (running) {
            	System.out.println();
               
               
                System.out.println("1. Reschedule an appointment");
                System.out.println("2. View all appointments");
                System.out.println("3. UpdateAppointmentStatus");
                System.out.println("4. Check Status Of all Appointments");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                

                int choice = scanner.nextInt();

                switch (choice) {
                    
                    case 1:
                        rescheduleAppointment(scanner);
                        break;
                    case 2:
                    	viewAppointments(appointmentDAO);
                        break;
                    case 3:
                    	appointmentService.updateAppointmentStatus();
                    	break;
                    
                    case 4:
                    	appointmentService.checkAppointmentStatus();
                        break;
                    case 5:
                    	 running = false;
                         break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void viewAppointments(AppointmentDAO appointmentDAO) {
		// TODO Auto-generated method stub
    	 List<Appointment> appointments = appointmentDAO.getAllAppointments();
         
         // Display all appointments
         for (Appointment appointment : appointments) {
             System.out.println(appointment);
         }
         
         appointmentDAO.writeAppointmentsToFile(appointments);
		
	}

    private static void bookAppointment(Scanner scanner) {
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Appointment Date (YYYY-MM-DDTHH:MM): ");
        LocalDateTime appointmentDate = LocalDateTime.parse(scanner.next());
        System.out.print("Enter Appointment Type: ");
        String appointmentType = scanner.next();
        System.out.print("Enter Notes: ");
        String notes = scanner.next();

        Appointment appointment = new Appointment(0, doctorId, patientId, appointmentDate, appointmentType, "Scheduled", notes);
        appointmentService.bookAppointment(appointment);
    }

    private static void cancelAppointment(Scanner scanner) {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        appointmentService.cancelAppointment(appointmentId);
    }

    private static void rescheduleAppointment(Scanner scanner) {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        System.out.print("Enter New Appointment Date (YYYY-MM-DDTHH:MM): ");
        String newDate = scanner.next();
        appointmentService.rescheduleAppointment(appointmentId, newDate);
    }
    


    public static void addPatient() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("\nEnter Patient Details:");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();

        String query = "INSERT INTO Patients (name, email, phone, address, dob) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, dob); // Assuming the date is in 'yyyy-mm-dd' format

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient added successfully.");

            } else {
                System.out.println("Failed to add patient.");
   
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public static void removePatient() {
    	Scanner scanner=new Scanner(System.in);
        System.out.print("Enter Patient ID to remove: ");
        int patientId = scanner.nextInt();

        String query = "DELETE FROM Patients WHERE patient_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, patientId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient removed successfully.");
                
            } else {
                System.out.println("Failed to remove patient.");
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

}
