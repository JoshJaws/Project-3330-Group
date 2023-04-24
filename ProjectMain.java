import java.util.*;
import librarypackage.SSN;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class ProjectMain {
    Library library;

    ProjectMain() {
        this.library = new Library();
    }

    // Print the main menu:
    public static void mainMenu() {
        System.out.println("University of Java Library System");
        System.out.println("Menu Options");
        System.out.println("1. New Membership");
        System.out.println("2. New Collection");
        System.out.println("3. Remove Membership");
        System.out.println("4. Remove Item from Collection");
        System.out.println("5. New Employee");
        System.out.println("6. Borrow Item");
        System.out.println("7. Return Item");
        System.out.println("8. Check overdues");
        System.out.println("9. Quit");
        System.out.println("");
    }

    // You can either implement your events in these functions, or you can write an
    // Event class and call a static function here.
    public static void newMemberEvent(Library library) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter Membership info: ");
        System.out.print("Enter Member Name: ");
        String name = scn.nextLine();

        System.out.print("Enter Member Address: ");
        String address = scn.nextLine();

        System.out.print("Enter Member Date of Birth (dd/MM/yyyy): ");
        String dob = scn.nextLine();

        System.out.print("Enter Member email: ");
        String email = scn.nextLine();

        System.out.print("Enter Member SSN: ");
        String ssn = scn.nextLine();
        SSN SSN = new SSN(ssn);

        System.out.print("Enter Membership type (Student/Professor/External): ");
        String memtype = scn.nextLine();

        System.out.print("Creating a new member...");
        scn.close();
        try {
            Date DOB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
            if (memtype == "Student" || memtype == "student")
                library.ondutyLibrarian.makeStudent(name, address, DOB, email, SSN, library);
            else if (memtype == "Professor" || memtype == "professor")
                library.ondutyLibrarian.makeProfessor(name, address, DOB, email, SSN, library);
            else
                library.ondutyLibrarian.makeMember(name, address, DOB, email, SSN, library);
        } catch (Exception e) {
            System.out.println("must enter an appropriate dob!"); // Happen when the dob format is wrong.
            return;
        }
        System.out.print("The membership ID is: " + library.getMember(name).getID());
        System.out.println("New Member Successfully Saved to database.");
    }

    public static void newCollectionEvent() {
        // Scanner scn = new Scanner(System.in);
        // System.out.println("Enter new collection info: ");
        
        // System.out.print("Enter collection Name: ");
        // String name = scn.nextLine();
    };

    public static void newRemoveMemberEvent(Library library) {
        System.out.println("Enter the name of the member who you want to remove:");
        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();
        library.removeMember(name);
        scn.close();
    }

    public static void newRemoveCollectionEvent() {
    };

    public static void newEmployeeEvent(Library library) {
        // Enter info of the member:
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter Employee info: ");
        System.out.print("Enter Employee Name: ");
        String name = scn.nextLine();

        System.out.print("Enter Employee Address: ");
        String address = scn.nextLine();

        System.out.print("Enter Employee Date of Birth (dd/MM/yyyy): ");
        String dob = scn.nextLine();

        System.out.print("Enter Employee email: ");
        String email = scn.nextLine();

        System.out.print("Enter Employee SSN: ");
        String ssn = scn.nextLine();
        SSN SSN = new SSN(ssn);

        System.out.print("Enter Employee type (Librarian/Technician): ");
        String memtype = scn.nextLine();

        System.out.print("Creating a new employee...");

        // Try to create a new member.
        try {
            Date DOB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
            if (memtype == "Librarian" || memtype == "librarian") {
                Librarian librarian = new Librarian(name, address, DOB, email, SSN, library);
                library.addLibrarian(librarian);
            } else {
                Technician technician = new Technician(name, address, DOB, email, SSN, library);
                library.addTechnician(technician);
            }
        } catch (Exception e) {
            System.out.println("must enter an appropriate dob!");
            return;
        }
        System.out.println("New Employee Successfully Saved to database.");
    }

    public static void newBorrowsEvent() {
    };

    public static void newReturnEvent() {
    };

    public static void newCheckOverdues() {
    };

    // You are free to implememnt other events that you see needs to be implemented
    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("Library must have a librarian and technician to be used!"); // Preconditions for Library
    
        //Add 3 employee: Librarian and Technician
        ProjectMain.newEmployeeEvent(library);
        ProjectMain.newEmployeeEvent(library);
        ProjectMain.newEmployeeEvent(library);

        Hashtable<Integer, Member> memberTable=library.getMemberTable();
        Hashtable<Integer, Employee> employeeTable=library.getEmployeeTable();

        for(Employee employee:employeeTable.values()){
            System.out.println(employee.name);
        }

        ProjectMain.newRemoveMemberEvent(library);

        for(Employee employee:employeeTable.values()){
            System.out.println(employee.name);
        }

        ProjectMain.mainMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your option number: ");
        int option = scanner.nextInt();
        while (true) {
            switch (option) {
                case 1:
                    ProjectMain.newMemberEvent(library);
                    break;
                case 2:
                    ProjectMain.newCollectionEvent();
                    break;
                case 3:
                    // ProjectMain.newRemoveMemberEvent(library,library.getMember(name));
                    break;
                case 4:
                    ProjectMain.newRemoveCollectionEvent();
                    break;
                case 5:
                    ProjectMain.newEmployeeEvent(library);
                    break;
                case 6:
                    ProjectMain.newBorrowsEvent();
                    break;
                case 7:
                    ProjectMain.newReturnEvent();
                    break;
                case 8:
                    ProjectMain.newCheckOverdues();
                    break;
                case 9:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid operator.");
                    continue;
            }
        }
    }
}
