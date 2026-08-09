package com.learning.todo.note.mapper;

import com.learning.todo.note.dto.CreateNoteRequest;
import com.learning.todo.note.dto.NoteResponse;
import com.learning.todo.note.dto.UpdateNoteRequest;
import com.learning.todo.note.model.Note;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface NoteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Note toEntity(CreateNoteRequest request);

    NoteResponse toResponse(Note note);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void update(UpdateNoteRequest request,
                @MappingTarget Note note);
}
