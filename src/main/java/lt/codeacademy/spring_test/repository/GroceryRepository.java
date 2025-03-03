package lt.codeacademy.spring_test.repository;

import lt.codeacademy.spring_test.entity.Grocery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Long> {

    List<Grocery> findAllByPrice(double price,Pageable pageable);
}
