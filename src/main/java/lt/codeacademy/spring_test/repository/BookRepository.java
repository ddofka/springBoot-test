package lt.codeacademy.spring_test.repository;

import lt.codeacademy.spring_test.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findAllByGenre(String genre);

    Book findBookByTitle(String title);
    boolean existsBookByTitle(String title);

}
