package de.god.springjdbcfun.domain.library;

import org.jmolecules.ddd.annotation.Entity;
import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
public class Book
{
    @Id
    private Long id;
    private String title;
    private Author author;

    @Builder
    private Book(final String title, final Author author)
    {
        this.title = title;
        this.author = author;
    }
}
