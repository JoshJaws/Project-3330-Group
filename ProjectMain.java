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



    //Events functions:

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
        SSN SSN = new SSN(ssn); //convert the String to SSN type.

        String memtype;

        while(true){
            System.out.print("Enter Membership type (Student/Professor/External): ");
            memtype = scn.nextLine();
            if(memtype.equals("Student")||memtype.equals("student")||memtype.equals("Professor")||memtype.equals("professor")||memtype.equals("External")||memtype.equals("external")) 
            break;
            System.out.println("Invalid input!");
        }

        System.out.print("Creating a new member...");
        try {
            Date DOB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
            if (memtype.equals("Student") || memtype.equals("student")){
                library.ondutyLibrarian.makeStudent(name, address, DOB, email, SSN, library);
            }
            else if (memtype.equals("Professor") || memtype.equals("professor"))
                library.ondutyLibrarian.makeProfessor(name, address, DOB, email, SSN, library);
            else
                library.ondutyLibrarian.makeMember(name, address, DOB, email, SSN, library);
        } catch (Exception e) {
            System.out.println("must enter an appropriate dob!"); // Happen when the dob format is wrong.
            return;
        }
        System.out.println("The membership ID is: " + library.getMember(name).getID());
        System.out.println("New Member Successfully Saved to database.");
        System.out.println("");
    }

    //Checked
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

        String memtype;

        while(true){
            System.out.print("Enter Employee type (Librarian/Technician): ");
            memtype = scn.nextLine();
            if(memtype.equals("Librarian")||memtype.equals("librarian")||memtype.equals("Technician")||memtype.equals("technician")) 
            break;
            System.out.println("Invalid input!");
        }

        System.out.print("Creating a new employee...");

        // Try to create a new employee.
        try {
            Date DOB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
            if (memtype.equals("Librarian")  || memtype.equals("librarian")) {
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
        System.out.println("");
    }

    public static void newCollectionEvent() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter new collection info: ");
        
        System.out.print("Enter collection Name: ");
        String name = scn.nextLine();

        System.out.println();
    };

    public static void newRemoveMemberEvent(Library library) {
        System.out.println("Enter the name of the member who you want to remove:");
        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();
        library.removeMember(name);
    }

    public static void newRemoveCollectionEvent() {
    };

    public static void newBorrowsEvent() {
        String collection;
        Scanner scn = new Scanner(System.in);
        while(true){
            System.out.println("Which kind of collection do you want to borrow?");
            System.out.println("Books--B   DVD---D  Journal--J  newspaper--N");
            collection=scn.nextLine();
            if(collection.equals("B")||collection.equals("D")||collection.equals("J")||collection.equals("N"))
                break;
            System.out.println("Invalid input!");
        }

        String itemSection;
        while(true){
            System.out.println("Which section does this item belong to?");
            System.out.println("Art--A   Science--S  Law--L  newspaper--N");
            itemSection=scn.nextLine();
            if(itemSection.equals("A")||itemSection.equals("S")||itemSection.equals("L")||itemSection.equals("N"))
                break;
            System.out.println("Invalid input!");
        }

    };

    public static void newReturnEvent() {
    };

    public static void newCheckOverdues() {
    };

    // You are free to implememnt other events that you see needs to be implemented
    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("Library must have a librarian and technician to be used!"); // Preconditions for Library
    
        //Add 2 employee: Librarian and Technician 
        ProjectMain.newEmployeeEvent(library);
        ProjectMain.newEmployeeEvent(library);

        //Add 2 members to test
        ProjectMain.newMemberEvent(library);
        ProjectMain.newMemberEvent(library);

        Hashtable<Integer, Member> memberTable=library.getMemberTable();
        Hashtable<Integer, Employee> employeeTable=library.getEmployeeTable();

        System.out.println("Employees:");
        for(Employee employee:employeeTable.values()){
            System.out.println(employee.name);
        }
        System.out.println("");

        System.out.println("Members:");
        for(Member member:memberTable.values()){
            System.out.println(member.name);
        }
        System.out.println("");

        //Test remove a member.
        ProjectMain.newRemoveMemberEvent(library);
        System.out.println("");

        System.out.println("Member after deletion:");
        for(Member member:memberTable.values()){
            System.out.println(member.name);
        }
        System.out.println("");

        // for(Employee employee:employeeTable.values()){
        //     System.out.println(employee.name);
        // }

        // ProjectMain.mainMenu();
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
                    ProjectMain.newRemoveMemberEvent(library);
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
