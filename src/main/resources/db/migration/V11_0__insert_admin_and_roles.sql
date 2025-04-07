-- Sample attendance for 10 students (user_id 4 to 13) for weekdays in early March 2025
-- Excludes holidays and weekends

INSERT INTO dbo.lunch_attendance (user_id, lunch_date, status, comment) VALUES
-- Monday, March 3
(4, '2025-03-03', TRUE, NULL), (5, '2025-03-03', TRUE, NULL), (6, '2025-03-03', TRUE, NULL),
(7, '2025-03-03', TRUE, NULL), (8, '2025-03-03', TRUE, NULL), (9, '2025-03-03', TRUE, NULL),
(10, '2025-03-03', TRUE, NULL), (11, '2025-03-03', TRUE, NULL), (12, '2025-03-03', TRUE, NULL), (13, '2025-03-03', TRUE, NULL),

-- Tuesday, March 4
(4, '2025-03-04', TRUE, NULL), (5, '2025-03-04', TRUE, NULL), (6, '2025-03-04', TRUE, NULL),
(7, '2025-03-04', TRUE, NULL), (8, '2025-03-04', TRUE, NULL), (9, '2025-03-04', TRUE, NULL),
(10, '2025-03-04', TRUE, NULL), (11, '2025-03-04', TRUE, NULL), (12, '2025-03-04', TRUE, NULL), (13, '2025-03-04', TRUE, NULL),

-- Wednesday, March 5
(4, '2025-03-05', TRUE, NULL), (5, '2025-03-05', TRUE, NULL), (6, '2025-03-05', TRUE, NULL),
(7, '2025-03-05', TRUE, NULL), (8, '2025-03-05', TRUE, NULL), (9, '2025-03-05', TRUE, NULL),
(10, '2025-03-05', TRUE, NULL), (11, '2025-03-05', TRUE, NULL), (12, '2025-03-05', TRUE, NULL), (13, '2025-03-05', TRUE, NULL),

-- Thursday, March 6
(4, '2025-03-06', TRUE, NULL), (5, '2025-03-06', TRUE, NULL), (6, '2025-03-06', TRUE, NULL),
(7, '2025-03-06', TRUE, NULL), (8, '2025-03-06', TRUE, NULL), (9, '2025-03-06', TRUE, NULL),
(10, '2025-03-06', TRUE, NULL), (11, '2025-03-06', TRUE, NULL), (12, '2025-03-06', TRUE, NULL), (13, '2025-03-06', TRUE, NULL),

-- Friday, March 7
(4, '2025-03-07', TRUE, NULL), (5, '2025-03-07', TRUE, NULL), (6, '2025-03-07', TRUE, NULL),
(7, '2025-03-07', TRUE, NULL), (8, '2025-03-07', TRUE, NULL), (9, '2025-03-07', TRUE, NULL),
(10, '2025-03-07', TRUE, NULL), (11, '2025-03-07', TRUE, NULL), (12, '2025-03-07', TRUE, NULL), (13, '2025-03-07', TRUE, NULL);
