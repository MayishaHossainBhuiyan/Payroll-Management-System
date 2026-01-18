package Project;

import java.io.*;
import java.util.ArrayList;

public class EmployeeDataHandle {

    private static final String DATA_FILE = "EmployeeDataFile.txt";
    private static final ArrayList<Employee> list = new ArrayList<>();

    // Load data once when class is used
    static {
        loadFromFile();
    }

    private static void loadFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            // No existing data, nothing to load.
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Format: id;name;age;phone;salary;paidMonthsCSV
                String[] parts = line.split(";");
                if (parts.length < 6) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String phone = parts[3];
                int salary = Integer.parseInt(parts[4]);
                int[] paidMonths = parsePaidMonths(parts[5]);

                Employee e = new Employee(id, name, age, phone, salary, paidMonths);
                list.add(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found, it will be created on save.");
        } catch (IOException e) {
            System.out.println("Error reading data file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number format error in data file: " + e.getMessage());
        }
    }

    private static int[] parsePaidMonths(String csv) {
        int[] arr = new int[12];
        try {
            String[] parts = csv.split(",");
            for (int i = 0; i < parts.length && i < 12; i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }
        } catch (Exception e) {
            System.out.println("Error parsing paid months: " + csv);
        }
        return arr;
    }

    public static boolean addEmployee(Employee e) {
        if (exists(e.getId())) {
            System.out.println("Employee with id " + e.getId() + " already exists.");
            return false;
        }
        list.add(e);
        updateFile();
        return true;
    }

    public static boolean removeEmployee(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                updateFile();
                System.out.println("Employee deleted successfully.");
                return true;
            }
        }
        System.out.println("Id not found.");
        return false;
    }

    public static void fetchEmployeeData() {
        System.out.print(getEmployeeDataString());
    }

    // 👉 NEW: return all employee data as a String (for GUI)
    public static String getEmployeeDataString() {
        if (list.isEmpty()) {
            return "No employees found.\n";
        }

        StringBuilder sb = new StringBuilder();
        for (Employee e : list) {
            sb.append("Id: ").append(e.getId())
                    .append(", Name: ").append(e.getName())
                    .append(", Age: ").append(e.getAge())
                    .append(", Phone: ").append(e.getPhoneNumber())
                    .append(", Salary: ").append(e.getSalary())
                    .append(", Paid Months: ").append(monthExpress(e.getPaidMonthsArray()))
                    .append("\n");
        }
        return sb.toString();
    }

    public static void payrollDetailsFromDatabase(int id) {
        System.out.print(getPayrollDetailsString(id));
    }

    
    public static String getPayrollDetailsString(int id) {
        Employee e = findById(id);
        if (e == null) {
            return "Id " + id + " not found.\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("---- Payroll Details ----\n");
        sb.append("Id: ").append(e.getId()).append("\n");
        sb.append("Name: ").append(e.getName()).append("\n");
        sb.append("Age: ").append(e.getAge()).append("\n");
        sb.append("Phone: ").append(e.getPhoneNumber()).append("\n");
        sb.append("Salary: ").append(e.getSalary()).append("\n");
        sb.append("Paid Months: ").append(monthExpress(e.getPaidMonthsArray())).append("\n");
        return sb.toString();
    }

    public static boolean payDatabase(int id, String month) {
        Employee e = findById(id);
        if (e == null) {
            System.out.println("Id not found.");
            return false;
        }

        try {
            e.addMonth(month);
            updateFile();
            System.out.println("Payment recorded successfully.");
            return true;
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid month: " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            System.out.println("Error while updating payment: " + ex.getMessage());
            return false;
        }
    }

    private static Employee findById(int id) {
        for (Employee e : list) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    private static boolean exists(int id) {
        return findById(id) != null;
    }

    private static void updateFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Employee e : list) {
                StringBuilder sb = new StringBuilder();
                sb.append(e.getId()).append(";");
                sb.append(e.getName()).append(";");
                sb.append(e.getAge()).append(";");
                sb.append(e.getPhoneNumber()).append(";");
                sb.append(e.getSalary()).append(";");
                sb.append(e.getPaidMonths()); // CSV string

                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing data file: " + e.getMessage());
        }
    }

    private static String monthExpress(int[] mon) {
        String[] months = {
            "January","February","March","April","May","June",
            "July","August","September","October","November","December"
        };

        StringBuilder sb = new StringBuilder("{");
        boolean any = false;

        for (int i = 0; i < mon.length && i < 12; i++) {
            if (mon[i] == 1) {
                if (any) sb.append(", ");
                sb.append(months[i]);
                any = true;
            }
        }

        if (!any) sb.append("No paid months");
        sb.append("}");
        return sb.toString();
    }
}
