package de.god.springjdbcfun.domain.library;

import java.util.Set;

import org.jmolecules.ddd.annotation.AggregateRoot;
import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@AggregateRoot
public class Library
{
    @Id
    private Long id;
    private String name;
    private Set<Book> books;

    @Builder
    private Library(
            final String name,
            @Singular final Set<Book> books)
    {
        this.name = name;
        this.books = books;
    }
}
