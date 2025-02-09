-- V8_0__insert_admin_and_roles.sql

INSERT INTO dbo.app_user (username, password, email, phone, name, last_name, oib, school_id, grade_id)
VALUES ('tinrupcic',
        '$2a$10$fMTKsuI82Fc6CuIFhBtwN.6eoG6k4KvE2qEohe6GcuxZT0pu.d6va',
        'tinrupcic5@gmail.com',
        NULL,
        'Tin',
        'Rupcic',
        '12345678901',
        1,
        NULL);

insert into user_roles (user_id, role_id)
values (1, 1);