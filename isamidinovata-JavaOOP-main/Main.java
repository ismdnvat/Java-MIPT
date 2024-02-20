import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void PrintMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Create an object of the Student class");
        System.out.println("2. Get name of student");
        System.out.println("3. Rename student");
        System.out.println("4. Add a grade");
        System.out.println("5. Delete a grade");
        System.out.println("6. Get grades");
        System.out.println("7. Print student information");
        System.out.println("8. Undo last action");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
        Student<Integer> student = null;
        while (true) {
            PrintMenu();
            int choice = scanner.nextInt();

            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter student name:");
                    String name = scanner.nextLine();
                    student = new Student<>(name, (grade) -> true);
                    System.out.println("Student " + name + " is created.");
                    break;
                case 2:
                    if (student != null) {
                        String nameStudent = student.getName();
                        System.out.println("Student's name is " + nameStudent + ".");
                    } else {
                        System.out.println("First create a student.");
                    }
                    break;
                case 3:
                    if (student != null) {
                        System.out.println("New name for " + student.getName() + ": ");
                        String newName = scanner.nextLine();
                        student.changeName(newName);
                        System.out.println("Student's name is updated.");
                    } else {
                        System.out.println("First create a student.");
                    }
                    break;
                case 4:
                    if (student != null) {
                        System.out.println("New grade: ");
                        int grade = scanner.nextInt();
                        student.addGrade(grade);
                        System.out.println("Grade " + grade + " added.");
                    } else {
                        System.out.println("First create a student.");
                    }
                    break;
                case 5:
                    if (student != null) {
                        System.out.println("Input a grade to delete:");
                        int grade = scanner.nextInt();
                        student.deleteGrade(grade);
                        System.out.println("Grade " + grade + " deleted");
                    } else {
                        System.out.println("First create a student.");
                    }
                    break;
                case 6:
                    if (student != null) {
                        ArrayList<Integer> grades = student.getGrades();
                        System.out.print("List of " + student.getName() + "'s grades: ");
                        for (Integer grade : grades) {
                            System.out.print(grade + " ");
                        }
                        System.out.println();
                    } else {
                        System.out.println("First create a student.");
                    }
                    break;
                case 7:
                    if (student != null) {
                        System.out.println(student.toString());
                    } else {
                        System.out.println("First create a student.");
                    }
                    break;
                case 8:
                    if (student != null) {
                        student.undoLastAction();
                        System.out.println("Last action canceled");
                    } else {
                        System.out.println("First create a student.");
                    }
                    break;
                case 0:
                    System.out.println("Exit");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect action");
                    break;
            }
            System.out.println();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
