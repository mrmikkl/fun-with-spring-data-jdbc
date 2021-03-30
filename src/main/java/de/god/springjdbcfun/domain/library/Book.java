package de.god.springjdbcfun.domain.library;

import java.util.Objects;

import org.jmolecules.ddd.annotation.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import de.god.springjdbcfun.domain.author.Author;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = @PersistenceConstructor)
@Entity
public class Book
{
    @Id
    private Long id;
    private String title;
    private AggregateReference<Author, Long> author;
//    private Author author;

    @Builder
    private Book(final String title, final Author author)
    {
        this.title = title;
//        this.author = author;
        if (Objects.nonNull(author))
        {
            this.author = AggregateReference.to(author.getId());
        }
    }
}
