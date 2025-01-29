-- V6_0__create_receipt_table.sql

-- Table to store receipts at the end of the month for users
CREATE TABLE monthly_receipt (
                                 id BIGSERIAL PRIMARY KEY,                          -- Unique receipt ID
                                 user_id BIGINT NOT NULL,                           -- The user associated with the receipt
                                 amount DECIMAL(10, 2) NOT NULL,                    -- Total amount for the meals in the month
                                 receipt_date DATE NOT NULL,                        -- Date when the receipt was generated (e.g., end of the month)
                                 created_by BIGINT NOT NULL,                        -- The user who created the receipt (e.g., an admin)
                                 status VARCHAR(50) CHECK (status IN ('PAID', 'PENDING', 'NOT_PAID')) NOT NULL,  -- Status of the receipt
                                 for_month DATE NOT NULL,                               -- Month and year for the student's receipt (e.g., '2025-01-01' for January 2025)
                                 CONSTRAINT FK_receipt_user FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE,
                                 CONSTRAINT FK_receipt_creator FOREIGN KEY (created_by) REFERENCES app_user(id) ON DELETE CASCADE
);
