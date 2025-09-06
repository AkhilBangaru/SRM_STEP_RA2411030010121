// File: Assignment_4_akhil.java
// Author: Bangaru Chaitanya Venkata Sai Akhil

import java.util.*;

class Subject {
    String subjectCode;
    String subjectName;
    int credits;
    String instructor;

    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }
}

class Student {
    String studentId;
    String studentName;
    String className;
    String[] subjects;
    double[] marks;  // parallel to subjects
    double gpa;

    // Static members
    static int totalStudents = 0;
    static String schoolName = "OpenAI High School";
    static String[] gradingScale = {"A: 90-100", "B: 75-89", "C: 60-74", "D: 40-59", "F: <40"};
    static double passPercentage = 40.0;

    public Student(String studentId, String studentName, String className, String[] subjects) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.subjects = subjects;
        this.marks = new double[subjects.length];
        this.gpa = 0.0;
        totalStudents++;
    }

    // Add marks for a subject
    public void addMarks(String subject, double mark) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equalsIgnoreCase(subject)) {
                marks[i] = mark;
                return;
            }
        }
        System.out.println("Subject not found for " + studentName);
    }

    // Calculate GPA (simple avg/10 for demonstration)
    public void calculateGPA() {
        double total = 0;
        for (double m : marks) total += m;
        double percentage = total / subjects.length;
        this.gpa = percentage / 10.0;  // scale to 0–10
    }

    // Generate report card
    public void generateReportCard() {
        System.out.println("\n--- Report Card ---");
        System.out.println("School: " + schoolName);
        System.out.println("Student: " + studentName + " (" + studentId + ")");
        System.out.println("Class: " + className);
        System.out.println("Subjects & Marks:");
        double total = 0;
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + ": " + marks[i]);
            total += marks[i];
        }
        double percentage = total / subjects.length;
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Grade: " + getGrade(percentage));
        System.out.println("GPA: " + gpa);
        System.out.println("Promotion Eligible: " + checkPromotionEligibility());
    }

    // Check promotion eligibility
    public boolean checkPromotionEligibility() {
        double total = 0;
        for (double m : marks) total += m;
        double percentage = total / subjects.length;
        return percentage >= passPercentage;
    }

    // Grade categorization
    public static String getGrade(double percentage) {
        if (percentage >= 90) return "A";
        else if (percentage >= 75) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 40) return "D";
        else return "F";
    }

    // Static methods
    public static void setGradingScale(String[] newScale) {
        gradingScale = newScale;
    }

    public static double calculateClassAverage(Student[] students, String className) {
        double total = 0;
        int count = 0;
        for (Student s : students) {
            if (s != null && s.className.equalsIgnoreCase(className)) {
                double sum = 0;
                for (double m : s.marks) sum += m;
                total += (sum / s.subjects.length);
                count++;
            }
        }
        return count == 0 ? 0 : total / count;
    }

    public static Student[] getTopPerformers(Student[] students, int count) {
        Arrays.sort(students, (a, b) -> Double.compare(b.gpa, a.gpa));
        return Arrays.copyOfRange(students, 0, Math.min(count, students.length));
    }

    public static void generateSchoolReport(Student[] students) {
        System.out.println("\n=== School Report (" + schoolName + ") ===");
        System.out.println("Total Students: " + totalStudents);

        // Class-wise average
        Set<String> classes = new HashSet<>();
        for (Student s : students) {
            if (s != null) classes.add(s.className);
        }
        for (String c : classes) {
            double avg = calculateClassAverage(students, c);
            System.out.println("Class " + c + " Average: " + avg + "%");
        }

        // Top performer
        Student[] tops = getTopPerformers(students, 1);
        if (tops.length > 0)
            System.out.println("Top Performer: " + tops[0].studentName + " (GPA: " + tops[0].gpa + ")");
    }
}

public class Assignment_4_akhil {
    public static void main(String[] args) {
        // Subjects
        String[] subjects = {"Math", "Science", "English"};

        // Create students
        Student s1 = new Student("S001", "Akhil", "10A", subjects);
        Student s2 = new Student("S002", "Ravi", "10A", subjects);
        Student s3 = new Student("S003", "Priya", "10B", subjects);

        // Add marks
        s1.addMarks("Math", 95);
        s1.addMarks("Science", 88);
        s1.addMarks("English", 92);

        s2.addMarks("Math", 70);
        s2.addMarks("Science", 65);
        s2.addMarks("English", 72);

        s3.addMarks("Math", 45);
        s3.addMarks("Science", 55);
        s3.addMarks("English", 60);

        // Calculate GPAs
        s1.calculateGPA();
        s2.calculateGPA();
        s3.calculateGPA();

        // Report cards
        s1.generateReportCard();
        s2.generateReportCard();
        s3.generateReportCard();

        // School Report
        Student[] allStudents = {s1, s2, s3};
        Student.generateSchoolReport(allStudents);
    }
}
