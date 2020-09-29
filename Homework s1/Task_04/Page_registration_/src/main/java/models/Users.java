package models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Users {
    private String email;
    private String name;
    private String surname;
    private String number;
}
