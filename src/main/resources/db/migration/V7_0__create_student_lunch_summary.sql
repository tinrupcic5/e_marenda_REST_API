-- V7_0__create_student_lunch_summary.sql
CREATE TABLE student_lunch_summary (
                                       id BIGSERIAL PRIMARY KEY,
                                       user_id BIGINT NOT NULL,
                                       month INT NOT NULL,  -- Stores the month (e.g., 1 for January)
                                       year INT NOT NULL,   -- Stores the year (e.g., 2024)
                                       total_cost DECIMAL(10,2) NOT NULL DEFAULT 0,  -- Running total for the month's lunches
                                       CONSTRAINT unique_user_month UNIQUE (user_id, month, year),  -- Ensure one record per user per month
                                       CONSTRAINT FK_student_lunch_summary_user FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE
);