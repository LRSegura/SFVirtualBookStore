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
public class Buy extends AbstractEntity{

    @Column(nullable = false)
    private BigDecimal amount;

    @Column()
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn()
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Buy buy = (Buy) o;
        return Objects.equals(amount, buy.amount) && Objects.equals(description, buy.description) && Objects.equals(price, buy.price) && Objects.equals(book, buy.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amount, description, price, book);
    }
}
