import  java.io.*; //import IO Pacakge
//create the class and variables
public class Ticket {
    String row_letter;
    int seat_number;
    Person person;
    double price;

//Constructor
    public Ticket(String rowLetter, int seatNumber,Person person,int price) {
        this.row_letter = rowLetter;
        this.seat_number = seatNumber;
        this.person = person;
        this.price = price;


    }
//Getters
    public String getRow_letter() {

        return row_letter;
    }

    public int getSeat_number() {

        return seat_number;
    }
    public double getPrice(){
        return price;
    }
    public Person getPerson(){
        return person;
    }

//Save the Ticket Informations and Personal Informations in text file
    public void save() throws IOException {
        FileWriter myWrite = new FileWriter(row_letter+seat_number+".txt");
        myWrite.write("------Seat Information.------");
        myWrite.write("\nRow: "+row_letter);
        myWrite.write("\nSeat Number: "+seat_number);
        myWrite.write("Price: "+price);
        myWrite.write("\n------Personal Information------");
        myWrite.write("\nName: "+person.name);
        myWrite.write("\nSurName: "+person.surname);
        myWrite.write("\nEmail: "+person.email);
        myWrite.close();
    }




}
