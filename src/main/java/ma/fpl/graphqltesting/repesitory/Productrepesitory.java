package ma.fpl.graphqltesting.repesitory;

import ma.fpl.graphqltesting.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productrepesitory extends JpaRepository<Product,String> {
}
