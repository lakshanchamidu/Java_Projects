import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

class PlaneManagement {

    private static int[][] seat_list = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    static Ticket[] ticketsold = new Ticket[54];
//Task 01
    public static void main(String[] args) {
//repeat x variable 9 times
        String x = " ";
        String x_title = x.repeat(9);
        System.out.println("\n" + x_title + "Welcome to the Plane Management" + x_title);
        String y = " ";
        String a = "*";
        String menu_start = a.repeat(49);
        System.out.println("\n" + menu_start);
        String y_start = y.repeat(17);
        System.out.println("\n*" + y_start + "MENU OPTIONS" + y_start + "*");
        System.out.println("\n" + menu_start);
        //Print the Main menu
        System.out.println("\n1) Buy a seat");
        System.out.println("\n2) Cancel a Seat");
        System.out.println("\n3) Find First Available Seat ");
        System.out.println("\n4) Show Seating plan");
        System.out.println("\n5) Print Tickets information and total sales");
        System.out.println("\n6) Search Ticket");
        System.out.println("\n0) Quit");
        System.out.println("\n" + menu_start);

        Scanner input = new Scanner(System.in);
        //Exception Handling
        try {
            //get the input and select the correct case statment.
            do {
                System.out.print("\nPlease select an option: ");
                int option = input.nextInt();

                switch (option) {
                    case 1:
                        buy_seat();
                        break;
                    case 2:
                        cancel_seat(seat_list);
                        break;
                    case 3:
                        find_first_available();
                        main(null);
                        break;
                    case 4:
                        showSeatingplan();
                        break;
                    case 5:
                        printInformation();
                        main(null);
                        break;
                    case 6:
                        searchTicket(seat_list);
                        main(null);
                        break;
                    case 0:
                        System.out.println("Exiting the Program");
                        System.exit(0);
                    default:
                        System.out.println("Enter the correct option number");
                        main(null);
                }
            }
            while (true);//while loop
        } catch (Exception e) {
            System.out.println("Enter the correct row letter and row number.");
        }
    }


    public static void buy_seat() {
        //Create the new String variable store to inputs
        Scanner input1 = new Scanner(System.in);
        try {
            System.out.println("\n----------Buying a new Ticket----------");
            //get the inputs
            System.out.print("\nEnter the Row Letter(A-D): ");
            String row_letter = input1.next().toUpperCase();
            System.out.print("Enter the seat Number(1-14): ");
            int seat_number = input1.nextInt();
            //comvert string data type to char data type
            //https:stackoverflow.com/questions/7853502/how-to-convert-parse-from-string-to-char-in-java
            int rowNumber = row_letter.charAt(0) - 'A';
            //check the seat number and row are correct
            if (rowNumber < 0 || rowNumber > 4 || rowNumber >= seat_list.length ||
                    seat_number <= 0 || seat_number > seat_list[rowNumber].length) {
                System.out.println("\nEnter the correct Seat Row and Seat Number! ");
                main(null);

            }

            if (seat_list[rowNumber][seat_number - 1] == 1) {
                System.out.println("Seat is already purchased.Select different seat.");
                main(null);
                return;
            }
            //Changen the Elemnet in Seatlist array and get the personal informations.
            seat_list[rowNumber][seat_number - 1] = 1;
            Scanner input5 = new Scanner(System.in);
            System.out.print("\nEnter the name: ");
            String name = input5.next();
            isValidName(name);
            System.out.print("Enter the SurName: ");
            String surname = input5.next();
            isValidSurname(surname);
            System.out.print("Enter the email: ");
            String email = input5.next();
            isValidEmailStrict(email);
            if (isValidEmailStrict(email) && isValidName(name) && isValidSurname(surname)) {
                System.out.println();
            } else {
                System.out.println("Invalid email,name or surname format.");
                buy_seat();
            }
            System.out.println("\nSeat is successfully purchased.");
            //call the method
            AppendDataToArray(name,surname,email,row_letter,seat_number,prices(seat_number));
            main(null);
        } catch (Exception e) {
            System.out.println("Enter the seat number and Row number correctly.");
        }
    }


    public static void cancel_seat(int[][] seat_list) {
        Scanner input2 = new Scanner(System.in);
        try {
            System.out.println("\n----------Cancel a seat----------");
            //get the inputs
            System.out.print("\nEnter the Row Letter you want to cancel: ");
            String cancel_row_letter = input2.next().toUpperCase();
            System.out.print("Enter the seat number you want to cancel: ");
            int cancel_seat_number = input2.nextInt();
            //convert string to char data type
            int row_let = cancel_row_letter.charAt(0) - 'A';
            //check the row and seat number.
            if (row_let < 0 || row_let > 3 || row_let >= seat_list.length || cancel_seat_number <= 0 ||
                    cancel_seat_number > seat_list[row_let].length) {
                System.out.println("Enter the seat number or row correctly. ");
                cancel_seat(seat_list);
            }
            if (seat_list[row_let][cancel_seat_number - 1] == 0) {
                System.out.println("Seat is free.");
                main(null);
            }
            //Remove the sold ticket infotmations and change the indexing to 0;
            seat_list[row_let][cancel_seat_number - 1] = 0;
            if(cancel_row_letter.equals("A")) {
                ticketsold[cancel_seat_number - 1] = null;
            }if(cancel_row_letter.equals("B")) {
                ticketsold[14+cancel_seat_number - 1] =null;
            }if(cancel_row_letter.equals("C")) {
                ticketsold[26+cancel_seat_number - 1] = null;
            }if(cancel_row_letter.equals("D")) {
                ticketsold[38+cancel_seat_number - 1] = null;
            }
            System.out.println("Seat successfully cancel. ");
            main(null);

        } catch (Exception e) {
            System.out.println("Enter the cancel seat number and seat row correctly");
        }
    }

    //Show the seat plan using foe loops
    public static void showSeatingplan() {
        System.out.println("\n----------Seat Plan----------");
        for (int i = 0; i < seat_list.length; i++) {
            for (int j = 0; j < seat_list[i].length; j++) {
                //This line I used to ternory operater.
                //https://www.geeksforgeeks.org/java-ternary-operator-with-examples/
                System.out.print((seat_list[i][j] == 0 ? "O" : "X") + " ");
            }
            System.out.println();
        }
        main(null);
    }
    //Print the all tickets sold in seesion
    static void printInformation(){
        int EntirePrice = 0;
        for(int i =0;i<=52;i++){
            if(ticketsold[i]!=null){
                System.out.println("Ticket"+(i+1)+": ");
                System.out.println("----------Seat Information----------");
                System.out.println("Row: "+ticketsold[i].getRow_letter());
                //it using to ticket array
                System.out.println("Seat Number: "+ticketsold[i].getSeat_number());
                System.out.println("----------Personal Information----------");
                //it using to ticket array and person object
                System.out.println("Name: "+ticketsold[i].person.getName());
                System.out.println("SurName: "+ticketsold[i].person.getSurname());
                System.out.println("Email: "+ticketsold[i].person.getEmail());
                //calculate the total sales
                EntirePrice+= ticketsold[i].getPrice();
            }
        }
        System.out.println("Total Sales: "+EntirePrice);
    }
    //Finf the firat free seat in seatlist array
    public static void find_first_available() {
        for (int i = 0; i < seat_list.length; i++) {
            for (int j = 0; j < seat_list[i].length; j++) {
                if (seat_list[i][j] == 0) {
                    char row = (char) ('A' + i);
                    System.out.println("First avaialbe seat is: " + row + (j + 1));
                    break;
                }
            }
            break;
        }
    }
    static void searchTicket(int seat_list[][]) {
        Scanner input3 = new Scanner(System.in);
        //get the inputs
        System.out.print("Enter the Row you want search: ");
        String searchSeat = input3.next().toUpperCase();
        System.out.print("Enter the seat you want search: ");
        int SearchSeatNum = input3.nextInt();
        //convert string to char data type
        int rowNum = searchSeat.charAt(0) - 'A';
        int i = SearchSeatNum - 1;
        //check the seat is free or purchased
        if (seat_list[rowNum][i] == 1) {
            for (Ticket ticket : ticketsold) { //enhanved for loop https://zerotomastery.io/blog/enhanced-for-loop-java/
                if (ticket != null && ticketsold[i].getRow_letter().equalsIgnoreCase(searchSeat) &&
                        ticketsold[i].getSeat_number() == SearchSeatNum) {
                    System.out.println("Ticket found: ");
                    //print ticket informatuions
                    System.out.println("----------Seat Information----------");
                    System.out.println("\nRow: "+ticketsold[i].getRow_letter());
                    System.out.println("Seat: "+ticketsold[i].getSeat_number());
                    System.out.println("Price: "+ticketsold[i].getPrice());
                    System.out.println("----------Personal Information----------");
                    System.out.println("Name: "+ticketsold[i].person.getName());
                    System.out.println("Surname: "+ticketsold[i].person.getSurname());
                    System.out.println("Email: "+ticketsold[i].person.getEmail());
                    //break the code block
                    break;
                }
            }
        } else {
            System.out.println("Seat is Available");
        }
    }
    //Calculate the Price and return it
    static int prices(int seat_number){
        int price = 0;
        if (1<=seat_number&&seat_number<=5){
            price = 200;
        } else if (5<seat_number&&seat_number<=9) {
            price = 150;
        }else if(9<seat_number&&seat_number<=14){
            price = 0;
        }
        return price;
    }
    //create the append methos with parameters
    static void AppendDataToArray(String name,String surname,String email, String row_letter,
                                  int seat_number,int price) throws IOException {
        //create the person object
        Person person = new Person(name,surname,email);
        //call the save metnhod and append the sold ticke information to ticketsold array
        if(row_letter.equals("A")){
            ticketsold[seat_number-1] = new Ticket(row_letter,seat_number,person,price);
            ticketsold[seat_number-1].save();
        }else if(row_letter.equals("B")){
            ticketsold[14+seat_number-1] = new Ticket(row_letter,seat_number,person,price);
            ticketsold[14+seat_number-1].save();
        }else if(row_letter.equals("C")){
            ticketsold[26+seat_number-1] = new Ticket(row_letter,seat_number,person,price);
            ticketsold[26+seat_number-1].save();
        }else if(row_letter.equals("D")){
            ticketsold[38+seat_number-1] = new Ticket(row_letter,seat_number,person,price);
            ticketsold[38+seat_number-1].save();
        }
    }
    public static boolean isValidSurname(String Surname) {
        String nameRegex = "^[A-Za-z]+(?: [A-Za-z]+)*$";
        Pattern pattern = Pattern.compile(nameRegex);
        return pattern.matcher(Surname).matches();
    }
    public static boolean isValidName(String name) {
        String nameRegex = "^[A-Za-z]+(?: [A-Za-z]+)*$";
        Pattern pattern = Pattern.compile(nameRegex);
        return pattern.matcher(name).matches();
    }
    public static boolean isValidEmailStrict(String email) {
        String emailRegex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}" +
                "[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }


}
