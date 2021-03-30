package de.god.springjdbcfun.domain.author;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepo extends CrudRepository<Author, Long>
{
}
