package Project;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandHandle {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void show() {
        System.out.println("       Payroll Management System");
        System.out.println("Press 1 - Add Employee");
        System.out.println("Press 2 - Remove Employee");
        System.out.println("Press 3 - List Of Employee");
        System.out.println("Press 4 - Payroll Details");
        System.out.println("Press 5 - Make Payroll (Mark salary as paid)");
        System.out.println("Press 6 - End");
    }

    public static Command getInput() {
        while (true) {
            try {
                System.out.print("Enter your command (1-6): ");
                String line = SCANNER.nextLine().trim();
                int choice = Integer.parseInt(line);

                switch (choice) {
                    case 1: return Command.ADD;
                    case 2: return Command.REMOVE;
                    case 3: return Command.LISTOFEMPLOYEE;
                    case 4: return Command.PAYROLLDETAILS;
                    case 5: return Command.PAY;
                    case 6: return Command.END;
                    default:
                        System.out.println("Invalid option. Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a numeric value between 1 and 6.");
            } catch (NoSuchElementException ex) {
                System.out.println("Input stream closed unexpectedly. Exiting.");
                System.exit(1);
            }
        }
    }

    // Helper to read integer safely
    private static int readInt(String fieldName) {
        while (true) {
            try {
                String line = SCANNER.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.print("Invalid " + fieldName + ". Please enter a valid integer: ");
            }
        }
    }

    public static void addEmployee() {
        try {
            System.out.print("Enter employee id: ");
            int id = readInt("id");

            System.out.print("Enter employee name: ");
            String name = SCANNER.nextLine().trim();
            while (name.isEmpty()) {
                System.out.print("Name cannot be empty. Enter employee name: ");
                name = SCANNER.nextLine().trim();
            }

            System.out.print("Enter employee age: ");
            int age = readInt("age");

            System.out.print("Enter employee phone number: ");
            String phone = SCANNER.nextLine().trim();
            while (phone.isEmpty()) {
                System.out.print("Phone number cannot be empty. Enter phone number: ");
                phone = SCANNER.nextLine().trim();
            }

            System.out.print("Enter employee salary: ");
            int salary = readInt("salary");

            int[] paidMonths = new int[12]; // all 0 initially

            Employee e = new Employee(id, name, age, phone, salary, paidMonths);

            boolean added = EmployeeDataHandle.addEmployee(e);
            if (added) {
                System.out.println("Employee added successfully.");
            }

        } catch (Exception ex) {
            System.out.println("Error while adding employee: " + ex.getMessage());
        }
    }

    public static void removeEmployee() {
        try {
            System.out.print("Enter employee id to remove: ");
            int id = readInt("id");
            EmployeeDataHandle.removeEmployee(id);
        } catch (Exception ex) {
            System.out.println("Error while removing employee: " + ex.getMessage());
        }
    }

    public static void payrollDetails() {
        try {
            System.out.print("Enter employee id for payroll details: ");
            int id = readInt("id");
            EmployeeDataHandle.payrollDetailsFromDatabase(id);
        } catch (Exception ex) {
            System.out.println("Error while fetching payroll details: " + ex.getMessage());
        }
    }

    public static void pay() {
        try {
            System.out.print("Enter employee id to pay: ");
            int id = readInt("id");

            System.out.print("Enter month name (e.g., January): ");
            String month = SCANNER.nextLine().trim();

            if (!isValidMonth(month)) {
                System.out.println("Invalid month name. Please try again.");
                return;
            }

            EmployeeDataHandle.payDatabase(id, month);

        } catch (Exception ex) {
            System.out.println("Error while making payment: " + ex.getMessage());
        }
    }

    private static boolean isValidMonth(String month) {
        String[] months = {
            "January","February","March","April","May","June",
            "July","August","September","October","November","December"
        };
        for (String m : months) {
            if (m.equalsIgnoreCase(month)) return true;
        }
        return false;
    }
}

