CREATE TABLE roles
(
    id UUID PRIMARY KEY,

    name VARCHAR(50) UNIQUE NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP
);