INSERT INTO regions VALUES (NULL, 'Europa', 'EU');
INSERT INTO regions VALUES (NULL, 'Afryka', 'AF');
INSERT INTO regions VALUES (NULL, 'Ameryka Północna', 'AN');
INSERT INTO regions VALUES (NULL, 'Ameryka Środkowa', NULL);
INSERT INTO regions VALUES (NULL, 'Ameryka Południowa', 'AS');
INSERT INTO regions VALUES (NULL, 'Azja', 'AS');
INSERT INTO regions VALUES (NULL, 'Australia', 'AU');

COMMIT;

-----------------------------

INSERT INTO countries VALUES (1, 'Wielka Brytania', NULL, 66.6, 'angielski', 'GBP', '.uk', 'GB', '+44');
INSERT INTO countries VALUES (2, 'Niemcy', NULL, 83.02, 'niemiecki', 'EUR', '.de', 'DE', '+49');
INSERT INTO countries VALUES (3, 'Francja', NULL, 66.99, 'francuski', 'EUR', '.fr', 'FR', '+33');
INSERT INTO countries VALUES (4, 'Polska', NULL, 37.97, 'polski', 'PLN', '.pl', 'PL', '+48');
INSERT INTO countries VALUES (5, 'Czechy', NULL, 10.69, 'czeski', 'CZK', '.cz', 'CZ', '+420');
INSERT INTO countries VALUES (6, 'Litwa', NULL, 2.79, 'litewski', 'EUR', '.lt', 'LT', '+370');
INSERT INTO countries VALUES (7, 'Ukraina', NULL, 41.98, 'ukraiński', 'UAH', '.ua', 'UA', '+380');
INSERT INTO countries VALUES (8, 'Słowacja', NULL, 5.45, 'słowacki', 'EUR', '.sk', 'SK', '+421');
INSERT INTO countries VALUES (9, 'Norwegia', NULL, 5.43, 'norweski', 'NOK', '.no', 'NO', '+47');
INSERT INTO countries VALUES (10, 'Szwecja', NULL, 10.23, 'szwedzki', 'SEK', '.se', 'SE', '+46');
INSERT INTO countries VALUES (11, 'Finlandia', NULL, 5.58, 'fiński', 'EUR', '.fi', 'FI', '+358');
INSERT INTO countries VALUES (12, 'Hiszpania', NULL, 46.94, 'hiszpański', 'EUR', '.es', 'E', '+34');
INSERT INTO countries VALUES (13, 'Republika Południowej Ameryki', NULL, 57.78, 'angielski', 'ZAR', '.za', NULL, NULL);
INSERT INTO countries VALUES (14, 'Egipt', NULL, 98.42, 'arabski', 'EGP', '.eg', 'EG', '+20');
INSERT INTO countries VALUES (15, 'Izrael', NULL, 8.88, 'hebrajski', 'ILS', '.il', 'IL', '+972');
INSERT INTO countries VALUES (16, 'Irak', NULL, 38.43, 'arabski', 'IQD', NULL, 'IQD', NULL);
INSERT INTO countries VALUES (17, 'Turcja', NULL, 82 ,'turecki' , 'TRY', '.tr', 'TR', NULL);
INSERT INTO countries VALUES (18, 'Stany Zjednoczone Ameryki', NULL, 328.2, 'angielski', 'USD', '.us', 'US', '+1');
INSERT INTO countries VALUES (19, 'Kanada', NULL, 37.59, 'angielski', 'CAD', '.ca', 'CA', '+1');
INSERT INTO countries VALUES (20, 'Brazylia', NULL, 209.5, 'portugalski', 'BRL', NULL, NULL, NULL);
INSERT INTO countries VALUES (21, 'Argentyna', NULL, 44.49, 'hiszpański', 'ARS', NULL, NULL, NULL);
INSERT INTO countries VALUES (22, 'Chińska Republika Ludowa', NULL, 1393, 'chiński', 'CNY', NULL, NULL, NULL);
INSERT INTO countries VALUES (23, 'Japonia', NULL, 126.5, 'japoński', 'JPY', '.jp', 'JP', '+81');
INSERT INTO countries VALUES (24, 'Korea Południowa', NULL, 51.64, 'koreański', 'KRW', '.kr', 'KR', '+82');
INSERT INTO countries VALUES (25, 'Rosja', NULL, 146, 'rosyjski', 'RUB', '.ru', 'RUS', '+7');
INSERT INTO countries VALUES (26, 'Gruzja', NULL, 4, 'gruziński', 'GEL', NULL, NULL, NULL);

COMMIT;

----------------------

INSERT INTO reg_countries VALUES (1, 1, 1);
INSERT INTO reg_countries VALUES (2, 1, 2);
INSERT INTO reg_countries VALUES (3, 1, 3);
INSERT INTO reg_countries VALUES (4, 1, 4);
INSERT INTO reg_countries VALUES (5, 1, 5);
INSERT INTO reg_countries VALUES (6, 1, 6);
INSERT INTO reg_countries VALUES (7, 1, 7);
INSERT INTO reg_countries VALUES (8, 1, 8);
INSERT INTO reg_countries VALUES (9, 1, 9);
INSERT INTO reg_countries VALUES (10, 1, 10);
INSERT INTO reg_countries VALUES (11, 1, 11);
INSERT INTO reg_countries VALUES (12, 1, 12);
INSERT INTO reg_countries VALUES (13, 2, 13);
INSERT INTO reg_countries VALUES (14, 2, 14);
INSERT INTO reg_countries VALUES (15, 6, 15);
INSERT INTO reg_countries VALUES (16, 6, 16);
INSERT INTO reg_countries VALUES (17, 6, 17);
INSERT INTO reg_countries VALUES (18, 3, 18);
INSERT INTO reg_countries VALUES (19, 3, 19);
INSERT INTO reg_countries VALUES (20, 5, 20);
INSERT INTO reg_countries VALUES (21, 5, 21);
INSERT INTO reg_countries VALUES (22, 6, 22);
INSERT INTO reg_countries VALUES (23, 6, 23);
INSERT INTO reg_countries VALUES (24, 6, 24);
INSERT INTO reg_countries VALUES (25, 6, 25);
INSERT INTO reg_countries VALUES (26, 6, 26);

COMMIT;

--------------------

INSERT INTO cities VALUES (1, 'Londyn', 8.9, 1);
INSERT INTO cities VALUES (2, 'Berlin', 3.6, 2);
INSERT INTO cities VALUES (3, 'Paryż', 2.1, 3);
INSERT INTO cities VALUES (4, 'Warszawa', 1.8, 4);
INSERT INTO cities VALUES (5, 'Praga', 1.3, 5);
INSERT INTO cities VALUES (6, 'Wilno', 0.6, 6);
INSERT INTO cities VALUES (7, 'Kijów', 2.9, 7);
INSERT INTO cities VALUES (8, 'Bratysława', 0.4, 8);
INSERT INTO cities VALUES (9, 'Oslo', 0.7, 9);
INSERT INTO cities VALUES (10, 'Sztokholm', 1, 10);
INSERT INTO cities VALUES (11, 'Helsinki', 0.6, 11);
INSERT INTO cities VALUES (12, 'Madryt', 3.3, 12);
INSERT INTO cities VALUES (13, 'Kapsztad', 4, 13);
INSERT INTO cities VALUES (14, 'Kair', 9.5, 14);
INSERT INTO cities VALUES (15, 'Jerozolima', 0.9, 15);
INSERT INTO cities VALUES (16, 'Bagdad', 8.1, 16);
INSERT INTO cities VALUES (17, 'Ankara', 5.7, 17);
INSERT INTO cities VALUES (18, 'Waszyngton', 0.7, 18);
INSERT INTO cities VALUES (19, 'Ottawa', 0.93, 19);
INSERT INTO cities VALUES (20, 'Brasilia', 3, 20);
INSERT INTO cities VALUES (21, 'Buenos Aires', 3, 21);
INSERT INTO cities VALUES (22, 'Pekin', 21.9, 22);
INSERT INTO cities VALUES (23, 'Tokio', 14, 23);
INSERT INTO cities VALUES (24, 'Seul', 9.7, 24);
INSERT INTO cities VALUES (25, 'Moskwa', 12.7, 25);
INSERT INTO cities VALUES (26, 'Tbilisi', 1.2, 26);
INSERT INTO cities VALUES (27, 'Radom', 0.2, 4);
INSERT INTO cities VALUES (28, 'Sosnowiec', 0.2, 4);

COMMIT;

------------------------

INSERT INTO addresses VALUES (1, 'Ilene', 2134, 1);
INSERT INTO addresses VALUES (2, 'Bellgrove', 89359, 2);
INSERT INTO addresses VALUES (3, 'Mendota', 7460204, 3);
INSERT INTO addresses VALUES (4, 'Graceland', 54325, 4);
INSERT INTO addresses VALUES (5, 'Loftsgordon', 4566, 5);
INSERT INTO addresses VALUES (6, 'Redwing', 1685141, 6);
INSERT INTO addresses VALUES (7, 'Arapahoe', 154648, 7);
INSERT INTO addresses VALUES (8, 'Muir', 38703, 8);
INSERT INTO addresses VALUES (9, 'Golden Leaf', 66999, 9);
INSERT INTO addresses VALUES (10, 'La Follette', 359120, 10);
INSERT INTO addresses VALUES (11, 'Dakota', 235654, 11);
INSERT INTO addresses VALUES (12, 'Gerald', 1216, 12);
INSERT INTO addresses VALUES (13, 'Sloan', 24556, 13);
INSERT INTO addresses VALUES (14, 'North', 627750, 14);
INSERT INTO addresses VALUES (15, 'Cardinal', 678214, 15);
INSERT INTO addresses VALUES (16, 'Granby', 4620031, 16);
INSERT INTO addresses VALUES (17, 'Golf Course', 4567774, 17);
INSERT INTO addresses VALUES (18, 'Oxford', 335674, 18);
INSERT INTO addresses VALUES (19, '1st', 13409, 19);
INSERT INTO addresses VALUES (20, 'Vermont', 8600, 20);
INSERT INTO addresses VALUES (21, 'Hansons', 25624, 21);
INSERT INTO addresses VALUES (22, 'Laurel', 73565, 22);
INSERT INTO addresses VALUES (23, 'Main', 19285, 23);
INSERT INTO addresses VALUES (24, 'Dottie', 53843, 24);
INSERT INTO addresses VALUES (25, '1st', 33675, 25);
INSERT INTO addresses VALUES (26, 'Dovetail', 2325, 26);
INSERT INTO addresses VALUES (27, 'Oneill', 35775, 27);
INSERT INTO addresses VALUES (28, 'Oakridge', 33567, 28);

COMMIT;

--------------------

INSERT INTO doctors VALUES (1, 'Wendye', 'McKeefry', '2021-09-25', NULL, 'K');
INSERT INTO doctors VALUES (2, 'Arnuad', 'Mars', '2018-01-10', NULL, 'M');
INSERT INTO doctors VALUES (3, 'Ashton', 'Hickeringill', '2018-07-27', NULL, 'M');
INSERT INTO doctors VALUES (4, 'Grethel', 'Loughan', '2020-07-05', NULL, 'K');
INSERT INTO doctors VALUES (5, 'Pippy', 'Kidwell', '2020-12-13', NULL, 'K');
INSERT INTO doctors VALUES (6, 'Rubi', 'Seabrooke', '2019-12-28', NULL, 'K');
INSERT INTO doctors VALUES (7, 'Barclay', 'Castard', '2019-07-04', NULL, 'M');
INSERT INTO doctors VALUES (8, 'Palm', 'Jakubovicz', '2021-05-11', NULL, 'M');
INSERT INTO doctors VALUES (9, 'Ennis', 'Stonard', '2019-06-13', NULL, 'M');
INSERT INTO doctors VALUES (10, 'Lydie', 'Stillgoe', '2018-09-12', NULL, 'K');
INSERT INTO doctors VALUES (11, 'Lorette', 'Sulter', '2021-06-27', NULL, 'K');
INSERT INTO doctors VALUES (12, 'Adams', 'Dougherty', '2021-06-22', NULL, 'M');
INSERT INTO doctors VALUES (13, 'Demeter', 'Durnell', '2021-07-23', NULL, 'K');
INSERT INTO doctors VALUES (14, 'Angus', 'Tompkinson', '2018-08-01', NULL, 'M');
INSERT INTO doctors VALUES (15, 'Merilyn', 'Tring', '2017-11-13', NULL, 'K');
INSERT INTO doctors VALUES (16, 'Emory', 'Prise', '2018-12-25', NULL, 'M');
INSERT INTO doctors VALUES (17, 'Hadlee', 'Giveen', '2018-10-17', NULL, 'M');
INSERT INTO doctors VALUES (18, 'Normy', 'Bragg', '2021-01-18', NULL, 'M');
INSERT INTO doctors VALUES (19, 'Alessandra', 'Anthoine', '2019-11-30', NULL, 'K');
INSERT INTO doctors VALUES (20, 'Dominica', 'Arcase', '2017-12-13', NULL, 'K');


COMMIT;

--------------------------

INSERT INTO hospitals VALUES (1, 1, 'Gaylord-Witting', '2021-02-23', '6804432525', 1);
INSERT INTO hospitals VALUES (2, 2, 'Heller and Sons', '2018-02-28', '5422154622', 2);
INSERT INTO hospitals VALUES (3, 3, 'Fisher-Terry', '2019-12-11', '6741031782', 3);
INSERT INTO hospitals VALUES (4, 4, 'Schmeler, Harvey and Abernathy', '2019-06-16', '1603062335', 4);
INSERT INTO hospitals VALUES (5, 5, 'Collins-Kshlerin', '2019-04-15', '1296862399', 5);
INSERT INTO hospitals VALUES (6, 6, 'Haley Group', '2020-08-31', '5587446107', 6);
INSERT INTO hospitals VALUES (7, 7, 'Kreiger, Kozey and McLaughlin', '2020-05-25', '9958627442', 7);
INSERT INTO hospitals VALUES (8, 8, 'Okuneva-Bogan', '2019-08-31', '8610093697', 8);
INSERT INTO hospitals VALUES (9, 9, 'Anderson-Quitzon', '2021-03-10', '7168795333', 9);
INSERT INTO hospitals VALUES (10, 10, 'Cassin-Welch', '2020-03-20', '1989669131', 10);
INSERT INTO hospitals VALUES (11, 11, 'Bartoletti, Gerlach and Labadie', '2017-09-15', '1154307956', 11);
INSERT INTO hospitals VALUES (12, 12, 'Wolf and Sons', '2021-08-21', '4092704437', 12);
INSERT INTO hospitals VALUES (13, 13, 'Wyman, Wilkinson and Hintz', '2019-12-16', '4689869464', 13);
INSERT INTO hospitals VALUES (14, 14, 'Green, Jacobi and Olson', '2019-08-08', '9771417118', 14);

COMMIT;

--------------------

INSERT INTO offices VALUES (1, 9, 90);
INSERT INTO offices VALUES (2, 6, 52);
INSERT INTO offices VALUES (3, 13, 2);
INSERT INTO offices VALUES (4, 8, 61);
INSERT INTO offices VALUES (5, 7, 47);
INSERT INTO offices VALUES (6, 3, 54);
INSERT INTO offices VALUES (7, 8, 66);
INSERT INTO offices VALUES (8, 11, 63);
INSERT INTO offices VALUES (9, 9, 42);
INSERT INTO offices VALUES (10, 12, 62);
INSERT INTO offices VALUES (11, 10, 86);
INSERT INTO offices VALUES (12, 3, 93);
INSERT INTO offices VALUES (13, 2, 50);
INSERT INTO offices VALUES (14, 6, 87);
INSERT INTO offices VALUES (15, 3, 80);
INSERT INTO offices VALUES (16, 14, 58);
INSERT INTO offices VALUES (17, 12, 65);
INSERT INTO offices VALUES (18, 9, 12);
INSERT INTO offices VALUES (19, 2, 34);
INSERT INTO offices VALUES (20, 12, 5);
INSERT INTO offices VALUES (21, 5, 63);
INSERT INTO offices VALUES (22, 3, 11);
INSERT INTO offices VALUES (23, 4, 76);
INSERT INTO offices VALUES (24, 9, 86);
INSERT INTO offices VALUES (25, 9, 44);
INSERT INTO offices VALUES (26, 6, 67);
INSERT INTO offices VALUES (27, 7, 80);
INSERT INTO offices VALUES (28, 1, 42);
INSERT INTO offices VALUES (29, 8, 44);
INSERT INTO offices VALUES (30, 14, 100);

COMMIT;

----------------------

INSERT INTO patients VALUES (1, 'Koren', 'Pllu', '2019-09-09', 'nisl aenean lectus pellentesque', 'K');
INSERT INTO patients VALUES (2, 'Thedric', 'Phelp', '2020-02-05', 'curae duis faucibus accumsan odio', 'M');
INSERT INTO patients VALUES (3, 'Sal', 'Caville', '2020-02-25', 'in', 'K');
INSERT INTO patients VALUES (4, 'Lucia', 'Dennidge', '2020-10-02', 'imperdiet sapien', 'K');
INSERT INTO patients VALUES (5, 'Lek', 'Brislawn', '2021-01-28', 'eleifend luctus ultricies eu nibh', 'M');
INSERT INTO patients VALUES (6, 'Pascal', 'Kaming', '2021-07-12', 'libero rutrum ac lobortis vel', 'M');
INSERT INTO patients VALUES (7, 'Darb', 'Henriquet', '2020-08-09', 'convallis', 'K');
INSERT INTO patients VALUES (8, 'Dave', 'Chicco', '2020-02-24', 'amet lobortis sapien sapien', 'M');
INSERT INTO patients VALUES (9, 'Tristan', 'Cosgrave', '2020-10-10', 'consequat', 'M');
INSERT INTO patients VALUES (10, 'Gertrude', 'Matussow', '2019-06-07', 'purus eu magna vulputate', 'K');
INSERT INTO patients VALUES (11, 'Osbourn', 'Moss', '2020-09-14', 'velit eu', 'M');
INSERT INTO patients VALUES (12, 'Felicdad', 'Harrap', '2021-05-06', 'diam erat fermentum justo', 'K');
INSERT INTO patients VALUES (13, 'Shirley', 'Seary', '2020-11-08', 'vel nisl duis ac', 'K');
INSERT INTO patients VALUES (14, 'Ellis', 'Glencross', '2020-03-30', 'justo', 'M');
INSERT INTO patients VALUES (15, 'Carleen', 'Eede', '2021-03-23', 'non', 'K');
INSERT INTO patients VALUES (16, 'Doralynn', 'Maruska', '2019-10-12', 'sapien cum sociis', 'K');
INSERT INTO patients VALUES (17, 'Mirabella', 'Bilson', '2021-03-27', 'condimentum id luctus nec molestie', 'K');
INSERT INTO patients VALUES (18, 'Umberto', 'Massingberd', '2020-09-08', 'cras mi pede', 'M');
INSERT INTO patients VALUES (19, 'Xenos', 'Mangan', '2020-10-16', 'ac leo pellentesque ultrices mattis', 'M');
INSERT INTO patients VALUES (20, 'Bert', 'Eisak', '2020-08-11', 'nibh', 'K');

COMMIT;

-------------------

INSERT INTO accounts VALUES (1, 'velit', '8XBdVT7Ka', 1);
INSERT INTO accounts VALUES (2, 'parturient', 'JXxzAcbgm', 2);
INSERT INTO accounts VALUES (3, 'interdum', 'gr4w5BSXL', 3);
INSERT INTO accounts VALUES (4, 'in', 'DOBiDL9GB1j', 4);
INSERT INTO accounts VALUES (5, 'habitasse', 'OkHNfT', 5);
INSERT INTO accounts VALUES (6, 'nunc', 'kdjnnIFLa', 6);
INSERT INTO accounts VALUES (7, 'beastmaster64', 'cUxB1xDkP', 7);
INSERT INTO accounts VALUES (8, 'ipsum', 'nJ3Bkn1AuMZi', 8);
INSERT INTO accounts VALUES (9, 'erat', 'qx3ibzhpKi', 9);
INSERT INTO accounts VALUES (10, 'luctus', '3XMQfVZ', 10);
INSERT INTO accounts VALUES (11, 'pewdiepie', 'TXvxsuYjzD3K', 11);
INSERT INTO accounts VALUES (12, 'neque', '474BpL14d', 12);
INSERT INTO accounts VALUES (13, 'felis', '4q2uFGh', 13);
INSERT INTO accounts VALUES (14, 'vitae', 'ZpNwjT1l', 14);
INSERT INTO accounts VALUES (15, 'gravida', '6pAOVxip', 15);
INSERT INTO accounts VALUES (16, 'suscipit', 'NK4xIa5dqEo', 16);
INSERT INTO accounts VALUES (17, 'facilisi', 'wo1Nrf', 17);
INSERT INTO accounts VALUES (18, 'NULLa', 'NiPf7sm', 18);
INSERT INTO accounts VALUES (19, 'at', 'y8H9GoMOe6I', 19);
INSERT INTO accounts VALUES (20, 'scelerisque', '272xe2BTXGPR', 20);

COMMIT;

----------------------

INSERT INTO appointments VALUES (1, 18, 14, '22-01-13 01:34:01', 10);
INSERT INTO appointments VALUES (2, 17, 14, '22-01-13 18:43:45', 26);
INSERT INTO appointments VALUES (3, 7, 14, '22-01-13 22:37:08', 18);
INSERT INTO appointments VALUES (4, 14, 4, '22-01-13 06:09:38', 26);
INSERT INTO appointments VALUES (5, 15, 6, '22-01-13 12:41:59', 27);
INSERT INTO appointments VALUES (6, 15, 10, '22-01-13 12:45:34', 22);
INSERT INTO appointments VALUES (7, 10, 3, '22-01-13 03:47:21', 25);
INSERT INTO appointments VALUES (8, 12, 19, '22-01-13 01:29:32', 5);
INSERT INTO appointments VALUES (9, 7, 7, '22-01-13 10:52:50', 30);
INSERT INTO appointments VALUES (10, 7, 10, '22-01-13 22:27:41', 29);
INSERT INTO appointments VALUES (11, 6, 20, '22-01-13 09:29:56', 26);
INSERT INTO appointments VALUES (12, 5, 11, '22-01-13 02:10:34', 16);
INSERT INTO appointments VALUES (13, 8, 16, '22-01-13 19:57:49', 7);
INSERT INTO appointments VALUES (14, 7, 12, '22-01-13 08:14:34', 1);
INSERT INTO appointments VALUES (15, 14, 3, '22-01-13 22:27:44', 24);

COMMIT;

---------------------

INSERT INTO dependents VALUES (1, 'Amery', 'Mansbridge', '2020-01-12', 'vitae ipsum', 'M', 19);
INSERT INTO dependents VALUES (2, 'Bebe', 'Boath', '2021-06-22', 'faucibus accumsan odio', 'K', 12);
INSERT INTO dependents VALUES (3, 'Dreddy', 'Degoix', '2021-01-31', 'placerat ante', 'K', 7);
INSERT INTO dependents VALUES (4, 'Ludovika', 'Aspinal', '2019-11-04', 'augue a', 'K', 2);
INSERT INTO dependents VALUES (5, 'Creight', 'Sherrington', '2019-12-18', 'sed', 'M', 4);
INSERT INTO dependents VALUES (6, 'Karlis', 'McFarlane', '2020-03-10', 'lorem', 'M', 6);
INSERT INTO dependents VALUES (7, 'Gerhardine', 'Fevier', '2019-07-21', 'in', 'K', 7);
INSERT INTO dependents VALUES (8, 'Lilian', 'Dominick', '2021-12-24', 'nisi vulputate nonummy maecenas', 'K', 19);
INSERT INTO dependents VALUES (9, 'Willem', 'Caswell', '2020-09-27', 'nibh fusce lacus purus', 'M', 4);
INSERT INTO dependents VALUES (10, 'Orin', 'Knyvett', '2020-06-20', 'bibendum felis sed interdum', 'M', 13);
INSERT INTO dependents VALUES (11, 'Ariel', 'Saile', '2020-05-09', 'ac est lacinia', 'K', 5);
INSERT INTO dependents VALUES (12, 'Margy', 'Altamirano', '2021-10-03', 'integer pede justo lacinia eget', 'K', 15);
INSERT INTO dependents VALUES (13, 'Gardener', 'Smithe', '2020-06-24', 'fusce consequat NULLa nisl nunc', 'M', 15);
INSERT INTO dependents VALUES (14, 'Jania', 'Mordaunt', '2021-06-18', 'congue', 'K', 12);
INSERT INTO dependents VALUES (15, 'Marquita', 'Vlahos', '2019-07-25', 'aliquam augue', 'K', 18);

COMMIT;

--------------------------

INSERT INTO specializations VALUES (1, 'Research Associate', 2482, 4370, 18);
INSERT INTO specializations VALUES (2, 'Assistant Manager', 2346, 3656, 18);
INSERT INTO specializations VALUES (3, 'Health Coach II', 2744, 4052, 16);
INSERT INTO specializations VALUES (4, 'Assistant Manager', 2911, 3892, 16);
INSERT INTO specializations VALUES (5, 'Automation Specialist II', 2877, 3215, 1);
INSERT INTO specializations VALUES (6, 'Sales Associate', 2852, 3965, 15);
INSERT INTO specializations VALUES (7, 'Software Consultant', 2459, 3645, 20);
INSERT INTO specializations VALUES (8, 'VP Accounting', 2107, 3441, 6);
INSERT INTO specializations VALUES (9, 'Associate Professor', 2523, 4386, 19);
INSERT INTO specializations VALUES (10, 'Legal Assistant', 2286, 3830, 16);
INSERT INTO specializations VALUES (11, 'Computer Systems Analyst I', 2153, 4366, 12);
INSERT INTO specializations VALUES (12, 'Data Coordiator', 2191, 3520, 18);
INSERT INTO specializations VALUES (13, 'Actuary', 2602, 4450, 19);
INSERT INTO specializations VALUES (14, 'Biostatistician II', 2578, 3438, 17);
INSERT INTO specializations VALUES (15, 'Dental Hygienist', 2304, 4050, 15);

COMMIT;

UPDATE countries SET capital = 1 WHERE country_id = 1;
UPDATE countries SET capital = 2 WHERE country_id = 2;
UPDATE countries SET capital = 3 WHERE country_id = 3;
UPDATE countries SET capital = 4 WHERE country_id = 4;
UPDATE countries SET capital = 5 WHERE country_id = 5;
UPDATE countries SET capital = 6 WHERE country_id = 6;
UPDATE countries SET capital = 7 WHERE country_id = 7;
UPDATE countries SET capital = 8 WHERE country_id = 8;
UPDATE countries SET capital = 9 WHERE country_id = 9;
UPDATE countries SET capital = 10 WHERE country_id = 10;
UPDATE countries SET capital = 11 WHERE country_id = 11;
UPDATE countries SET capital = 12 WHERE country_id = 12;
UPDATE countries SET capital = 13 WHERE country_id = 13;
UPDATE countries SET capital = 14 WHERE country_id = 14;
UPDATE countries SET capital = 15 WHERE country_id = 15;
UPDATE countries SET capital = 16 WHERE country_id = 16;
UPDATE countries SET capital = 17 WHERE country_id = 17;
UPDATE countries SET capital = 18 WHERE country_id = 18;
UPDATE countries SET capital = 19 WHERE country_id = 19;
UPDATE countries SET capital = 20 WHERE country_id = 20;
UPDATE countries SET capital = 21 WHERE country_id = 21;
UPDATE countries SET capital = 22 WHERE country_id = 22;
UPDATE countries SET capital = 23 WHERE country_id = 23;
UPDATE countries SET capital = 24 WHERE country_id = 24;
UPDATE countries SET capital = 25 WHERE country_id = 25;
UPDATE countries SET capital = 26 WHERE country_id = 26;


UPDATE doctors SET spec_id = 1 WHERE pesel = 1;
UPDATE doctors SET spec_id = 2 WHERE pesel = 2;
UPDATE doctors SET spec_id = 3 WHERE pesel = 3;
UPDATE doctors SET spec_id = 4 WHERE pesel = 4;
UPDATE doctors SET spec_id = 5 WHERE pesel = 5;
UPDATE doctors SET spec_id = 6 WHERE pesel = 6;
UPDATE doctors SET spec_id = 7 WHERE pesel = 7;
UPDATE doctors SET spec_id = 8 WHERE pesel = 8;
UPDATE doctors SET spec_id = 9 WHERE pesel = 9;
UPDATE doctors SET spec_id = 10 WHERE pesel = 10;
UPDATE doctors SET spec_id = 11 WHERE pesel = 11;
UPDATE doctors SET spec_id = 12 WHERE pesel = 12;
UPDATE doctors SET spec_id = 13 WHERE pesel = 13;
UPDATE doctors SET spec_id = 14 WHERE pesel = 14;
UPDATE doctors SET spec_id = 15 WHERE pesel = 15;
UPDATE doctors SET spec_id = 1 WHERE pesel = 16;
UPDATE doctors SET spec_id = 2 WHERE pesel = 17;
UPDATE doctors SET spec_id = 3 WHERE pesel = 18;
UPDATE doctors SET spec_id = 4 WHERE pesel = 19;
UPDATE doctors SET spec_id = 5 WHERE pesel = 20;

commit;