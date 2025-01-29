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

CREATE OR REPLACE FUNCTION update_student_lunch_summary()
RETURNS TRIGGER AS $$
BEGIN
    -- Insert or update the student's monthly lunch cost summary
INSERT INTO student_lunch_summary (user_id, month, year, total_cost)
SELECT
    NEW.user_id,
    EXTRACT(MONTH FROM ld.lunch_day_date) AS month,
        EXTRACT(YEAR FROM ld.lunch_day_date) AS year,
        COALESCE((SELECT total_cost FROM student_lunch_summary
                  WHERE user_id = NEW.user_id
                    AND month = EXTRACT(MONTH FROM ld.lunch_day_date)
                    AND year = EXTRACT(YEAR FROM ld.lunch_day_date)), 0) + ldc.meal_cost AS total_cost
ON CONFLICT (user_id, month, year)
    DO UPDATE SET total_cost = student_lunch_summary.total_cost + ldc.meal_cost;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to execute the function after inserting a new lunch attendance record
CREATE TRIGGER trg_update_student_lunch_summary
    AFTER INSERT ON lunch_attendance
    FOR EACH ROW
    WHEN (NEW.status = TRUE) -- Only count if the student is attending
    EXECUTE FUNCTION update_student_lunch_summary();
