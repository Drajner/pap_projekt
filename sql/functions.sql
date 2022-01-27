CREATE OR REPLACE FUNCTION calculate_payment(doc_pesel VARCHAR2, pay_for_appointment NUMBER)
RETURN NUMBER
AS
    v_payment NUMBER;
BEGIN
    v_payment := 0;

    SELECT COUNT(appointment_id)
    INTO v_payment
    FROM appointments
    WHERE doctor = doc_pesel;
    
    v_payment := v_payment * pay_for_appointment;
    
    RETURN v_payment;
END;
/

CREATE OR REPLACE FUNCTION calculate_age(p_pesel VARCHAR2)
RETURN NUMBER
AS
    v_birth_date DATE;
    v_age   NUMBER;
    v_is_doctor NUMBER;
BEGIN
    
    v_age := 0;
    v_is_doctor := 0;
    
    SELECT COUNT(*)
    INTO v_is_doctor
    FROM doctors
    WHERE p_pesel = pesel;
    
    IF v_is_doctor = 1 THEN
        SELECT birth_date
        INTO v_birth_date
        FROM doctors
        WHERE p_pesel = pesel;
        
        v_age := extract (year FROM SYSDATE) - extract (year FROM v_birth_date);
    ELSE
        SELECT birth_date
        INTO v_birth_date
        FROM patients
        WHERE p_pesel = pesel;
        
        v_age := extract (year FROM SYSDATE) - extract (year FROM v_birth_date);
    END IF;
    
    RETURN v_age;
END;
