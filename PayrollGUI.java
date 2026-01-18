package Project;

import javax.swing.*;
import java.awt.*;

public class PayrollGUI extends JFrame {

    //(Black & Off-White)
    private static final Color COLOR_DARK = new Color(18, 18, 18);
    private static final Color COLOR_BG   = new Color(245, 245, 240); // off-white background
    private static final Color COLOR_CARD = new Color(252, 252, 248); // slightly brighter off-white
    private static final Color COLOR_LINE = new Color(60, 60, 60);

    private JTextArea outputArea;
    private JLabel statusLabel;

    public PayrollGUI() {
        setTitle("Payroll Management System");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ROOT WRAPPER (adds left/right margin)
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(COLOR_BG);
        root.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16)); // LEFT & RIGHT MARGIN
        setContentPane(root);

        // Top header
        root.add(createHeaderPanel(), BorderLayout.NORTH);

        // Left menu
        root.add(createLeftMenuPanel(), BorderLayout.WEST);

        // Center content
        root.add(createCenterPanel(), BorderLayout.CENTER);

        // Bottom status bar
        root.add(createStatusBar(), BorderLayout.SOUTH);
    }

    //HEADER
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_DARK);

        JLabel title = new JLabel(" Payroll Management System ");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JLabel subtitle = new JLabel(" Manage employees, payroll, and payments easily ");
        subtitle.setForeground(new Color(230, 230, 225));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(Box.createVerticalStrut(8));
        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(4));
        textPanel.add(subtitle);
        textPanel.add(Box.createVerticalStrut(8));

        header.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        header.add(textPanel, BorderLayout.WEST);

        return header;
    }

    //LEFT MENU
    private JPanel createLeftMenuPanel() {
        JPanel left = new JPanel();
        left.setBackground(COLOR_DARK);
        left.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, COLOR_LINE));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JLabel menuLabel = new JLabel(" Actions");
        menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        menuLabel.setForeground(new Color(245, 245, 240));
        menuLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        left.add(menuLabel);
        left.add(Box.createVerticalStrut(5));

        JButton addButton     = createMenuButton(" Add Employee");
        JButton removeButton  = createMenuButton("Remove Employee");
        JButton listButton    = createMenuButton("List Employees");
        JButton detailsButton = createMenuButton("Payroll Details");
        JButton payButton     = createMenuButton("Make Payment");
        JButton clearButton   = createMenuButton("Clear Output");
        JButton exitButton    = createMenuButton("Exit");

        // Actions
        addButton.addActionListener(e -> addEmployeeUI());
        removeButton.addActionListener(e -> removeEmployeeUI());
        listButton.addActionListener(e -> {
            String data = EmployeeDataHandle.getEmployeeDataString();
            outputArea.setText(data);
            setStatus("Loaded employee list.");
        });
        detailsButton.addActionListener(e -> payrollDetailsUI());
        payButton.addActionListener(e -> payUI());
        clearButton.addActionListener(e -> {
            outputArea.setText("");
            setStatus("Output cleared.");
        });
        exitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Add to panel
        left.add(addButton);
        left.add(removeButton);
        left.add(listButton);
        left.add(detailsButton);
        left.add(payButton);
        left.add(clearButton);

        left.add(Box.createVerticalGlue());
        left.add(exitButton);
        left.add(Box.createVerticalStrut(15));

        return left;
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Medium button size + padding
        btn.setMaximumSize(new Dimension(220, 44));
        btn.setPreferredSize(new Dimension(220, 44));
        btn.setMinimumSize(new Dimension(200, 44));

        btn.setBackground(COLOR_CARD);    
        btn.setForeground(COLOR_DARK);

        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 190)),
                BorderFactory.createEmptyBorder(10, 16, 10, 16) // medium padding
        ));

        // Hover effect
        btn.addChangeListener(e -> {
            ButtonModel model = btn.getModel();
            if (model.isRollover()) {
                btn.setBackground(new Color(232, 232, 226));
            } else {
                btn.setBackground(COLOR_CARD);
            }
        });

        return btn;
    }

    //CENTER PANEL
    private JPanel createCenterPanel() {
        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        center.setBackground(COLOR_BG);

        JLabel lbl = new JLabel(" Output");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbl.setForeground(COLOR_DARK);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        outputArea.setMargin(new Insets(8, 8, 8, 8));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        
        outputArea.setBackground(COLOR_CARD);
        outputArea.setForeground(COLOR_DARK);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 190)));

        // Make scroll viewport OFF-WHITE too
        scrollPane.getViewport().setBackground(COLOR_CARD);

        center.add(lbl, BorderLayout.NORTH);
        center.add(scrollPane, BorderLayout.CENTER);

        return center;
    }

    //STATUS BAR 
    private JPanel createStatusBar() {
        JPanel status = new JPanel(new BorderLayout());
        status.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, COLOR_LINE));

        // Make status bar dark (matches theme)
        status.setBackground(COLOR_DARK);

        statusLabel = new JLabel(" Ready.");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(230, 230, 225));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        status.add(statusLabel, BorderLayout.WEST);
        return status;
    }

    private void setStatus(String msg) {
        statusLabel.setText(" " + msg);
    }



    private void addEmployeeUI() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Enter employee id:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr.trim());

            String name = JOptionPane.showInputDialog(this, "Enter employee name:");
            if (name == null || name.trim().isEmpty()) {
                showError("Name cannot be empty.");
                return;
            }

            String ageStr = JOptionPane.showInputDialog(this, "Enter employee age:");
            if (ageStr == null) return;
            int age = Integer.parseInt(ageStr.trim());

            String phone = JOptionPane.showInputDialog(this, "Enter employee phone number:");
            if (phone == null || phone.trim().isEmpty()) {
                showError("Phone number cannot be empty.");
                return;
            }

            String salaryStr = JOptionPane.showInputDialog(this, "Enter employee salary:");
            if (salaryStr == null) return;
            int salary = Integer.parseInt(salaryStr.trim());

            int[] paidMonths = new int[12];

            Employee emp = new Employee(id, name.trim(), age, phone.trim(), salary, paidMonths);
            boolean added = EmployeeDataHandle.addEmployee(emp);

            if (added) {
                showInfo("Employee added successfully.");
                setStatus("Employee " + id + " added.");
            } else {
                showError("Employee with id " + id + " already exists.");
                setStatus("Add failed: duplicate id.");
            }

        } catch (NumberFormatException ex) {
            showError("Invalid number format. Please enter valid integers for id, age, and salary.");
            setStatus("Error: invalid numeric input.");
        } catch (Exception ex) {
            showError("Error while adding employee: " + ex.getMessage());
            setStatus("Unexpected error while adding.");
        }
    }

    private void removeEmployeeUI() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Enter employee id to remove:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr.trim());

            boolean removed = EmployeeDataHandle.removeEmployee(id);
            if (removed) {
                showInfo("Employee removed successfully.");
                setStatus("Employee " + id + " removed.");
            } else {
                showError("Employee id " + id + " not found.");
                setStatus("Remove failed: id not found.");
            }
        } catch (NumberFormatException ex) {
            showError("Invalid id. Please enter a valid integer.");
            setStatus("Error: invalid id input.");
        } catch (Exception ex) {
            showError("Error while removing employee: " + ex.getMessage());
            setStatus("Unexpected error while removing.");
        }
    }

    private void payrollDetailsUI() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Enter employee id for payroll details:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr.trim());

            String details = EmployeeDataHandle.getPayrollDetailsString(id);
            outputArea.setText(details);
            setStatus("Loaded payroll details for id " + id + ".");
        } catch (NumberFormatException ex) {
            showError("Invalid id. Please enter a valid integer.");
            setStatus("Error: invalid id input.");
        } catch (Exception ex) {
            showError("Error while fetching payroll details: " + ex.getMessage());
            setStatus("Unexpected error while fetching details.");
        }
    }

    private void payUI() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Enter employee id to pay:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr.trim());

            String month = JOptionPane.showInputDialog(this, "Enter month name (e.g., January):");
            if (month == null || month.trim().isEmpty()) {
                showError("Month cannot be empty.");
                return;
            }

            if (!isValidMonth(month)) {
                showError("Invalid month name. Please enter a correct month (January..December).");
                return;
            }

            boolean success = EmployeeDataHandle.payDatabase(id, month.trim());
            if (success) {
                showInfo("Payment recorded successfully.");
                setStatus("Payment recorded for id " + id + " (" + month + ").");
            } else {
                showError("Could not process payment. Check employee id or month.");
                setStatus("Payment failed.");
            }

        } catch (NumberFormatException ex) {
            showError("Invalid id. Please enter a valid integer.");
            setStatus("Error: invalid id input.");
        } catch (Exception ex) {
            showError("Error while processing payment: " + ex.getMessage());
            setStatus("Unexpected error while paying.");
        }
    }

    private boolean isValidMonth(String month) {
        String[] months = {
                "January","February","March","April","May","June",
                "July","August","September","October","November","December"
        };
        for (String m : months) {
            if (m.equalsIgnoreCase(month.trim())) return true;
        }
        return false;
    }

    //helpers 
    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // MAIN: run GUI
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            PayrollGUI gui = new PayrollGUI();
            gui.setVisible(true);
        });
    }
}
