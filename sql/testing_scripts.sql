SELECT (d.name || ' ' || d.surname) doctor, (p.name || ' ' || p.surname) patient, a.time, o.room office
FROM appointments a JOIN doctors d ON a.doctor = d.pesel JOIN patients p ON a.patient = p.pesel JOIN offices o ON a.office_id = o.office_id
WHERE d.pesel = '777';


SELECT (d.name || ' ' || d.surname) doctor, calculate_age(d.pesel) age, calculate_payment(d.pesel, 150) payment
FROM doctors d
WHERE calculate_payment(d.pesel, 150) > 0
ORDER BY calculate_age(d.pesel) DESC;


SELECT c.name, c.population, COUNT(o.office_id) offices_in_city
FROM cities c LEFT JOIN addresses a ON c.city_id = a.city_id LEFT JOIN hospitals h ON h.address = a.address_id LEFT JOIN offices o ON o.hospital_id = h.hospital_id
WHERE c.population > 3
GROUP BY c.name, c.population
ORDER BY COUNT(o.office_id) DESC;

-- aby przetestowa� procedur� drop_unasigned_patients mo�na u�y� tego selecta przed i po wywolaniu bloku kt�ry jest poni�ej, aby zaobserwowa� zmiany

SELECT (p.name || ' ' || p.surname) patient, COUNT(a.appointment_id) appointments
FROM patients p LEFT JOIN appointments a ON p.pesel = a.patient
GROUP BY (p.name || ' ' || p.surname)
ORDER BY COUNT(a.appointment_id) ASC;


BEGIN
    drop_unasigned_patients();
END;
/
-- aby przetestowa� procedur� delete_late_appointments mo�na u�y� tego selecta przed i po wywolaniu bloku kt�ry jest poni�ej, aby zaobserwowa� zmiany

SELECT  a.appointment_id, a.time, SYSDATE
FROM appointments a
ORDER BY a.time ASC;

BEGIN
    delete_late_appointments();
END;
/


