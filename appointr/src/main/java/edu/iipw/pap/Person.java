package edu.iipw.pap;

import java.time.LocalDate;
import java.time.Period;

/**
 * An abstract class representing a person.
 */
public abstract class Person {
    /**
     * The person's first name.
     */
    private String name;
    /**
     * The person's surname.
     */
    private String surname;
    /**
     * The person's date of birth.
     */
    private LocalDate dateOfBirth;
    /**
     * The person's pesel.
     */
    private String pesel;
    /**
     * The person's gender.
     */
    private Character gender;


    /**
     * Constructor for the Person class.
     *
     * @param pesel       the person's pesel.
     * @param name        the person's first name.
     * @param surname     the person's surname.
     * @param dateOfBirth the person's date of birth.
     * @param gender      the person's gender.
     */
    public Person(String pesel, String name, String surname, LocalDate dateOfBirth, Character gender) {
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    /**
     * Returns the person's pesel.
     *
     * @return the person's pesel.
     */
    public String getPesel() {
        return pesel;
    }

    /**
     * Sets the person's pesel.
     *
     * @param pesel the person's pesel.
     */
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    /**
     * Returns the person's first name.
     *
     * @return the person's first name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the person's first name.
     *
     * @param name the person's first name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the person's surname.
     *
     * @return the person's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the person's surname.
     *
     * @param surname the person's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the person's date of birth.
     *
     * @return the person's date of birth.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the person's date of birth.
     *
     * @param dateOfBirth the person's date of birth.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the person's full name.
     *
     * @return the person's full name.
     */
    public String getFullName() {
        return name + ' ' + surname;
    }

    /**
     * Returns the person's age.
     *
     * @return the person's age.
     */
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Returns the person's gender.
     *
     * @return the person's gender.
     */
    public Character getGender() {
        return gender;
    }

    /**
     * Sets the person's gender.
     *
     * @param gender the person's gender.
     */
    public void setGender(Character gender) {
        this.gender = gender;
    }

    /**
     * Returns a string representation of the person.
     *
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        return name + ' ' + surname;
    }

}
