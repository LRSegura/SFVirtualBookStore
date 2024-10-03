package com.dev2ever.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table
@Setter
@Getter
@ToString
public class Book extends AbstractEntity {

    @Column(nullable = false)
    public String title;

    @ManyToOne
    @JoinColumn(nullable = false)
    public Author author;

    @Column()
    public String publisher;

    @Column()
    public Integer year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher) && Objects.equals(year, book.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, author, publisher, year);
    }
}
