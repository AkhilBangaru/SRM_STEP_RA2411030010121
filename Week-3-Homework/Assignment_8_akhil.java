// File: Assignment_8_akhil.java
// Author: Bangaru Chaitanya Venkata Sai Akhil

import java.util.*;

class Patient {
    String patientId;
    String patientName;
    int age;
    String gender;
    String contactInfo;
    ArrayList<String> medicalHistory;
    ArrayList<String> currentTreatments;
    boolean discharged;

    // Static variable
    static int totalPatients = 0;

    public Patient(String patientId, String patientName, int age, String gender, String contactInfo) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.medicalHistory = new ArrayList<>();
        this.currentTreatments = new ArrayList<>();
        this.discharged = false;
        totalPatients++;
    }

    public void updateTreatment(String treatment) {
        currentTreatments.add(treatment);
        medicalHistory.add("Treatment: " + treatment);
        System.out.println("✅ Treatment added for " + patientName + ": " + treatment);
    }

    public void dischargePatient() {
        this.discharged = true;
        System.out.println("🏥 Patient " + patientName + " discharged.");
    }
}

class Doctor {
    String doctorId;
    String doctorName;
    String specialization;
    ArrayList<String> availableSlots;
    int patientsHandled;
    double consultationFee;

    public Doctor(String doctorId, String doctorName, String specialization, double consultationFee) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
        this.availableSlots = new ArrayList<>(Arrays.asList("10:00 AM", "11:00 AM", "2:00 PM", "4:00 PM"));
        this.patientsHandled = 0;
    }

    public boolean bookSlot(String time) {
        if (availableSlots.contains(time)) {
            availableSlots.remove(time);
            return true;
        }
        return false;
    }
}

class Appointment {
    String appointmentId;
    Patient patient;
    Doctor doctor;
    String appointmentDate;
    String appointmentTime;
    String status; // Scheduled, Cancelled, Completed
    String type;   // Consultation, Follow-up, Emergency

    // Static variables
    static int totalAppointments = 0;
    static String hospitalName = "OpenAI Super Specialty Hospital";
    static double totalRevenue = 0;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String appointmentDate, String appointmentTime, String type) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = "Scheduled";
        this.type = type;
        totalAppointments++;
    }

    public void cancelAppointment() {
        this.status = "Cancelled";
        System.out.println("❌ Appointment " + appointmentId + " cancelled.");
    }

    public double generateBill() {
        double bill = doctor.consultationFee;
        switch (type) {
            case "Consultation": bill += 0; break;
            case "Follow-up": bill *= 0.5; break;
            case "Emergency": bill *= 2; break;
        }
        totalRevenue += bill;
        System.out.println("💵 Bill for Appointment " + appointmentId + ": " + bill);
        return bill;
    }

    // Schedule appointment if slot available
    public static Appointment scheduleAppointment(String appointmentId, Patient p, Doctor d, String date, String time, String type) {
        if (d.bookSlot(time)) {
            Appointment appt = new Appointment(appointmentId, p, d, date, time, type);
            d.patientsHandled++;
            System.out.println("📅 Appointment Scheduled: " + appointmentId + " for " + p.patientName + " with Dr." + d.doctorName);
            return appt;
        } else {
            System.out.println("⚠ Slot not available with Dr." + d.doctorName + " at " + time);
            return null;
        }
    }

    // Static Reports
    public static void generateHospitalReport() {
        System.out.println("\n=== Hospital Report (" + hospitalName + ") ===");
        System.out.println("Total Patients: " + Patient.totalPatients);
        System.out.println("Total Appointments: " + totalAppointments);
        System.out.println("Total Revenue: " + totalRevenue);
    }

    public static void getDoctorUtilization(Doctor[] doctors) {
        System.out.println("\n=== Doctor Utilization Report ===");
        for (Doctor d : doctors) {
            System.out.println("Dr." + d.doctorName + " (" + d.specialization + ") handled " + d.patientsHandled + " patients.");
        }
    }

    public static void getPatientStatistics(Patient[] patients) {
        int discharged = 0, ongoing = 0;
        for (Patient p : patients) {
            if (p.discharged) discharged++;
            else ongoing++;
        }
        System.out.println("\n=== Patient Statistics ===");
        System.out.println("Ongoing Patients: " + ongoing);
        System.out.println("Discharged Patients: " + discharged);
    }
}

public class Assignment_8_akhil {
    public static void main(String[] args) {
        // Create doctors
        Doctor d1 = new Doctor("D001", "Ramesh", "Cardiologist", 1000);
        Doctor d2 = new Doctor("D002", "Suresh", "Neurologist", 1200);

        Doctor[] doctors = {d1, d2};

        // Create patients
        Patient p1 = new Patient("P001", "Akhil", 22, "Male", "9876543210");
        Patient p2 = new Patient("P002", "Meera", 30, "Female", "9123456780");
        Patient[] patients = {p1, p2};

        // Schedule appointments
        Appointment a1 = Appointment.scheduleAppointment("A001", p1, d1, "2025-09-06", "10:00 AM", "Consultation");
        Appointment a2 = Appointment.scheduleAppointment("A002", p2, d2, "2025-09-06", "11:00 AM", "Emergency");

        // Update treatments
        p1.updateTreatment("Blood Pressure Check");
        p2.updateTreatment("MRI Scan");

        // Generate bills
        if (a1 != null) a1.generateBill();
        if (a2 != null) a2.generateBill();

        // Discharge patient
        p1.dischargePatient();

        // Reports
        Appointment.generateHospitalReport();
        Appointment.getDoctorUtilization(doctors);
        Appointment.getPatientStatistics(patients);
    }
}
