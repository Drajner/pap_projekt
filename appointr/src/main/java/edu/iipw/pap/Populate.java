package edu.iipw.pap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;

// https://danetestowe.pl/generator?age=2565&ageValue=&sex=no
// https://pesel.cstudios.pl/o-generatorze/generator-on-line
// https://passwordsgenerator.net/
public class Populate {
    // public ObservableList<Patient> patients;
    public ArrayList<Patient> patients;

    public ArrayList<Doctor> doctors;

    public ArrayList<Appointment> appointments;

    public Populate() {
        Patient patient1 = new Patient("1", "Kamil", "Nowak", LocalDate.parse("2001-05-03"), "Cysts on the liver", 'M');  // 01250356956
        Patient patient2 = new Patient("2", "Adam", "Durham", LocalDate.parse("1997-04-26"), "Severe toothache", 'M');  // 97042678331
        Patient patient3 = new Patient("3", "Adam", "Drzewicki", LocalDate.parse("1990-06-07"), "Tummy ache", 'M');  // 90060798254
        Patient patient4 = new Patient("4", "Kacper", "Janicki", LocalDate.parse("1958-02-08"), "Temporal blindness", 'M');  // 58020800634
        Patient patient5 = new Patient("5", "Aleksandra", "Piątek", LocalDate.parse("1983-06-27"), "No additional information given", 'K');  // 83062780123
        Patient patient6 = new Patient("6", "Adolf", "Wierzbicki", LocalDate.parse("1974-12-05"), "Broken fibula", 'M');  // 74120570156
        Patient patient7 = new Patient("7", "Waldemar", "Górecki", LocalDate.parse("1960-03-29"), "Leukemia", 'M');  // 60032978694
        Patient patient8 = new Patient("8", "Bohdan", "Dobrowolski", LocalDate.parse("1965-07-10"), "Elevated cholesterol", 'M');  // 65071003794
        Patient patient9 = new Patient("9", "Bronisław", "Domański", LocalDate.parse("1978-11-10"), "Clinical depression", 'M');  // 78111075053
        Patient patient10 = new Patient("10", "Zenon", "Sowa", LocalDate.parse("1997-06-01"), "Childhood epilepsy", 'M');  // 97060136732

        Doctor admin = new Doctor("777", "Admin", "", LocalDate.parse("1000-10-10"), "Admin", "admin", "admin", new ArrayList<>(), 'M');
        Doctor doctor1 = new Doctor("11", "Jacek", "Kowalski", LocalDate.parse("1970-01-01"), "Optometrist", "jacek", "kEcAj", new ArrayList<Appointment>(), 'M');  // 70010152275
        Doctor doctor2 = new Doctor("12", "John", "Moore", LocalDate.parse("1980-07-17"), "Dentist", "qwerty", "password", new ArrayList<Appointment>(), 'M');  // 80071732176
        Doctor doctor3 = new Doctor("13", "Bogusław", "Wróbel", LocalDate.parse("1995-03-12"), "Physician", "mWf{w?3.kd}a5j!C", "d9kq>c'8{^dGhg+F", new ArrayList<Appointment>(), 'M');  // 95031266310
        Doctor doctor4 = new Doctor("14", "Bogumiła", "Polak", LocalDate.parse("1976-11-12"), "Medical apprentice", "tw8yuTAZN4=UmB{C", "2aZdcjnb_^rm*r_(", new ArrayList<Appointment>(), 'K');  // 76111249041
        Doctor doctor5 = new Doctor("15", "Alina", "Makowska", LocalDate.parse("1978-05-19"), "Medical apprentice", "TqA*w+3a?^Jr2F7[", "TdAY6#>&UU^<}~wJ", new ArrayList<Appointment>(), 'K');  // 78051972283
        Doctor doctor6 = new Doctor("16", "Matylda", "Domańska", LocalDate.parse("1958-04-13"), "Physician", "a?*(/MDwd%Lh2<!m", ",6*}2[4vFz:vHN(w", new ArrayList<Appointment>(), 'K');  // 58041304863
        Doctor doctor7 = new Doctor("17", "Błażej", "Kopeć", LocalDate.parse("1957-12-01"), "Dietician", "r}*)QSqC(gDYny6@", "Rb>kjE7*pHT,ns7/", new ArrayList<Appointment>(), 'M');  // 57120125151
        Doctor doctor8 = new Doctor("18", "Nicola", "Stefańska", LocalDate.parse("1997-02-03"), "Physician", "%zg9HrFV(:F&]6Lh", "5csGKyfWtm.f\\W_j", new ArrayList<Appointment>(), 'K');  // 97020371241
        Doctor doctor9 = new Doctor("19", "Andżelika", "Nowakowska", LocalDate.parse("1970-04-09"), "Neurologist", "V\\\\YD^3g8vh5tG'm", "c+}W}8k)Y/^U8q4]", new ArrayList<Appointment>(), 'K');  // 70040923184
        Doctor doctor10 = new Doctor("20", "Oliwia", "Wojciechowska", LocalDate.parse("1958-05-01"), "Oncologist", "_S]+ygM2.]M=2k3]", "z?;3Lvs@Sxx{LK!)", new ArrayList<Appointment>(), 'K');  // 58050153827

        Appointment appointment1 = new Appointment(600, doctor3, patient1, LocalDateTime.parse("2021-12-02T10:15:00"), 4);
        Appointment appointment2 = new Appointment(601, doctor2, patient2, LocalDateTime.parse("2021-12-02T10:15:00"), 52);
        Appointment appointment3 = new Appointment(602, doctor4, patient3, LocalDateTime.parse("2021-11-04T09:15:00"), 56);
        Appointment appointment4 = new Appointment(603, doctor1, patient4, LocalDateTime.parse("2021-07-07T08:15:00"), 24);
        Appointment appointment5 = new Appointment(604, doctor5, patient5, LocalDateTime.parse("2022-01-07T12:15:00"), 52);
        Appointment appointment6 = new Appointment(605, doctor6, patient6, LocalDateTime.parse("2022-01-14T14:15:00"), 64);
        Appointment appointment7 = new Appointment(606, doctor10, patient7, LocalDateTime.parse("2022-01-17T11:45:00"), 36);
        Appointment appointment8 = new Appointment(607, doctor7, patient8, LocalDateTime.parse("2022-01-21T12:00:00"), 534);
        Appointment appointment9 = new Appointment(608, doctor8, patient9, LocalDateTime.parse("2022-01-27T17:15:00"), 677);
        Appointment appointment10 = new Appointment(609, doctor9, patient10, LocalDateTime.parse("2022-01-30T16:00:00"), 412);

        patients = new ArrayList<>();
        // final ObservableList<Person> patients = FXCollections.observableArrayList(
        //         patient1, patient2, patient3, patient4, patient5, patient6, patient7, patient8, patient9, patient10
        // );
        appointments = new ArrayList<>();
        doctors = new ArrayList<>();

        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        patients.add(patient4);
        patients.add(patient5);
        patients.add(patient6);
        patients.add(patient7);
        patients.add(patient8);
        patients.add(patient9);
        patients.add(patient10);

        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments.add(appointment3);
        appointments.add(appointment4);
        appointments.add(appointment5);
        appointments.add(appointment6);
        appointments.add(appointment7);
        appointments.add(appointment8);
        appointments.add(appointment9);
        appointments.add(appointment10);

        doctors.add(doctor1);
        doctors.add(doctor2);
        doctors.add(doctor3);
        doctors.add(doctor4);
        doctors.add(doctor5);
        doctors.add(doctor6);
        doctors.add(doctor7);
        doctors.add(doctor8);
        doctors.add(doctor9);
        doctors.add(doctor10);

        doctors.add(admin);
    }

}
