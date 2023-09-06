package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
public class StudentRecordGUI {
    private StudentRecord record;
    private JFrame frame;
    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextArea outputArea;

    public StudentRecordGUI() {
        record = new StudentRecord();
        frame = new JFrame("Student Record Management");
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        nameField = new JTextField();
        rollNumberField = new JTextField();
        addressField = new JTextField();
        phoneNumberField = new JTextField();
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                String address = addressField.getText();
                String phoneNumber = phoneNumberField.getText();
                record.addStudent(new student(name, rollNumber, address, phoneNumber));
                displayMessage("Student added successfully.");
                clearFields();
            }
        });



        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter Roll Number to Remove:");
                try {
                    int rollNumber = Integer.parseInt(input);
                    record.removeStudent(rollNumber);
                    displayMessage("Student removed successfully.");
                    clearFields();
                } catch (NumberFormatException ex) {
                    displayMessage("Invalid input. Please enter a valid roll number.");
                }
            }
        });

        JButton searchByRollButton = new JButton("Search by Roll Number");
        searchByRollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter Roll Number:");
                if (input != null && !input.isEmpty()) {
                    try {
                        int rollNumber = Integer.parseInt(input);
                        student foundStudent = record.findStudentByRollNumber(rollNumber);
                        if (foundStudent != null) {
                            displayMessage("Found Student by Roll Number:\n" + foundStudent.toString());
                        } else {
                            displayMessage("Student not found.");
                        }
                    } catch (NumberFormatException ex) {
                        displayMessage("Invalid Roll Number format.");
                    }
                }
            }
        });

        JButton searchByNameButton = new JButton("Search by Name");
        searchByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter Name:");
                if (name != null && !name.isEmpty()) {
                    student foundStudent = record.findStudentByName(name);
                    if (foundStudent != null) {
                        displayMessage("Found Student by Name:\n" + foundStudent.toString());
                    } else {
                        displayMessage("Student not found.");
                    }
                }
            }
        });
        JPanel searchButtonsPanel = new JPanel(new FlowLayout());
        searchButtonsPanel.add(searchByRollButton);
        searchButtonsPanel.add(searchByNameButton);


        JButton saveButton = new JButton("Save to File");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog(frame, "Enter the filename to save:");
                if (fileName != null && !fileName.isEmpty()) {
                    record.saveToFile(fileName);
                }
            }
        });

        // Load button
        JButton loadButton = new JButton("Load from File");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog(frame, "Enter the filename to load:");
                if (fileName != null && !fileName.isEmpty()) {
                    record.loadFromFile(fileName);
                    displayAllStudents();
                }
            }
        });
        searchButtonsPanel.add(saveButton);
        searchButtonsPanel.add(loadButton);
        JPanel fileButtonsPanel = new JPanel(new FlowLayout());
        fileButtonsPanel.add(saveButton);
        fileButtonsPanel.add(loadButton);

// Add searchButtonsPanel and fileButtonsPanel to the main button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(searchButtonsPanel);
        buttonPanel.add(fileButtonsPanel);




        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll Number:"));
        inputPanel.add(rollNumberField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Phone Number:"));
        inputPanel.add(phoneNumberField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(inputPanel, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(controlPanel, BorderLayout.NORTH);
        JButton displayAllDataButton = new JButton("Display All Data");
        displayAllDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");// Clear the output area
                displayAllStudents();
            }
        });

        frame.add(displayAllDataButton, BorderLayout.SOUTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    private void displayMessage(String message) {
        outputArea.append(message + "\n");
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        addressField.setText("");
        phoneNumberField.setText("");
    }
    private void displayAllStudents() {
        outputArea.setText(""); // Clear the output area
        List<student> studentList = record.displayAllStudents(); // Change the variable name
        for (student student : studentList) { // Use the corrected variable name
            outputArea.append(student.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentRecordGUI();
            }
        });
    }
}
