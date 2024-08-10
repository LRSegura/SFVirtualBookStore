package com.dev2ever.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table
@Setter
@Getter
@ToString
public class Author extends AbstractEntity  {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column()
    private String email;

    @Column()
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(name, author.name) && Objects.equals(lastName, author.lastName) && Objects.equals(email,
                author.email) && Objects.equals(phone, author.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, lastName, email, phone);
    }
}
