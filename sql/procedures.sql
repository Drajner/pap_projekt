CREATE OR REPLACE PROCEDURE drop_unassigned_patient
AS  
BEGIN
    FOR to_delete_pesel IN (SELECT p.pesel FROM patients p WHERE NOT EXISTS( SELECT * FROM appointments a WHERE p.pesel = a.patient))
    LOOP
        DELETE FROM patients WHERE pesel = to_delete_pesel.pesel;
    END LOOP;
END;
/

CREATE OR REPLACE PROCEDURE delete_late_appointments
AS
BEGIN
    FOR late_appointment IN (SELECT * FROM appointments WHERE time < SYSDATE)
    LOOP
        DELETE FROM appointments WHERE appointment_id = late_appointment.appointment_id;
    END LOOP;
    
END;
/
