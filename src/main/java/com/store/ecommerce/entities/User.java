package com.store.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "email",  nullable = false)
    private String email;

    @Column(name = "password",  nullable = false)
    private String password;

    @OneToMany(mappedBy = "user_id") // sets owner of relationship to user_id field in addresses
    @Builder.Default // needed to make builder work otherwise initialization is skipped
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

}
