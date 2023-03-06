package org.obisidiana.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
  /*  @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;*/
    @Column(name = "confirm_id")
    private Long confirmId;
}
