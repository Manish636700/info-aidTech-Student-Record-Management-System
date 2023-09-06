package student;

import java.io.*;


class student implements Serializable {
    private String name;
    private int rollNumber;
    private String address;
    private String phoneNumber;

    public student(String name, int rollNumber, String address, String phoneNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Roll Number: " + rollNumber + "\n" +
                "Address: " + address + "\n" +
                "Phone Number: " + phoneNumber + "\n";
    }
}
