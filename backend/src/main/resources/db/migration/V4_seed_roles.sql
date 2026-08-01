CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO roles (id, name, created_at)
VALUES
(gen_random_uuid(), 'ROLE_ADMIN', NOW()),
(gen_random_uuid(), 'ROLE_USER', NOW())
ON CONFLICT (name) DO NOTHING;