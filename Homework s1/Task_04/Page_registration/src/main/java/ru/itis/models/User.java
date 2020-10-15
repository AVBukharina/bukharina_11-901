package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
