import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("----------------------------");
        System.out.println("  Student Grade Calculator  ");
        System.out.println("----------------------------");

        // 1. Kitne subjects hain?
        System.out.print("Enter the number of subjects: ");
        int numSubjects = sc.nextInt();

        int totalMarks = 0;

        // 2. Har subject ke marks input lo
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks obtained in Subject " + i + " (out of 100): ");
            int marks = sc.nextInt();
            
            // Validation: Marks 0 se 100 ke beech hone chahiye
            while (marks < 0 || marks > 100) {
                System.out.println("⚠️ Invalid marks! Please enter between 0 and 100.");
                System.out.print("Enter marks for Subject " + i + " again: ");
                marks = sc.nextInt();
            }
            totalMarks += marks;
        }

        // 3. Percentage Calculate karo
        double averagePercentage = (double) totalMarks / numSubjects;
        char grade;

        // 4. Grade Assign karo 
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F'; // Fail
        }

        // 5. Result Show karo
        System.out.println("\n----------------------------");
        System.out.println("       FINAL RESULTS        ");
        System.out.println("----------------------------");
        System.out.println("Total Marks:      " + totalMarks + " / " + (numSubjects * 100));
        System.out.printf("Average Percent:  %.2f%%\n", averagePercentage);
        System.out.println("Grade:            " + grade);
        System.out.println("----------------------------");

        sc.close();
    }
}