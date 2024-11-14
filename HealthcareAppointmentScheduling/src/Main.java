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
	   static boolean adminloggedin=false;
	   private static Connection connection;
	   
    public static void main(String[] args) {
    	try {
			 connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","sankara@2718");
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
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
			

		
    }
    
    private static void adminActions() {
    	
        try (Connection connection = DatabaseConnection.getConnection()) {
            appointmentService = new AppointmentService(connection);
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            
            // Retrieve all appointments
            
            boolean running = true;
            Scanner scanner = new Scanner(System.in);

            while (running) {
            	System.out.println("Welcome to the Healthcare Appointment Scheduling System");
               
                System.out.println("1. Book an appointment");
                System.out.println("2. Cancel an appointment");
                System.out.println("3. Reschedule an appointment");
                System.out.println("4. View all appointments");
                System.out.println("5. Exit");
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
    


    

}
