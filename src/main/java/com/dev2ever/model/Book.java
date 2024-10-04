package com.dev2ever.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
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

    @Column()
    public BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher) && Objects.equals(year, book.year) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, author, publisher, year, price);
    }
}
