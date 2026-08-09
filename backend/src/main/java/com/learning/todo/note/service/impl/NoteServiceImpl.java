package com.learning.todo.note.service.impl;

import com.learning.todo.common.exception.ResourceNotFoundException;
import com.learning.todo.common.security.CustomUserDetails;
import com.learning.todo.common.security.SecurityUtils;
import com.learning.todo.note.dto.CreateNoteRequest;
import com.learning.todo.note.dto.NoteResponse;
import com.learning.todo.note.dto.UpdateNoteRequest;
import com.learning.todo.note.enums.NoteStatus;
import com.learning.todo.note.mapper.NoteMapper;
import com.learning.todo.note.model.Note;
import com.learning.todo.note.repository.NoteRepository;
import com.learning.todo.note.service.NoteService;
import com.learning.todo.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;
    private final NoteMapper mapper;
    private final SecurityUtils utils;

    @Override
    public NoteResponse create(CreateNoteRequest request) {
        User owner = getCurrentUser();
        Note note = mapper.toEntity(request);
        note.setOwner(owner);
        note.setStatus(NoteStatus.ACTIVE);

        Note result = repository.save(note);
        log.info("Note created successfully. noteId={}, userId={}",
                result.getId(),
                owner.getId()
        );

        return mapper.toResponse(result);
    }

    @Override
    @Transactional(readOnly = true)
    public NoteResponse findById(UUID id) {
        User owner = getCurrentUser();
        Note result = findNoteByIdAndOwner(id, owner);

        return mapper.toResponse(result);
    }

    @Override
    public NoteResponse update(UUID id, UpdateNoteRequest request) {
        User owner = getCurrentUser();
        Note existingNote = findNoteByIdAndOwner(id, owner);

        mapper.update(request, existingNote);

        Note updatedResult = repository.save(existingNote);
        log.info(
                "Note updated successfully. noteId={}, userId={}",
                updatedResult.getId(),
                owner.getId()
        );

        return mapper.toResponse(updatedResult);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoteResponse> findAll(int page, int size, String sortBy, String direction, String search) {
        User owner = getCurrentUser();

        Sort sort = createSort(sortBy, direction);

        Pageable pageable = PageRequest.of(
                page,
                size,
                sort
        );

        Page<Note> notes;

        if (search != null && !search.isBlank()) {

            notes = repository
                    .findByOwnerAndTitleContainingIgnoreCase(
                            owner,
                            search.trim(),
                            pageable
                    );

        } else {

            notes = repository
                    .findByOwner(
                            owner,
                            pageable
                    );
        }

        return notes.map(mapper::toResponse);
    }

    @Override
    public void delete(UUID id) {
        User owner = getCurrentUser();

        Note note = findNoteByIdAndOwner(id, owner);

        repository.delete(note);

        log.info(
                "Note deleted successfully. noteId={}, userId={}",
                id,
                owner.getId()
        );
    }

    private User getCurrentUser() {
        return utils.getCurrentUser().getUser();
    }

    private Note findNoteByIdAndOwner(
            UUID id,
            User owner
    ) {

        return repository
                .findByIdAndOwner(id, owner)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Note not found with id: " + id
                        )
                );
    }

    private Sort createSort(
            String sortBy,
            String direction
    ) {

        String property =
                sortBy == null || sortBy.isBlank()
                        ? "createdAt"
                        : sortBy;

        Sort.Direction sortDirection =
                "desc".equalsIgnoreCase(direction)
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        return Sort.by(sortDirection, property);
    }
}
