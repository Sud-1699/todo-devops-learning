package com.learning.todo.role.model;

import com.learning.todo.common.model.Base;
import com.learning.todo.role.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends Base {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleType name;
}
