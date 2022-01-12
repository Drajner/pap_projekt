package edu.iipw.pap;

import java.time.LocalDate;

public class TableRow {
    private Appointment appointment;
    private Integer index;
    private LocalDate date;
    private String name;
    private String address;

    public TableRow(Appointment appointment, int index) {
        this.appointment = appointment;
        this.index = index;
        this.date = appointment.getTimeOfAppointment().toLocalDate();
        this.name = appointment.getPatient().getName() + " " + appointment.getPatient().getSurname();
        this.address = appointment.getOfficeId();
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
