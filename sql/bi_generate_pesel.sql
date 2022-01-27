create or replace TRIGGER BI_GENERATE_PESEL
BEFORE INSERT ON patients
FOR EACH ROW
DECLARE PRAGMA AUTONOMOUS_TRANSACTION;
    v_pesel     VARCHAR2(15);
    v_year      NUMBER;
    v_month     NUMBER;
    v_day       NUMBER;
    v_gender    CHARACTER;
    v_control   NUMBER;
    v_gender_s  NUMBER;
    v_sum       NUMBER;

    TYPE weightsarray IS VARRAY(15) OF NUMBER;
    v_weights   weightsarray;
    v_pesel_arr weightsarray;
BEGIN
    v_sum := 0;
    v_pesel := '';
    v_pesel_arr := weightsarray(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    v_weights := weightsarray(1, 3, 7, 9, 1, 3, 7, 9, 1, 3);

    v_year := EXTRACT(year FROM :new.birth_date);
    v_month := EXTRACT(month FROM :new.birth_date);
    v_day := EXTRACT(day FROM :new.birth_date);
    v_gender := :new.gender;

    IF v_year < 2000 THEN
        v_pesel_arr(1) := MOD(FLOOR(v_year / 10), 10);
        v_pesel_arr(2) := MOD(v_year, 10);
    ELSE
        v_pesel_arr(1) := MOD(FLOOR(v_year / 10), 10) + 2;
        v_pesel_arr(2) := MOD(v_year, 10);
    END IF;

    IF v_month < 10 THEN
        v_pesel_arr(3) := 0;
        v_pesel_arr(4) := MOD(v_month, 10);
    ELSE
        v_pesel_arr(3) := FLOOR(v_month / 10);
        v_pesel_arr(4) := MOD(v_month, 10);
    END IF;

    IF v_day < 10 THEN
        v_pesel_arr(5) := 0;
        v_pesel_arr(6) := MOD(v_day, 10);
    ELSE
        v_pesel_arr(5) := FLOOR(v_day / 10);
        v_pesel_arr(6) := MOD(v_day, 10);
    END IF;

    IF v_gender = 'K' THEN
        v_gender_s := DBMS_RANDOM.VALUE(500, 4999) * 2;
    ELSE
        v_gender_s := DBMS_RANDOM.VALUE(500, 4999) * 2 + 1;
    END IF;
    v_pesel_arr(7) := FLOOR(v_gender_s / 1000);
    v_pesel_arr(8) := MOD(FLOOR(v_gender_s / 100), 10);
    v_pesel_arr(9) := MOD(FLOOR(v_gender_s / 10), 10);
    v_pesel_arr(10) := MOD(v_gender_s, 10);

    v_sum := v_pesel_arr(1) * v_weights(1)
             + v_pesel_arr(2) * v_weights(2)
             + v_pesel_arr(3) * v_weights(3)
             + v_pesel_arr(4) * v_weights(4)
             + v_pesel_arr(5) * v_weights(5)
             + v_pesel_arr(6) * v_weights(6)
             + v_pesel_arr(7) * v_weights(7)
             + v_pesel_arr(8) * v_weights(8)
             + v_pesel_arr(9) * v_weights(9)
             + v_pesel_arr(10) * v_weights(10)
             + v_pesel_arr(11) * v_weights(11);

    v_control := 10 - MOD(v_sum, 10);
    v_pesel_arr(11) := v_control;

    v_pesel := TO_CHAR(v_pesel_arr(1)) || TO_CHAR(v_pesel_arr(2)) || TO_CHAR(v_pesel_arr(3)) || TO_CHAR(v_pesel_arr(4)) ||
               TO_CHAR(v_pesel_arr(5)) || TO_CHAR(v_pesel_arr(6)) || TO_CHAR(v_pesel_arr(7)) || TO_CHAR(v_pesel_arr(8)) ||
               TO_CHAR(v_pesel_arr(9)) || TO_CHAR(v_pesel_arr(10)) || TO_CHAR(v_pesel_arr(11));

    :new.pesel := v_pesel;
END;
