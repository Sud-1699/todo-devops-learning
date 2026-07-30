package com.learning.todo.note.model;

import com.learning.todo.common.model.Base;
import com.learning.todo.note.enums.NoteStatus;
import com.learning.todo.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note extends Base {
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private NoteStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;
}
