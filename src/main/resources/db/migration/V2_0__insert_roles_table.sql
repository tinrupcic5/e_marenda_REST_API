-- V2_0__insert_roles_table.sql
INSERT INTO dbo.app_role (description, name) VALUES
 ('Admin has all rights and can manage all aspects of the system.', 'ADMIN'),
 ('Student can activate or deactivate lunch for each day.', 'STUDENT'),
 ('Parent can activate or deactivate lunch for each day for its child.', 'PARENT'),
 ('Kitchen staff can see who is coming for lunch on a given day.', 'KITCHEN');
