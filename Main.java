import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=====================================");
            System.out.println("     STUDENT RECORD SYSTEM");
            System.out.println("=====================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("=====================================");

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    StudentDetails.addStudent(sc);
                    break;

                case 2:
                    StudentDetails.viewStudents();
                    break;

                case 3:
                    StudentDetails.searchStudent(sc);
                    break;

                case 4:
                    StudentDetails.updateStudent(sc);
                    break;

                case 5:
                    StudentDetails.deleteStudent(sc);
                    break;

                case 6:
                    System.out.println("\nThank You for Using Student Record System!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice! Please try again.");
            }
        }
    }
}