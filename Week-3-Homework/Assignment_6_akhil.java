// File: Assignment_6_akhil.java
// Author: Bangaru Chaitanya Venkata Sai Akhil

import java.util.*;

abstract class Employee {
    String empId;
    String empName;
    String department;
    String designation;
    double baseSalary;
    String joinDate;
    boolean[] attendanceRecord; // 30 days
    int leavesTaken = 0;

    // Static variables
    static int totalEmployees = 0;
    static String companyName = "OpenAI Tech Solutions";
    static double totalSalaryExpense = 0.0;
    static int workingDaysPerMonth = 22;

    public Employee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
        this.attendanceRecord = new boolean[30];
        totalEmployees++;
    }

    // Mark attendance
    public void markAttendance(int day, boolean present) {
        if (day < 1 || day > 30) {
            System.out.println("❌ Invalid day!");
            return;
        }
        attendanceRecord[day - 1] = present;
    }

    // Count presents
    public int getPresentDays() {
        int count = 0;
        for (boolean present : attendanceRecord) {
            if (present) count++;
        }
        return count;
    }

    // Request leave
    public void requestLeave(int days) {
        leavesTaken += days;
        System.out.println("📌 Leave approved for " + empName + " | Days: " + days);
    }

    // Abstract methods for salary calculation
    public abstract double calculateSalary();
    public abstract double calculateBonus();

    // Pay slip
    public void generatePaySlip() {
        double salary = calculateSalary();
        double bonus = calculateBonus();
        totalSalaryExpense += (salary + bonus);

        System.out.println("\n=== Pay Slip ===");
        System.out.println("Company: " + companyName);
        System.out.println("Employee: " + empName + " (" + empId + ")");
        System.out.println("Department: " + department);
        System.out.println("Designation: " + designation);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Attendance Days: " + getPresentDays());
        System.out.println("Leaves Taken: " + leavesTaken);
        System.out.println("Final Salary: " + salary);
        System.out.println("Bonus: " + bonus);
        System.out.println("Net Pay: " + (salary + bonus));
    }

    // Static methods
    public static double calculateCompanyPayroll(Employee[] employees) {
        double total = 0;
        for (Employee e : employees) {
            if (e != null) total += e.calculateSalary() + e.calculateBonus();
        }
        return total;
    }

    public static void getAttendanceReport(Employee[] employees) {
        System.out.println("\n=== Attendance Report ===");
        for (Employee e : employees) {
            if (e != null) {
                System.out.println(e.empName + ": " + e.getPresentDays() + " days present, Leaves: " + e.leavesTaken);
            }
        }
    }
}

// Full-time employee
class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }

    @Override
    public double calculateSalary() {
        double dailyRate = baseSalary / Employee.workingDaysPerMonth;
        return dailyRate * getPresentDays();
    }

    @Override
    public double calculateBonus() {
        return (getPresentDays() >= Employee.workingDaysPerMonth - 2) ? baseSalary * 0.1 : baseSalary * 0.05;
    }
}

// Part-time employee
class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }

    @Override
    public double calculateSalary() {
        double hourlyRate = baseSalary / 100; // baseSalary represents total possible earnings
        return hourlyRate * (getPresentDays() * 4); // assume 4 hrs per day
    }

    @Override
    public double calculateBonus() {
        return baseSalary * 0.02; // minimal bonus
    }
}

// Contract employee
class ContractEmployee extends Employee {
    public ContractEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }

    @Override
    public double calculateSalary() {
        return baseSalary; // fixed
    }

    @Override
    public double calculateBonus() {
        return 0; // no bonus
    }
}

// Department class
class Department {
    String deptId;
    String deptName;
    Employee manager;
    Employee[] employees;
    double budget;

    public Department(String deptId, String deptName, Employee manager, double budget, int capacity) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.manager = manager;
        this.budget = budget;
        this.employees = new Employee[capacity];
    }

    public void addEmployee(Employee e) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = e;
                return;
            }
        }
    }

    public static void getDepartmentWiseExpenses(Department[] departments) {
        System.out.println("\n=== Department Wise Expenses ===");
        for (Department d : departments) {
            double total = 0;
            for (Employee e : d.employees) {
                if (e != null) {
                    total += e.calculateSalary() + e.calculateBonus();
                }
            }
            System.out.println(d.deptName + ": " + total);
        }
    }
}

public class Assignment_6_akhil {
    public static void main(String[] args) {
        // Create Employees
        Employee e1 = new FullTimeEmployee("E001", "Akhil", "IT", "Developer", 60000, "2022-01-01");
        Employee e2 = new PartTimeEmployee("E002", "Ravi", "IT", "Support", 20000, "2023-02-01");
        Employee e3 = new ContractEmployee("E003", "Priya", "HR", "Consultant", 30000, "2024-03-01");

        // Mark attendance
        for (int i = 1; i <= 20; i++) {
            e1.markAttendance(i, true);
            e2.markAttendance(i, i % 2 == 0); // present half the days
            e3.markAttendance(i, true);
        }

        // Request leave
        e1.requestLeave(2);

        // Departments
        Department d1 = new Department("D001", "IT", e1, 200000, 5);
        d1.addEmployee(e1);
        d1.addEmployee(e2);

        Department d2 = new Department("D002", "HR", e3, 150000, 3);
        d2.addEmployee(e3);

        // Generate payslips
        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        // Reports
        Employee[] allEmployees = {e1, e2, e3};
        Department[] allDepartments = {d1, d2};

        System.out.println("\nTotal Company Payroll: " + Employee.calculateCompanyPayroll(allEmployees));
        Department.getDepartmentWiseExpenses(allDepartments);
        Employee.getAttendanceReport(allEmployees);
    }
}
