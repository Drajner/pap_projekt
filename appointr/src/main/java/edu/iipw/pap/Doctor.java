package edu.iipw.pap;

import java.time.LocalDate;

public class Doctor extends Person{
    private String specialization;
    private String login;
    private String password;

    public Doctor(String name, String surname, LocalDate dateOfBirth, String specialization, String login, String password) {
        super(name, surname, dateOfBirth);
        this.specialization = specialization;
        this.login = login;
        this.password = password;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Appointment createAppointment(Patient patient, LocalDate timeOfAppointment, String address) {
        return new Appointment(this, patient, timeOfAppointment, address);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "specialization='" + specialization + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
