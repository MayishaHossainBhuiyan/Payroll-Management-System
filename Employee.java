package Project;

import java.util.ArrayList;

public class Employee extends Person implements EmployeeData {

    private int id;

    private String PhoneNumber;
    private int salary;
    int[] paidMonths;

    Employee(int id, String name, int age, String phoneNumber, int salary, int s[]) {
        super(name, age);
        this.id = id;
        this.PhoneNumber = phoneNumber;
        this.salary = salary;
        paidMonths = new int[12];
        for (int i = 0; i < 12; i++) {
            paidMonths[i] = s[i];
        }
    }

    Employee(int id, String name, int age, String phoneNumber, int salary) {
        super(name, age);
        this.id = id;
        this.PhoneNumber = phoneNumber;
        this.salary = salary;
        paidMonths = new int[12];

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String getPhoneNumber() {
        return this.PhoneNumber;
    }

    @Override
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getPaidMonths() {
        String mon = "";
        for (int i = 0; i < paidMonths.length; i++) {
            mon += paidMonths[i] + " ";
        }
        return mon;
    }

    @Override
    public void addMonth(String month) {
        if (month.equalsIgnoreCase("January")) {
            paidMonths[0] = 1;
        } else if (month.equalsIgnoreCase("February")) {
            paidMonths[1] = 1;
        } else if (month.equalsIgnoreCase("March")) {
            paidMonths[2] = 1;
        } else if (month.equalsIgnoreCase("April")) {
            paidMonths[3] = 1;
        } else if (month.equalsIgnoreCase("May")) {
            paidMonths[4] = 1;
        } else if (month.equalsIgnoreCase("June")) {
            paidMonths[5] = 1;
        } else if (month.equalsIgnoreCase("July")) {
            paidMonths[6] = 1;
        } else if (month.equalsIgnoreCase("August")) {
            paidMonths[7] = 1;
        } else if (month.equalsIgnoreCase("September")) {
            paidMonths[8] = 1;
        } else if (month.equalsIgnoreCase("October")) {
            paidMonths[9] = 1;
        } else if (month.equalsIgnoreCase("November")) {
            paidMonths[10] = 1;
        } else if (month.equalsIgnoreCase("December")) {
            paidMonths[11] = 1;
        } else {
            System.out.println("Month name not found");
        }
    }

    public int[] getPaidMonthsArray() {
        return paidMonths;
    }

}
