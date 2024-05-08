class Person {
    //create the variables
    String name;
    String surname;
    String email;

    //Constructor
    Person(String name,String surname,String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //Getters
    public  String getName() {

        return name;
    }

    public  String getSurname() {

        return surname;
    }

    public String getEmail() {

        return email;
    }


}
