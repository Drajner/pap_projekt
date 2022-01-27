create or replace TRIGGER tg_add_id_app
BEFORE INSERT ON appointments
FOR EACH ROW
DECLARE
    v_highest_id    NUMBER;
BEGIN
    SELECT MAX(appointment_id)
    INTO v_highest_id
    FROM appointments;

    :new.appointment_id := v_highest_id + 1;
END;
