-- Insert roles
INSERT INTO roles (name) VALUES ('ROLE_STUD');
INSERT INTO roles (name) VALUES ('ROLE_TEACH');

-- Insert users
-- Password for 'stud' is 'studPass'
-- Password for 'teach' is 'teachPass'
INSERT INTO users (username, password) VALUES ('stud', '$2a$10$E.V51.3VvIMgDs8x4Tf5a.eJ4S4S5.i4s2i8i3o1o1u1i1e1u1i1');
INSERT INTO users (username, password) VALUES ('teach', '$2a$10$I.A1.2B2c3D4e5F6g7H8i.j9k0l1m2n3o4p5q6r7s8t9u0v');

-- Assign roles to users using the join table defined in the User entity
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1); -- stud gets ROLE_STUD
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2); -- teach gets ROLE_TEACH
