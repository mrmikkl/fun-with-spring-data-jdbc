package de.god.springjdbcfun.domain.author;

import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Entity;
import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@AggregateRoot
public class Author
{
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Long rating;

    @Builder
    private Author(final String firstName, final String lastName, final Long rating)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }
}

