-- V5_0__create_lunch_tracking_tables.sql

-- Table to store school holidays
CREATE TABLE school_holiday (
                                id BIGSERIAL PRIMARY KEY,
                                non_working_date DATE NOT NULL UNIQUE,  -- Only stores non-working days
                                name VARCHAR(255) NOT NULL,             -- Name of the holiday (e.g., "Christmas Break")
                                description VARCHAR(255),               -- Optional description
                                school_id BIGINT NOT NULL,
                                CONSTRAINT FK_school_holiday FOREIGN KEY (school_id) REFERENCES e_school(id) ON DELETE CASCADE
);

-- Table to track student attendance for lunch
CREATE TABLE lunch_attendance (
                                  id BIGSERIAL PRIMARY KEY,
                                  user_id BIGINT NOT NULL,
                                  lunch_date DATE NOT NULL,               -- Directly storing the date of attendance
                                  status BOOLEAN NOT NULL,                -- True for "coming", False for "not coming"
                                  comment VARCHAR(255),                   -- Optional comments
                                  CONSTRAINT FK_lunch_attendance_user FOREIGN KEY (user_id) REFERENCES app_user (id) ON DELETE CASCADE
);

-- Table to track meal preferences of students
CREATE TABLE student_meal_preference (
                                         id BIGSERIAL PRIMARY KEY,
                                         user_id BIGINT NOT NULL,
                                         comment VARCHAR(255) NOT NULL, -- Meal preference or Allergies, e.g., "Vegetarian"
                                         CONSTRAINT FK_meal_preference_user FOREIGN KEY (user_id) REFERENCES app_user (id) ON DELETE CASCADE
);

-- Table to store meal cost for each lunch day, including a reference to the school
CREATE TABLE lunch_day_cost (
                                id BIGSERIAL PRIMARY KEY,               -- Unique ID for the record
                                school_id BIGINT NOT NULL,              -- Foreign key linking to the school table
                                meal_cost DECIMAL(10, 2) NOT NULL,      -- The cost of the meal per day
                                CONSTRAINT FK_lunch_day_cost_school FOREIGN KEY (school_id) REFERENCES e_school(id) ON DELETE CASCADE
);


