package com.gulbagomedovich.brs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<Agency> agencies;

    @OneToMany(mappedBy = "passenger")
    private Set<Ticket> tickets;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public String getFullName() {
        return firstName != null ? firstName + " " + lastName : "";
    }

}
