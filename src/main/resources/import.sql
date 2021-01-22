INSERT INTO USER (id, city, first_name, kind, last_name, street, street_number, zip)
VALUES ('e1c7c907-9c19-4274-9636-12cc208fa294', 'Duckburg', 'Scrooge', 'EMPLOYER', 'McDuck', 'Vault Road', '1',
        '54321');

INSERT INTO USER (id, employer_id, city, first_name, kind, last_name, street, street_number, zip)
VALUES ('e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'e1c7c907-9c19-4274-9636-12cc208fa294', 'Duckburg', 'Donald',
        'EMPLOYEE', 'Duck', 'Webfoot Walk', '1313', '54321');

--
-- Timelogs
--
-- 1
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 1', '2020-08-10',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:15', '16:15', 0);
-- 2
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 2', '2020-08-13',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:15', '17:10', 0);
-- 3
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 3', '2020-08-14',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:30', '15:50', 0);
-- 4
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 4', '2020-08-17',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:50', '17:40', 0);
-- 5
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 5', '2020-08-21',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:50', '17:00', 0);
-- 6
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 6', '2020-08-24',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:50', '17:35', 0);
-- 7
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 7', '2020-08-27',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '07:50', '17:30', 0);
-- 8
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 8', '2020-09-03',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '07:50', '17:30', 0);
-- 9
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 9', '2020-09-04',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '07:50', '17:00', 0);
-- 10
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 10', '2020-09-07',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:50', '18:00', 0);
-- 11
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 11', '2020-09-10',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '07:50', '17:15', 0);
-- 12
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 12', '2020-09-11',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '07:50', '17:10', 0);
-- 13
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 13', '2020-09-14',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:50', '17:20', 0);
-- 14
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 14', '2020-09-24',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '09:50', '17:30', 0);
-- 15
INSERT INTO TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (NEXTVAL(timelog_id_seq), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 15', '2020-09-25',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '08:50', '17:15', 0);

INSERT INTO SALARY (user_id, hourly_wage)
VALUES ('e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 23.0);
