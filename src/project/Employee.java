package Project;

public class Employee extends Person implements EmployeeData {

    private int id;
    private String phoneNumber;
    private int salary;
    private int[] paidMonths; // index 0..11 = January..December

    public Employee(int id, String name, int age,
                    String phoneNumber, int salary, int[] paidMonths) {
        super(name, age);
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.salary = salary;

        if (paidMonths != null && paidMonths.length == 12) {
            this.paidMonths = paidMonths.clone();
        } else {
            this.paidMonths = new int[12]; // all 0 (unpaid)
        }
    }

    // ==== EmployeeData implementation ====

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    // returns something like "1,0,0,1,0,0,0,0,0,0,0,0"
    @Override
    public String getPaidMonths() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paidMonths.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(paidMonths[i]);
        }
        return sb.toString();
    }

    @Override
    public void addMonth(String month) {
        int index = monthNameToIndex(month);
        if (index < 0) {
            throw new IllegalArgumentException("Unknown month: " + month);
        }
        paidMonths[index] = 1;
    }

    private int monthNameToIndex(String month) {
        if (month == null) return -1;
        String m = month.trim().toLowerCase();

        switch (m) {
            case "january":   return 0;
            case "february":  return 1;
            case "march":     return 2;
            case "april":     return 3;
            case "may":       return 4;
            case "june":      return 5;
            case "july":      return 6;
            case "august":    return 7;
            case "september": return 8;
            case "october":   return 9;
            case "november":  return 10;
            case "december":  return 11;
            default:          return -1;
        }
    }

    public int[] getPaidMonthsArray() {
        return paidMonths.clone();
    }
}
