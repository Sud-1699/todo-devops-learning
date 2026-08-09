package com.learning.todo.note.service;

import com.learning.todo.note.dto.CreateNoteRequest;
import com.learning.todo.note.dto.NoteResponse;
import com.learning.todo.note.dto.UpdateNoteRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface NoteService {
    NoteResponse create(CreateNoteRequest request);
    NoteResponse findById(UUID id);
    NoteResponse update(UUID id, UpdateNoteRequest request);
    Page<NoteResponse> findAll(int page, int size, String sortBy, String direction, String search);
    void delete(UUID id);
}
