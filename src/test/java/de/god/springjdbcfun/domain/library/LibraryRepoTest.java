package de.god.springjdbcfun.domain.library;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.god.springjdbcfun.domain.author.Author;
import de.god.springjdbcfun.domain.author.AuthorRepo;

@SpringBootTest
class LibraryRepoTest
{
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private LibraryRepo libraryRepo;

    @DisplayName("Library without Books")
    @Nested
    class LibraryWithoutBooks
    {
        @DisplayName("GIVEN a Library without Books "
                + "WHEN Library is saved "
                + "THEN the Library is created")
        @Test
        void createLibraryWithoutBooks()
        {
            final Library newLib = Library.builder()
                    .name("New Lib")
                    .build();

            final Library savedLib = libraryRepo.save(newLib);

            assertThat(savedLib.getId()).isPositive();
            assertThat(savedLib.getName()).isEqualTo(newLib.getName());
        }
    }

    @DisplayName("Library with Books")
    @Nested
    class LibraryWithBooks
    {
        @DisplayName("GIVEN a Library with Books from unknown Authors "
                + "WHEN Library is saved "
                + "THEN Library is created")
        @Test
        void createLibraryWithBooksWithUnknownAuthor()
        {
            // GIVEN
            final Book book1 = new BookFixture().create("First Book", null);
            final Book book2 = new BookFixture().create("Second Book", null);
            final Library lib = new LibraryFixture().create("New Lib", Set.of(book1, book2));

            // WHEN
            final Library savedLib = libraryRepo.save(lib);

            // THEN
            assertThat(savedLib.getBooks().size()).isEqualTo(2);

            savedLib.getBooks().forEach(book -> {
                assertThat(book).isNotNull()
                        .extracting(Book::getTitle)
                        .isNotNull();
            });
        }

        @DisplayName("GIVEN a Library with Books from same Author "
                + "WHEN Library is saved "
                + "THEN Library is created")
        @Test
        void createLibraryWithBooksFromSameAuthor()
        {
            // GIVEN
            final Author author = new AuthorFixture().create("Hans", "Peter", 3L);
            final Author savedAuthor = authorRepo.save(author);

            final Book book1 = new BookFixture().create("First Book", savedAuthor);
            final Book book2 = new BookFixture().create("Second Book", savedAuthor);
            final Library lib = new LibraryFixture().create("New Lib", Set.of(book1, book2));

            // WHEN
            final Library savedLib = libraryRepo.save(lib);

            // THEN
            assertThat(savedLib.getBooks().size()).isEqualTo(2);

            savedLib.getBooks().forEach(book -> {
                assertThat(book).isNotNull()
                        .extracting(Book::getTitle)
                        .isNotNull();

                assertThat(book.getAuthor()).isNotNull();
            });

            assertThat(savedLib.getBooks())
                    .extracting(Book::getTitle)
                    .anyMatch(value -> value.equals("First Book"))
                    .anyMatch(value -> value.equals("Second Book"));
        }

        private class AuthorFixture
        {
            public Author create(final String firstName, final String lastName, final Long rating)
            {
                return Author.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .rating(rating)
                        .build();
            }
        }

        private class BookFixture
        {
            public Book create(final String pBook, final Author pAuthor)
            {
                return Book.builder()
                        .title(pBook)
                        .author(pAuthor)
                        .build();
            }
        }

        private class LibraryFixture
        {
            public Library create(final String pName, final Set<Book> pBooks)
            {
                return Library.builder()
                        .name(pName)
                        .books(pBooks)
                        .build();
            }
        }
    }
}
