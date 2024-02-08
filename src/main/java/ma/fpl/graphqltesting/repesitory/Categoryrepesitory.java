package ma.fpl.graphqltesting.repesitory;

import ma.fpl.graphqltesting.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Categoryrepesitory extends JpaRepository<Category,Long> {
}
