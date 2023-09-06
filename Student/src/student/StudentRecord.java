package student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRecord {
    private List<student> students;

    public StudentRecord() {
        students = new ArrayList<>();
    }

    public void addStudent(student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public student findStudentByRollNumber(int rollNumber) {
        for (student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null; // Student not found
    }

    public student findStudentByName(String name) {
        for (student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null; // Student not found
    }

    public List<student> displayAllStudents() {
            return students;
    }

    // Save the student record to a file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
            System.out.println("Student record saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving student record to file: " + e.getMessage());
        }
    }

    // Load the student record from a file
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (List<student>) ois.readObject();
            System.out.println("Student record loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading student record from file: " + e.getMessage());
        }
    }
}