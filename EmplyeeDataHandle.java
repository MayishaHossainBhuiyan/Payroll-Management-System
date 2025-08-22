package Project;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EmplyeeDataHandle {

    static ArrayList<Employee> list = new ArrayList<Employee>();

    static {
        try {
            File f = new File("EmplyeeDataFile.txt");
            Scanner scan = new Scanner(f);
            while (scan.hasNext()) {
                int id = scan.nextInt();
                String name = scan.next();
                int age = scan.nextInt();
                String phone = scan.next();
                int salary = scan.nextInt();
                int[] month = new int[12];
                for (int i = 0; i < 12; i++) {
                    month[i] = scan.nextInt();

                }

                Employee em = new Employee(id, name, age, phone, salary, month);
                list.add(em);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    static void addEmployeeToDatabase(Employee em) {
        if (isExist(em)) {
            System.out.println("Employee Already exist");
        } else {
            list.add(em);
            updateFile();
            System.out.println("Employee Added");
        }

    }

    static void deleteEmployeeFromDatabase(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(list.get(i));
                System.out.println("Member Deleted");
                updateFile();
                return;
            }
        }
        System.out.println("Id not found");

    }

    static void FetchEmplyeeData() {
        for (int i = 0; i < list.size(); i++) {
            Employee ob = list.get(i);
            String mon = monthExpress(ob.getPaidMonthsArray());
            System.out.println("Id:" + ob.getId() + ", Name:" + ob.getName() + ", Age:" + ob.getAge() + ", Phone Number:" + ob.getPhoneNumber() + ", Salary:" + ob.getSalary() + ", Paid Months:{" + mon + "}");
        }
    }

    static String monthExpress(int[] mon) {
        String res = "";
        if (mon[0] == 1) {
            res += "January,";
        }
        if (mon[1] == 1) {
            res += "February,";
        }
        if (mon[2] == 1) {
            res += "March,";
        }
        if (mon[3] == 1) {
            res += "April,";
        }
        if (mon[4] == 1) {
            res += "May,";
        }
        if (mon[5] == 1) {
            res += "June,";
        }
        if (mon[6] == 1) {
            res += "July,";
        }
        if (mon[7] == 1) {
            res += "August,";
        }
        if (mon[8] == 1) {
            res += "September,";
        }
        if (mon[9] == 1) {
            res += "October,";
        }
        if (mon[10] == 1) {
            res += "November,";
        }
        if (mon[11] == 1) {
            res += "December,";
        }
        return res;
    }

    static void payrollDetiailsFromDatabase(int mon) {
        try {
            File f = new File("EmplyeeDataFile.txt");
            Scanner scan = new Scanner(f);
            while (scan.hasNext()) {
                int id = scan.nextInt();
                String name = scan.next();
                int age = scan.nextInt();
                String phone = scan.next();
                int salary = scan.nextInt();
                int[] month = new int[12];
                for (int i = 0; i < 12; i++) {
                    month[i] = scan.nextInt();
                }
                if (month[mon - 1] == 1) {
                    System.out.println("id: " + id + ", Name:" + name);
                }

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    static void PayDatabase(int id, String month) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.get(i).addMonth(month);
                System.out.println("Success");
                updateFile();
                return;
            }
        }
        System.out.println("id not found");
    }

    static void updateFile() {
        try {
            File f = new File("EmplyeeDataFile.txt");
            FileWriter wr = new FileWriter(f);
            for (int i = 0; i < list.size(); i++) {
                Employee ob = list.get(i);
                wr.write(ob.getId() + " " + ob.getName() + " " + ob.getAge() + " " + ob.getPhoneNumber() + " " + ob.getSalary() + " " + ob.getPaidMonths() + "\n");
            }
            wr.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    static boolean isExist(Employee em) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == em.getId()) {
                return true;
            }
        }
        return false;
    }

}
