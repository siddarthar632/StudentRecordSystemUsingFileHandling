import java.io.*;
import java.util.*;

public class StudentDetails {

    static final String FILE_NAME = "D:\\E Drive\\StudentRecordSystem\\StudentDetails.txt";

    // ---------------- ADD STUDENT ----------------
    public static void addStudent(Scanner sc) {

        try {

            System.out.print("Enter Student ID : ");
            int id = sc.nextInt();
            sc.nextLine();

            if (isIdExists(id)) {
                System.out.println("Student ID already exists!");
                return;
            }

            System.out.print("Enter Student Name : ");
            String name = sc.nextLine();

            System.out.print("Enter Student Age : ");
            int age = sc.nextInt();

            System.out.print("Enter Student Marks : ");
            double marks = sc.nextDouble();

            Student student = new Student(id, name, age, marks);

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
            bw.write(student.toString());
            bw.newLine();
            bw.close();

            System.out.println("\nStudent Added Successfully.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // ---------------- VIEW STUDENTS ----------------
    public static void viewStudents() {

        try {

            File file = new File(FILE_NAME);

            if (!file.exists() || file.length() == 0) {
                System.out.println("No Student Records Found.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            System.out.println("\n-----------------------------------------------");
            System.out.printf("%-8s %-20s %-8s %-8s\n",
                    "ID", "NAME", "AGE", "MARKS");
            System.out.println("-----------------------------------------------");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                System.out.printf("%-8s %-20s %-8s %-8s\n",
                        data[0], data[1], data[2], data[3]);
            }

            System.out.println("-----------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // ---------------- SEARCH STUDENT ----------------
    public static void searchStudent(Scanner sc) {

        try {

            System.out.print("Enter Student ID : ");
            int searchId = sc.nextInt();

            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (Integer.parseInt(data[0]) == searchId) {

                    System.out.println("\nStudent Found");
                    System.out.println("----------------------------");
                    System.out.println("ID    : " + data[0]);
                    System.out.println("Name  : " + data[1]);
                    System.out.println("Age   : " + data[2]);
                    System.out.println("Marks : " + data[3]);
                    System.out.println("----------------------------");

                    found = true;
                    break;
                }

            }

            if (!found) {
                System.out.println("Student Not Found.");
            }

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // ---------------- CHECK DUPLICATE ID ----------------
    public static boolean isIdExists(int id) {

        try {

            File file = new File(FILE_NAME);

            if (!file.exists())
                return false;

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (Integer.parseInt(data[0]) == id) {

                    br.close();
                    return true;
                }

            }

            br.close();

        } catch (Exception e) {
            return false;
        }

        return false;
    }

    // ---------------- UPDATE STUDENT ----------------
public static void updateStudent(Scanner sc) {

    System.out.print("Enter Student ID to Update: ");
    int updateId = sc.nextInt();
    sc.nextLine();

    File inputFile = new File(FILE_NAME);
    File tempFile = new File("temp.txt");

    boolean found = false;

    try (
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))
    ) {

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            if (Integer.parseInt(data[0]) == updateId) {

                found = true;

                System.out.print("Enter New Name: ");
                String name = sc.nextLine();

                System.out.print("Enter New Age: ");
                int age = sc.nextInt();

                System.out.print("Enter New Marks: ");
                double marks = sc.nextDouble();
                sc.nextLine();

                Student student = new Student(updateId, name, age, marks);

                bw.write(student.toString());

            } else {

                bw.write(line);

            }

            bw.newLine();
        }

    } catch (IOException e) {
        System.out.println(e.getMessage());
        return;
    }

    if (inputFile.delete()) {

        tempFile.renameTo(inputFile);

    }

    if (found)
        System.out.println("Student Updated Successfully.");
    else
        System.out.println("Student ID Not Found.");
}

    // ---------------- DELETE STUDENT ----------------
public static void deleteStudent(Scanner sc) {

    System.out.print("Enter Student ID to Delete: ");
    int deleteId = sc.nextInt();

    File inputFile = new File(FILE_NAME);
    File tempFile = new File("temp.txt");

    boolean found = false;

    try (
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))
    ) {

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            if (Integer.parseInt(data[0]) == deleteId) {

                found = true;
                continue;

            }

            bw.write(line);
            bw.newLine();

        }

    } catch (IOException e) {

        System.out.println(e.getMessage());
        return;

    }

    if (inputFile.delete()) {

        tempFile.renameTo(inputFile);

    }

    if (found)
        System.out.println("Student Deleted Successfully.");
    else
        System.out.println("Student ID Not Found.");
}

}
