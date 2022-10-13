package io.artcreativity.sketchserver.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "profiles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profile implements Serializable {



    private String lastName;
    private String image;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
