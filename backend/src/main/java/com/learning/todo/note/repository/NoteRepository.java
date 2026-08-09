package com.learning.todo.note.repository;

import com.learning.todo.note.model.Note;
import com.learning.todo.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {
    Page<Note> findByOwner(User owner, Pageable pageable);
    Optional<Note> findByIdAndOwner(UUID id, User owner);
    Page<Note> findByOwnerAndTitleContainingIgnoreCase(
            User owner,
            String title,
            Pageable pageable
    );
}
