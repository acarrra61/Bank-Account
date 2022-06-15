/**
 * Instructor: Daniel Mejia, Course: CS 3331
 * Lab Assignment: Programming Assignment 4
 * Description: The purpose of the abstract class Person is to implement the attributes and
 * methods that are common to every person involved in a banking system.
 * Honesty Statement: We confirm that the work of this assignment is completely our own.
 * By turning in this assignment, we declare that we did not receive unauthorized assistance.
 * Moreover, all deliverables including, but not limited to the source code, lab report, and
 * output files were written and produced by my partner and I, alone.
 * @author Abel Carrasco and Alonso Monarrez
 * @version 1.7
 * @since July 15, 2020
 */
public abstract class Person{
    protected String firstName;
    protected String lastName;
    protected String dateOfBirth;
    protected String address;
    protected String phoneNumber;
    protected String email;

    /**
     * The default constructor of a Person. Taken from Abel Carrasco.
     */
    public Person(){

    }

    /**
     * A constructor of a Person that includes the attributes common to every person.
     * Taken from Abel Carrasco.
     * @param firstNameIn A person's first name.
     * @param lastNameIn A person's last name.
     * @param dateOfBirthIn A person's date of birth.
     * @param addressIn A person's home address.
     * @param phoneNumberIn A person's phone number.
     * @param emailIn A person's email address.
     */
    public Person(String firstNameIn, String lastNameIn, String dateOfBirthIn, String addressIn, String phoneNumberIn,
                  String emailIn){
        this.firstName = firstNameIn;
        this.lastName = lastNameIn;
        this.dateOfBirth = dateOfBirthIn;
        this.address = addressIn;
        this.phoneNumber = phoneNumberIn;
        this.email = emailIn;
    }

    /**
     * A setter method that sets a person's first name to the given parameter.
     * Taken from Abel Carrasco.
     * @param firstNameIn A person's first name.
     */
    public void setFirstName(String firstNameIn) {
        this.firstName = firstNameIn;
    }

    /**
     * A setter method that sets a person's last name to the given parameter.
     * Taken from Abel Carrasco.
     * @param lastNameIn A person's last name.
     */
    public void setLastName(String lastNameIn) {
        this.lastName = lastNameIn;
    }

    /**
     * A setter method that sets a person's date of birth to the given parameter.
     * Taken from Abel Carrasco.
     * @param dateOfBirthIn A person's date of birth.
     */
    public void setDateOfBirth(String dateOfBirthIn) {
        this.dateOfBirth = dateOfBirthIn;
    }

    /**
     * A setter method that sets a person's address to the given parameter.
     * Taken from Abel Carrasco.
     * @param addressIn A person's home address.
     */
    public void setAddress(String addressIn) {
        this.address = addressIn;
    }

    /**
     * A setter method that sets a person's phone number to the given parameter.
     * Taken from Abel Carrasco.
     * @param phoneNumberIn A person's phone number.
     */
    public void setPhoneNumber(String phoneNumberIn) {
        this.phoneNumber = phoneNumberIn;
    }

    /**
     * A setter method that sets a person's email to the given parameter.
     * Taken from Abel Carrasco.
     * @param emailIn A person's email address.
     */
    public void setEmail(String emailIn) {
        this.email = emailIn;
    }

    /**
     * A getter method that returns a person's first name. Taken from Abel Carrasco.
     * @return firstName Returns the first name of a person.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * A getter method that returns a person's last name. Taken from Abel Carrasco.
     * @return lastName Returns the last name of a person.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * A getter method that returns a person's date of birth. Taken from Abel Carrasco.
     * @return dateOfBirth Returns the date of birth of a person.
     */
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * A getter method that returns a person's address. Taken from Abel Carrasco.
     * @return address Returns the home address of a person.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * A getter method that returns a person's phone number. Taken from Abel Carrasco.
     * @return phoneNumber Returns the phone number of a person.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * A getter method that returns a person's email. Taken from Abel Carrasco.
     * @return email Returns the email address of a person.
     */
    public String getEmail() {
        return this.email;
    }
}