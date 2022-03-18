CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO T_USER (id, city, first_name, kind, last_name, street, street_number, zip, email)
VALUES ('e1c7c907-9c19-4274-9636-12cc208fa294', 'Duckburg', 'Scrooge', 'EMPLOYER', 'McDuck', 'Vault Road', '1',
        '54321', 'wealthy.scrooge@duckburg.net');

INSERT INTO T_USER (id, employer_id, city, first_name, kind, last_name, street, street_number, zip, email)
VALUES ('e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'e1c7c907-9c19-4274-9636-12cc208fa294', 'Duckburg', 'Donald',
        'EMPLOYEE', 'Duck', 'Webfoot Walk', '1313', '54321', 'donald.duck@duckburg.net');

INSERT INTO T_USER (id, employer_id, city, first_name, kind, last_name, street, street_number, zip, email)
VALUES ('75f70398-160a-4d91-9456-cb8bf21a867d', 'e1c7c907-9c19-4274-9636-12cc208fa294', 'Long City', 'Goofy',
        'EMPLOYEE', 'Goof', 'Longwalk Road', '1', '3233', 'goofy.goof@mail.com');

INSERT INTO T_USER (id, employer_id, city, first_name, kind, last_name, street, street_number, zip, email)
VALUES ('02c96bdf-e708-4602-a8d9-09fbcdf46455', 'e1c7c907-9c19-4274-9636-12cc208fa294', 'Duckburg', 'Daisy',
        'EMPLOYEE', 'Duck', 'Webfoot Walk', '1313', '54321', 'daisy@duck.org');

--
-- Timelogs
--
-- 1
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 1', '2020-08-10',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:15', '16:15', 0);
-- 2
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 2', '2020-08-13',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:15', '17:10', 0);
-- 3
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 3', '2020-08-14',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:30', '15:50', 0);
-- 4
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 4', '2020-08-17',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:50', '17:40', 0);
-- 5
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 5', '2020-08-21',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:50', '17:00', 0);
-- 6
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 6', '2020-08-24',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:50', '17:35', 0);
-- 7
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 7', '2020-08-27',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '07:50', '17:30', 0);
-- 8
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 8', '2020-09-03',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '07:50', '17:30', 0);
-- 9
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 9', '2020-09-04',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '07:50', '17:00', 0);
-- 10
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 10', '2020-09-07',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:50', '18:00', 0);
-- 11
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 11', '2020-09-10',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '07:50', '17:15', 0);
-- 12
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 12', '2020-09-11',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '07:50', '17:10', 0);
-- 13
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 13', '2020-09-14',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '11:50', '17:20', 0);
-- 14
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 14', '2020-09-24',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '09:50', '17:30', 0);
-- 15
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 15', '2020-09-25',
        '2020-10-23 20:01:10.000000', '2020-10-23 20:01:10.000000', '08:50', '17:15', 0);
-- 16
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 'Day 16', '2022-02-12',
        '2022-02-12 20:01:10.000000', '2022-02-12 20:01:10.000000', '08:50', '17:15', 0);
-- Daisy Day 1
INSERT INTO T_TIMELOG (id, user_id, comment, date, date_added, date_updated, start_time, end_time, version)
VALUES (uuid_generate_v1(), '02c96bdf-e708-4602-a8d9-09fbcdf46455', 'Day 1 for Daisy', '2022-02-10',
        '2022-02-11 20:01:10.000000', '2022-02-11 20:01:10.000000', '09:00', '13:00', 0);

--------------------------
-- SALARY
--------------------------

INSERT INTO T_SALARY (id, user_id, hourly_wage, valid_from, valid_until)
VALUES ('63d40520-7670-4860-a756-54e5969628c3', 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 23.0, to_date('20200801', 'YYYYMMDD'), to_date('20200831', 'YYYYMMDD'));

INSERT INTO T_SALARY (id, user_id, hourly_wage, valid_from)
VALUES ('29e4af62-292c-4e46-ab63-8785a05c8061', 'e3a64823-324c-4aa0-8cff-436fcc9fdcb4', 25.0, to_date('20200901', 'YYYYMMDD'));

INSERT INTO T_SALARY (id, user_id, hourly_wage, valid_from)
VALUES ('781740f5-d375-49fe-9489-cf2a792c1de4', '02c96bdf-e708-4602-a8d9-09fbcdf46455', 30.0, to_date('20220101', 'YYYYMMDD'));

INSERT INTO T_SALARY (id, user_id, hourly_wage, valid_from)
VALUES ('a8957c4d-b830-4ef2-9cdd-8a91577afe94', '75f70398-160a-4d91-9456-cb8bf21a867d', 9.99, to_date('20000101', 'YYYYMMDD'));
