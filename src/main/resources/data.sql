INSERT INTO users VALUES (1, "user@user.com", "$2a$10$uKFJ0Z91cli2wjFmHguPGuCjZh8g3KH2enSpSMHOYtRGh52G8xehS", "user");
INSERT INTO users VALUES (2, "admin@admin.com", "$2a$10$IuyJ74kQHmthpEAm4QMqsuHyjJtNmgp8syUcOKEdRq6cM048rUi0.", "admin");
INSERT INTO roles VALUES (1, "ROLE_USER");
INSERT INTO roles VALUES (2, "ROLE_ADMIN");
INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (2, 1);
INSERT INTO users_roles VALUES (2, 2);