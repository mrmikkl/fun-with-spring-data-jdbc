package de.god.springjdbcfun.domain.library;

import java.util.List;

import org.jmolecules.ddd.annotation.AggregateRoot;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

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
    @MappedCollection(idColumn = "LIBRARY_ID", keyColumn = "ID")
    private List<Book> books;

    @Builder
    private Library(
            final String name,
            @Singular final List<Book> books)
    {
        this.name = name;
        this.books = books;
    }
}
