CREATE TABLE roles
(
    id UUID PRIMARY KEY,

    name VARCHAR(30) NOT NULL UNIQUE,

    created_at TIMESTAMPTZ NOT NULL,

    updated_at TIMESTAMPTZ
);

CREATE INDEX idx_roles_name
ON roles(name);