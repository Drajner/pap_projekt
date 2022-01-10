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
        Patient patient1 = new Patient("Kamil", "Nowak", LocalDate.parse("2001-05-03"), "Cysts on the liver");  // 01250356956
        Patient patient2 = new Patient("Adam", "Durham", LocalDate.parse("1997-04-26"), "Severe toothache");  // 97042678331
        Patient patient3 = new Patient("Adam", "Drzewicki", LocalDate.parse("1990-06-07"), "Tummy ache");  // 90060798254
        Patient patient4 = new Patient("Kacper", "Janicki", LocalDate.parse("1958-02-08"), "Temporal blindness");  // 58020800634
        Patient patient5 = new Patient("Aleksandra", "Piątek", LocalDate.parse("1983-06-27"), "No additional information given");  // 83062780123
        Patient patient6 = new Patient("Adolf", "Wierzbicki", LocalDate.parse("1974-12-05"), "Broken fibula");  // 74120570156
        Patient patient7 = new Patient("Waldemar", "Górecki", LocalDate.parse("1960-03-29"), "Leukemia");  // 60032978694
        Patient patient8 = new Patient("Bohdan", "Dobrowolski", LocalDate.parse("1965-07-10"), "Elevated cholesterol");  // 65071003794
        Patient patient9 = new Patient("Bronisław", "Domański", LocalDate.parse("1978-11-10"), "Clinical depression");  // 78111075053
        Patient patient10 = new Patient("Zenon", "Sowa", LocalDate.parse("1997-06-01"), "Childhood epilepsy");  // 97060136732

        Doctor doctor1 = new Doctor("Jacek", "Kowalski", LocalDate.parse("1970-01-01"), "Optometrist", "jacek", "kEcAj", new ArrayList<Appointment>());  // 70010152275
        Doctor doctor2 = new Doctor("John", "Moore", LocalDate.parse("1980-07-17"), "Dentist", "qwerty", "password", new ArrayList<Appointment>());  // 80071732176
        Doctor doctor3 = new Doctor("Bogusław", "Wróbel", LocalDate.parse("1995-03-12"), "Physician", "mWf{w?3.kd}a5j!C", "d9kq>c'8{^dGhg+F", new ArrayList<Appointment>());  // 95031266310
        Doctor doctor4 = new Doctor("Bogumiła", "Polak", LocalDate.parse("1976-11-12"), "Medical apprentice", "tw8yuTAZN4=UmB{C", "2aZdcjnb_^rm*r_(", new ArrayList<Appointment>());  // 76111249041
        Doctor doctor5 = new Doctor("Alina", "Makowska", LocalDate.parse("1978-05-19"), "Medical apprentice", "TqA*w+3a?^Jr2F7[", "TdAY6#>&UU^<}~wJ", new ArrayList<Appointment>());  // 78051972283
        Doctor doctor6 = new Doctor("Matylda", "Domańska", LocalDate.parse("1958-04-13"), "Physician", "a?*(/MDwd%Lh2<!m", ",6*}2[4vFz:vHN(w", new ArrayList<Appointment>());  // 58041304863
        Doctor doctor7 = new Doctor("Błażej", "Kopeć", LocalDate.parse("1957-12-01"), "Dietician", "r}*)QSqC(gDYny6@", "Rb>kjE7*pHT,ns7/", new ArrayList<Appointment>());  // 57120125151
        Doctor doctor8 = new Doctor("Nicola", "Stefańska", LocalDate.parse("1997-02-03"), "Physician", "%zg9HrFV(:F&]6Lh", "5csGKyfWtm.f\\W_j", new ArrayList<Appointment>());  // 97020371241
        Doctor doctor9 = new Doctor("Andżelika", "Nowakowska", LocalDate.parse("1970-04-09"), "Neurologist", "V\\\\YD^3g8vh5tG'm", "c+}W}8k)Y/^U8q4]", new ArrayList<Appointment>());  // 70040923184
        Doctor doctor10 = new Doctor("Oliwia", "Wojciechowska", LocalDate.parse("1958-05-01"), "Oncologist", "_S]+ygM2.]M=2k3]", "z?;3Lvs@Sxx{LK!)", new ArrayList<Appointment>());  // 58050153827

        Appointment appointment1 = new Appointment(doctor3, patient1, LocalDateTime.parse("2021-12-02T10:15:00"), "Baker Street 221B");
        Appointment appointment2 = new Appointment(doctor2, patient2, LocalDateTime.parse("2021-12-02T10:15:00"), "Baker Street 222A");
        Appointment appointment3 = new Appointment(doctor4, patient3, LocalDateTime.parse("2021-11-04T09:15:00"), "Baker Street 221A");
        Appointment appointment4 = new Appointment(doctor1, patient4, LocalDateTime.parse("2021-07-07T08:15:00"), "Baker Street 221C");
        Appointment appointment5 = new Appointment(doctor5, patient5, LocalDateTime.parse("2022-01-07T12:15:00"), "Baker Street 222B");
        Appointment appointment6 = new Appointment(doctor6, patient6, LocalDateTime.parse("2022-01-14T14:15:00"), "Baker Street 222B");
        Appointment appointment7 = new Appointment(doctor10, patient7, LocalDateTime.parse("2022-01-17T11:45:00"), "Baker Street 221A");
        Appointment appointment8 = new Appointment(doctor7, patient8, LocalDateTime.parse("2022-01-21T12:00:00"), "Baker Street 221B");
        Appointment appointment9 = new Appointment(doctor8, patient9, LocalDateTime.parse("2022-01-27T17:15:00"), "Baker Street 222A");
        Appointment appointment10 = new Appointment(doctor9, patient10, LocalDateTime.parse("2022-01-30T16:00:00"), "Baker Street 221C");

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
    }

}
