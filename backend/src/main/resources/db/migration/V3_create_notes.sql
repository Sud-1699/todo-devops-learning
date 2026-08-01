CREATE TABLE notes
(
    id UUID PRIMARY KEY,

    title VARCHAR(255) NOT NULL,

    description TEXT,

    status VARCHAR(20) NOT NULL,

    user_id UUID NOT NULL,

    created_at TIMESTAMPTZ NOT NULL,

    updated_at TIMESTAMPTZ,

    CONSTRAINT fk_note_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT chk_note_status
        CHECK (status IN ('ACTIVE', 'ARCHIVED'))
);

CREATE INDEX idx_notes_user
ON notes(user_id);

CREATE INDEX idx_notes_status
ON notes(status);

CREATE INDEX idx_notes_created_at
ON notes(created_at);