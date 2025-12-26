import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// 1. Student Class (Data Structure)
class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    // Display format
    @Override
    public String toString() {
        return "Roll No: " + rollNumber + " | Name: " + name + " | Grade: " + grade;
    }
}

// 2. Management System (Logic)
class SMS {
    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.dat"; // Data yahan save hoga

    public SMS() {
        loadData(); // Program start hote hi purana data load karo
    }

    // Add Student
    public void addStudent(String name, int rollNumber, String grade) {
        students.add(new Student(name, rollNumber, grade));
        saveData();
        System.out.println("✅ Student Added Successfully!");
    }

    // Remove Student
    public void removeStudent(int rollNumber) {
        boolean found = false;
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                students.remove(s);
                found = true;
                break;
            }
        }
        if (found) {
            saveData();
            System.out.println("🗑️ Student Removed.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    // Search Student
    public void searchStudent(int rollNumber) {
        boolean found = false;
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                System.out.println("🔎 Student Found: " + s);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("❌ Student not found.");
    }

    // Display All
    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("📂 No students found.");
        } else {
            System.out.println("\n--- All Students ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // --- File Handling 
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("⚠️ Error saving data.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                students = (ArrayList<Student>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("⚠️ Error loading data.");
            }
        }
    }
}

// 3. Main Class (User Interface)
public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SMS sms = new SMS();
        int choice;

        System.out.println("=================================");
        System.out.println(" 🎓 Student Management System 🎓 ");
        System.out.println("=================================");

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            // Validation: Agar user number ki jagah text daal de
            if (!sc.hasNextInt()) {
                System.out.println("⚠️ Invalid Input. Please enter a number.");
                sc.next(); // Clear buffer
                continue;
            }
            
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    sc.nextLine(); // Consume newline
                    String name = sc.nextLine();
                    
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    
                    System.out.print("Enter Grade (A/B/C): ");
                    String grade = sc.next();
                    
                    sms.addStudent(name, roll, grade);
                    break;
                case 2:
                    System.out.print("Enter Roll No to Remove: ");
                    int rRoll = sc.nextInt();
                    sms.removeStudent(rRoll);
                    break;
                case 3:
                    System.out.print("Enter Roll No to Search: ");
                    int sRoll = sc.nextInt();
                    sms.searchStudent(sRoll);
                    break;
                case 4:
                    sms.displayAll();
                    break;
                case 5:
                    System.out.println("Exiting... Data Saved. Goodbye! 👋");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("⚠️ Invalid Option.");
            }
        }
    }
}