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
        System.out.println("2. New item");
        System.out.println("3. Remove Membership");
        System.out.println("4. Remove Item from Collection");
        System.out.println("5. New Employee");
        System.out.println("6. Borrow Item");
        System.out.println("7. Return Item");
        System.out.println("8. Check overdues");
        System.out.println("9. Quit");
        System.out.println("");
    }

    // Events functions:

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
        SSN SSN = new SSN(ssn); // convert the String to SSN type.

        String memtype;

        while (true) {
            System.out.print("Enter Membership type (Student/Professor/External): ");
            memtype = scn.nextLine();
            if (memtype.equals("Student") || memtype.equals("student") || memtype.equals("Professor")
                    || memtype.equals("professor") || memtype.equals("External") || memtype.equals("external"))
                break;
            System.out.println("Invalid input!");
        }

        System.out.print("Creating a new member...");
        try {
            Date DOB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
            if (memtype.equals("Student") || memtype.equals("student")) {
                library.ondutyLibrarian.makeStudent(name, address, DOB, email, SSN, library);
            } else if (memtype.equals("Professor") || memtype.equals("professor"))
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

    // Checked
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

        while (true) {
            System.out.print("Enter Employee type (Librarian/Technician): ");
            memtype = scn.nextLine();
            if (memtype.equals("Librarian") || memtype.equals("librarian") || memtype.equals("Technician")
                    || memtype.equals("technician"))
                break;
            System.out.println("Invalid input!");
        }

        System.out.print("Creating a new employee...");

        // Try to create a new employee.
        try {
            Date DOB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
            if (memtype.equals("Librarian") || memtype.equals("librarian")) {
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

    public static void newItemEvent(Library library) {
        Scanner scn = new Scanner(System.in);
        System.out.println("");
        System.out.println("Enter new item info: ");
        // 1. Get collection:
        String collection;
        while (true) {
            System.out.println("Which kind of collection do you want to add to the library?");
            System.out.println("Books--B DVD---D Journal--J newspaper--N");
            collection = scn.nextLine();
            if (collection.equals("B") || collection.equals("D") || collection.equals("J") || collection.equals("N"))
                break;
            System.out.println("Invalid input!");
        }

        // 2. Get title:
        System.out.println("Enter the title:");
        String title = scn.nextLine();

        // 3. Get ISBN
        System.out.println("Enter the ISBN");
        String iSBN_String = scn.nextLine();
        try {
            int ISBN = Integer.parseInt(iSBN_String);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
        int ISBN = Integer.parseInt(iSBN_String);

        // 4. Get section:
        String sectionString;
        while (true) {
            System.out.println("Which section does this item belong to?");
            System.out.println("Art--A   Science--S  Law--L  newspaper--N");
            sectionString = scn.nextLine();
            if (sectionString.equals("A") || sectionString.equals("S") || sectionString.equals("L")
                    || sectionString.equals("N"))
                break;
            System.out.println("Invalid input!");
        }
        Section section;
        if (sectionString.equals("A")) {
            section = Section.ARTS;
        } else if (sectionString.equals("S")) {
            section = Section.SCIENCES;
        } else if (sectionString.equals("L")) {
            section = Section.LAWS;
        } else {
            section = Section.NEWSPAPERS;
        }

        // 5. get price
        System.out.println("Enter the price:");
        String priceString = scn.nextLine();
        try {
            float price = Float.parseFloat(priceString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
        float price = Float.parseFloat(priceString);

        // Construct the item according to its collection and add it to the library
        // (corresponding hash table)
        if (collection.equals("B")) {
            Book new_book = new Book(title, ISBN, section, price);
            library.ondutyTechnician.addItemToLibrary(new_book);
        } else if (collection.equals("D")) {
            DVD new_DVD = new DVD(title, ISBN, section, price);
            library.ondutyTechnician.addItemToLibrary(new_DVD);
        } else if (collection.equals("J")) {
            Journal new_jJournal = new Journal(title, ISBN, section, price);
            library.ondutyTechnician.addItemToLibrary(new_jJournal);
        } else {
            Newspaper new_nNewspaper = new Newspaper(title, ISBN, section, price);
            library.ondutyTechnician.addItemToLibrary(new_nNewspaper);
        }
    };

    public static void newRemoveMemberEvent(Library library) {
        System.out.println("Enter the name of the member who you want to remove:");
        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();
        library.removeMember(name);
    }

    public static void newRemoveItemEvent(Library library) {
        Scanner scn = new Scanner(System.in);
        System.out.println("");
        System.out.println("Enter new item info: ");

        // 2. Get title:
        System.out.println("Enter the title:");
        String title = scn.nextLine();

        // 2. Get section:
        String sectionString;
        while (true) {
            System.out.println("Which section does this item belong to?");
            System.out.println("Art--A   Science--S  Law--L  newspaper--N");
            sectionString = scn.nextLine();
            if (sectionString.equals("A") || sectionString.equals("S") || sectionString.equals("L")
                    || sectionString.equals("N"))
                break;
            System.out.println("Invalid input!");
        }
        Section section;
        if (sectionString.equals("A")) {
            section = Section.ARTS;
        } else if (sectionString.equals("S")) {
            section = Section.SCIENCES;
        } else if (sectionString.equals("L")) {
            section = Section.LAWS;
        } else {
            section = Section.NEWSPAPERS;
        }

        // 3. get the item according to section
        LibraryCollection item = library.getItem(section, title);

        // 4. on duty technician delete the item from library
        library.ondutyLibrarian.removeItemFromCollection(item);

    }

    public static void newBorrowsEvent(Library library) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the name of the member:");
        String memberName = scn.nextLine();
        Member borrower = library.getMember(memberName);

        // get the section
        String sectionString;
        while (true) {
            System.out.println("Which section does this item belong to?");
            System.out.println("Art--A   Science--S  Law--L  newspaper--N");
            sectionString = scn.nextLine();
            if (sectionString.equals("A") || sectionString.equals("S") || sectionString.equals("L")
                    || sectionString.equals("N"))
                break;
            System.out.println("Invalid input!");
        }
        Section section;
        if (sectionString.equals("A")) {
            section = Section.ARTS;
        } else if (sectionString.equals("S")) {
            section = Section.SCIENCES;
        } else if (sectionString.equals("L")) {
            section = Section.LAWS;
        } else {
            section = Section.NEWSPAPERS;
        }

        // get the name of the item:
        System.out.println("Enter the name:");
        String name = scn.nextLine();

        LibraryCollection item = library.getItem(section, name);

        borrower.borrowItem(library, item);

        // System.out.println("Enter the ISBN:");
        // String ISBN = scn.nextLine();

        // System.out.println("Enter the price");
        // String priceString = scn.nextLine();
        // try{
        // float price = Float.parseFloat(priceString);
        // }catch(NumberFormatException e){
        // System.out.println("Invalid Input! The input need to be a float number.");
        // }
        // float price = Float.parseFloat(priceString);

        // // Book book_borrowed=new lBook(title,ISBN,section,price);
        // if(sectionString.equals("A")){
        // library.artSection.Books
        // }else if(sectionString.equals("S")){
        // section = Section.SCIENCES;
        // }else if(sectionString.equals("L")){
        // section = Section.LAWS;
        // }else{
        // section = Section.NEWSPAPERS;
        // }
    };

    public static void newReturnEvent(Library library) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the name of the member:");
        String memberName = scn.nextLine();
        Member borrower = library.getMember(memberName);

        // get the section
        String sectionString;
        while (true) {
            System.out.println("Which section does this item belong to?");
            System.out.println("Art--A   Science--S  Law--L  newspaper--N");
            sectionString = scn.nextLine();
            if (sectionString.equals("A") || sectionString.equals("S") || sectionString.equals("L")
                    || sectionString.equals("N"))
                break;
            System.out.println("Invalid input!");
        }
        Section section;
        if (sectionString.equals("A")) {
            section = Section.ARTS;
        } else if (sectionString.equals("S")) {
            section = Section.SCIENCES;
        } else if (sectionString.equals("L")) {
            section = Section.LAWS;
        } else {
            section = Section.NEWSPAPERS;
        }

        // get the name of the item:
        System.out.println("Enter the name:");
        String name = scn.nextLine();

        LibraryCollection item = library.getItem(section, name);

        borrower.returnItem(item);
    }

    public static void newCheckOverdues() {
    };

    // You are free to implememnt other events that you see needs to be implemented
    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("Library must have a librarian and technician to be used!"); // Preconditions for Library

        // Add 2 employee: Librarian and Technician
        ProjectMain.newEmployeeEvent(library);
        ProjectMain.newEmployeeEvent(library);

        // Add 3 members ( test borrow etc)
        ProjectMain.newMemberEvent(library);
        ProjectMain.newMemberEvent(library);
        ProjectMain.newMemberEvent(library);

        Hashtable<Integer, Member> memberTable = library.getMemberTable();
        Hashtable<Integer, Employee> employeeTable = library.getEmployeeTable();

        System.out.println("Employees:");
        for (Employee employee : employeeTable.values()) {
            System.out.println(employee.name);
        }
        System.out.println("");

        System.out.println("Members:");
        for (Member member : memberTable.values()) {
            System.out.println(member.name);
        }
        System.out.println("");

        // Test remove a member.
        System.out.println("Test to remove a member:");
        ProjectMain.newRemoveMemberEvent(library);
        System.out.println("");

        System.out.println("Member after deletion:");
        for (Member member : memberTable.values()) {
            System.out.println(member.name);
        }
        System.out.println("");

        Scanner scanner = new Scanner(System.in);
        int option;
        while (true) {
            System.out.println("");
            ProjectMain.mainMenu();
            System.out.print("Enter your option number: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    ProjectMain.newMemberEvent(library);
                    break;
                case 2:
                    ProjectMain.newItemEvent(library);
                    break;
                case 3:
                    ProjectMain.newRemoveMemberEvent(library);
                    break;
                case 4:
                    ProjectMain.newRemoveItemEvent(library);
                    break;
                case 5:
                    ProjectMain.newEmployeeEvent(library);
                    break;
                case 6:
                    ProjectMain.newBorrowsEvent(library);
                    break;
                case 7:
                    ProjectMain.newReturnEvent(library);
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
