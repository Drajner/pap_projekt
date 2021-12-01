package edu.iipw.pap;

import java.time.LocalDate;

public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private LocalDate timeOfAppointment;
    private String address;

    public Appointment(Doctor doctor, Patient patient, LocalDate timeOfAppointment, String address) {
        this.doctor = doctor;
        this.patient = patient;
        this.timeOfAppointment = timeOfAppointment;
        this.address = address;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDate getTimeOfAppointment() {
        return timeOfAppointment;
    }

    public String getAddress() {
        return address;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTimeOfAppointment(LocalDate timeOfAppointment) {
        this.timeOfAppointment = timeOfAppointment;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", timeOfAppointment=" + timeOfAppointment +
                ", address='" + address + '\'' +
                '}';
    }
}
