-- Seed users only if not already present (MySQL syntax)
INSERT INTO users (username, password, role)
SELECT * FROM (SELECT 'admin' AS username,
                      '$2a$10$kVpbY/YKu6yZpK6MthmGxOKFRiYb3opmNgPu7Xdy8bCBCeY/Wb00e' AS password,
                      'ADMIN' AS role) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin')
    LIMIT 1;

INSERT INTO users (username, password, role)
SELECT * FROM (SELECT 'user' AS username,
                      '$2a$10$WTyTykVOrY6MQ0oNT46lXOMSy8TClrWlNQLMmZuQ.MNTOp4eIMTnG' AS password,
                      'USER' AS role) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'user')
    LIMIT 1;