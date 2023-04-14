import java.util.Date;


public class DVD extends LibraryCollection {
    private int ISBN;

    DVD() {
        this.title = "";
        this.ISBN = 0;
        this.isBorrowed = false;
        this.section = Section.ARTS;
        this.price = 10;
    }
    DVD(String title, int ISBN, Section section, float price) {
        this.title = title;
        this.ISBN = ISBN;
        this.isBorrowed = false;
        this.section = section;
        this.price = price;
    }
    public void borrowItem(Library library, Member member) {
        this.isBorrowed = true;
        this.dateRented = new Date();
        this.memberInformation = member.toString();
        this.starttimer(library.getLibrarian(), member);
    }
    @Override
    public void returnItem() {
        this.isBorrowed = false;
        this.stoptimer();
    }
}
