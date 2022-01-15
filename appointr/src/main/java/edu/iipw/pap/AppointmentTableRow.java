package edu.iipw.pap;

import java.time.format.DateTimeFormatter;

public class AppointmentTableRow {
    private Appointment appointment;
    private Integer index;
    private String date;
    private String name;
    private int office;

    public AppointmentTableRow(Appointment appointment, int index) {
        this.appointment = appointment;
        this.index = index;
        this.date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(appointment.getTimeOfAppointment());
        this.name = appointment.getPatient().getName() + " " + appointment.getPatient().getSurname();
        this.office = appointment.getOfficeId();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

}
