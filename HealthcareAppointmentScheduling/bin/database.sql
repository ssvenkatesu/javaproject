CREATE DATABASE IF NOT EXISTS healthcare;

USE healthcare;

CREATE TABLE IF NOT EXISTS Doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    available_hours VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    address VARCHAR(255),
    dob DATE
);

CREATE TABLE IF NOT EXISTS AppointmentTypes (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    duration_minutes INT,
    cost DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS Appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    doctor_id INT,
    patient_id INT,
    appointment_date DATETIME,
    appointment_type VARCHAR(50),
    status VARCHAR(50),
    notes TEXT,
    FOREIGN KEY (doctor_id) REFERENCES Doctors(doctor_id),
    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id)
);

CREATE TABLE IF NOT EXISTS Reschedules (
    reschedule_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id INT,
    old_date DATETIME,
    new_date DATETIME,
    reason VARCHAR(255),
    reschedule_date DATETIME,
    FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id)
);

CREATE TABLE IF NOT EXISTS CancelledAppointments (
    cancellation_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id INT,
    cancellation_date DATETIME,
    reason VARCHAR(255),
    FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id)
);

show tables;

desc patients;


INSERT INTO Doctors (doctor_id, name, specialization, email, phone, available_hours)
VALUES (1, 'Dr. Siva', 'Cardiologist', 'Siva@example.com', '123-456-7890', '9:00 AM - 5:00 PM');

INSERT INTO Doctors (doctor_id, name, specialization, email, phone, available_hours)
VALUES (2, 'Dr. Sri', 'Dermatologist', 'sri@example.com', '234-567-8901', '10:00 AM - 6:00 PM');

INSERT INTO Doctors (doctor_id, name, specialization, email, phone, available_hours)
VALUES (3, 'Dr. Venkatesu', 'ChildCare', 'Venkatesu@example.com', '888-888-8888', '6:00 AM - 6:00 PM');

INSERT INTO Patients (patient_id, name, email, phone, address, dob)
VALUES (1, 'Srinivas', 'Srinivas@example.com', '345-678-9012', '123 Maple St', '1990-05-20');

INSERT INTO Patients (patient_id, name, email, phone, address, dob)
VALUES (2, 'Williams', 'bobw@example.com', '456-789-0123', '456 Oak St', '1985-11-15');

INSERT INTO AppointmentTypes (type_id, name, duration_minutes, cost)
VALUES (1, 'Consultation', 30, 100.00);

INSERT INTO AppointmentTypes (type_id, name, duration_minutes, cost)
VALUES (2, 'Surgery', 120, 1500.00);

INSERT INTO Appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_type, status, notes)
VALUES (1, 1, 1, '2024-11-15T10:30', 'Consultation', 'Scheduled', 'Follow-up on heart condition');

INSERT INTO Appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_type, status, notes)
VALUES (2, 2, 2, '2024-11-15T11:00', 'Consultation', 'Scheduled', 'Skin rash consultation');

INSERT INTO Reschedules (reschedule_id, appointment_id, old_date, new_date, reason, reschedule_date)
VALUES (1, 1, '2024-11-15T10:30', '2024-11-16T10:30', 'Doctor not available', '2024-11-14T09:00');

INSERT INTO CancelledAppointments (cancellation_id, appointment_id, cancellation_date, reason)
VALUES (1, 2, '2024-11-14T15:00', 'Patient requested cancellation');

ALTER TABLE Reschedules
DROP FOREIGN KEY reschedules_ibfk_1;

ALTER TABLE Reschedules
ADD CONSTRAINT reschedules_ibfk_1
FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id)
ON DELETE CASCADE;

