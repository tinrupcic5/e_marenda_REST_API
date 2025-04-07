-- SUPER_ADMIN (already created with ID = 1)
-- Starting from user_id 2

-- ADMIN
INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('admin1',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'admin1@example.com',
        NULL,
        'Admin',
        'User',
        '10000000001',
        1,
        NULL);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);

-- KITCHEN
INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('kitchen1',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'kitchen1@example.com',
        NULL,
        'Kitchen',
        'Staff',
        '10000000002',
        1,
        NULL);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 5);

-- STUDENTS
INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('student1',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'student1@example.com',
        NULL,
        'Student',
        'One',
        '10000000003',
        1,
        3);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 3);

INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('student2',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'student2@example.com',
        NULL,
        'Student',
        'Two',
        '10000000004',
        1,
        3);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 3);

INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('student3',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'student3@example.com',
        NULL,
        'Student',
        'Three',
        '10000000005',
        1,
        2);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 3);

INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('student4',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'student4@example.com',
        NULL,
        'Student',
        'Four',
        '10000000006',
        1,
        3);
INSERT INTO user_roles (user_id, role_id) VALUES (7, 3);

INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib,school_id, grade_id)
VALUES ('student5',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'student5@example.com',
        NULL,
        'Student',
        'Five',
        '10000000007',
        1,
        4);
INSERT INTO user_roles (user_id, role_id) VALUES (8, 3);

INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('student6',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'student6@example.com',
        NULL,
        'Student',
        'Six',
        '10000000008',
        1,
        5);
INSERT INTO user_roles (user_id, role_id) VALUES (9, 3);

INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('student7',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'student7@example.com',
        NULL,
        'Student',
        'Seven',
        '10000000009',
        1,
        2);
INSERT INTO user_roles (user_id, role_id) VALUES (10, 3);

INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('student8',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'student8@example.com',
        NULL,
        'Student',
        'Eight',
        '10000000010',
        1,
        2);
INSERT INTO user_roles (user_id, role_id) VALUES (11, 3);

INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('student9',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'student9@example.com',
        NULL,
        'Student',
        'Nine',
        '10000000011',
        1,
        2);
INSERT INTO user_roles (user_id, role_id) VALUES (12, 3);

INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id,grade_id)
VALUES ('student10',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'student10@example.com',
        NULL,
        'Student',
        'Ten',
        '10000000012',
        1,
        4);
INSERT INTO user_roles (user_id, role_id) VALUES (13, 3);
