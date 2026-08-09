package com.learning.todo.note.controller;

import com.learning.todo.common.response.ApiResponse;
import com.learning.todo.note.dto.CreateNoteRequest;
import com.learning.todo.note.dto.NoteResponse;
import com.learning.todo.note.dto.UpdateNoteRequest;
import com.learning.todo.note.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService service;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<NoteResponse>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(value = "direction", defaultValue = "desc") String direction,
            @RequestParam(value = "search", required = false) String search
    ) {
        Page<NoteResponse> result = service.findAll(page, size, sortBy, direction, search);
        ApiResponse<Page<NoteResponse>> response = ApiResponse.<Page<NoteResponse>>builder()
                .success(true)
                .message("Note fetched successfully.")
                .data(result)
                .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NoteResponse>> findById(@PathVariable("id") UUID id) {
        NoteResponse result = service.findById(id);
        ApiResponse<NoteResponse> response = ApiResponse.<NoteResponse>builder()
                .success(true)
                .message("Note fetched by id successfully.")
                .data(result)
                .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<NoteResponse>> create(@Valid @RequestBody CreateNoteRequest request) {
        NoteResponse result = service.create(request);
        ApiResponse<NoteResponse> response = ApiResponse.<NoteResponse>builder()
                .success(true)
                .message("Note created successfully.")
                .data(result)
                .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NoteResponse>> update(@PathVariable("id") UUID id, @Valid @RequestBody UpdateNoteRequest request) {
        NoteResponse result = service.update(id, request);
        ApiResponse<NoteResponse> response = ApiResponse.<NoteResponse>builder()
                .success(true)
                .message("Note updated successfully.")
                .data(result)
                .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Note deleted successfully")
                .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                .build();

        return ResponseEntity.ok(response);
    }
}
