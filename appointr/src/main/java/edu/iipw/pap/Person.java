package edu.iipw.pap;

import java.time.LocalDate;
import java.time.Period;

/** An abstract class representing a person. */
public abstract class Person {
    /** The person's first name. */
    private String name;
    /** The person's surname. */
    private String surname;
    /** The person's date of birth. */
    private LocalDate dateOfBirth;

    /**
     * Constructor for the Person class.
     * @param name the person's first name.
     * @param surname the person's surname.
     * @param dateOfBirth the person's date of birth.
     */
    public Person(String name, String surname, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets the person's first name.
     * @param name The person's first name.
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the person's surname.
     * @param surname The person's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Sets the person's date of birth.
     * @param dateOfBirth The person's date of birth.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the person's first name.
     * @return the person's first name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the person's surname.
     * @return the person's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the person's date of birth.
     * @return the person's date of birth.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Returns the person's full name.
     * @return the person's full name.
     */
    public String getFullName() {
        return name + " " + surname;
    }

    /**
     * Returns the person's age.
     * @return the person's age.
     */
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Returns a string representation of the person.
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        return "Person{" +
                "name=\"" + name + '\"' +
                ", surname=\"" + surname + '\"' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}
