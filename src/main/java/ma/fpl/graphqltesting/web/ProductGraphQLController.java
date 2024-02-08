package ma.fpl.graphqltesting.web;

import ma.fpl.graphqltesting.dto.ProcductRequestDTO;
import ma.fpl.graphqltesting.entities.Category;
import ma.fpl.graphqltesting.entities.Product;
import ma.fpl.graphqltesting.repesitory.Categoryrepesitory;
import ma.fpl.graphqltesting.repesitory.Productrepesitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductGraphQLController {

    private Productrepesitory productrepesitory;
    private Categoryrepesitory categoryrepesitory;

    public ProductGraphQLController(Productrepesitory productrepesitory, Categoryrepesitory categoryrepesitory) {
        this.productrepesitory = productrepesitory;
        this.categoryrepesitory = categoryrepesitory;
    }





    @QueryMapping
    public List<Product> productList(){
        return productrepesitory.findAll();
    }

    @QueryMapping
    public Product productById(@Argument String id){
        return productrepesitory.findById(id).orElseThrow(()->new RuntimeException(String.format("Product %s not found ",id)));
    }
    @QueryMapping
    public List<Category> categoryList(){
        return categoryrepesitory.findAll();
    }

    @QueryMapping
    public Category categoryById(@Argument Long id){
        return categoryrepesitory.findById(id).orElseThrow(()->new RuntimeException(String.format("Category %ld not found ",id)));
    }


    @MutationMapping
    public Product saveProduct(@Argument ProcductRequestDTO product){
        Product productoSave=new Product();
        Category category=categoryrepesitory.findById(product.categoryId()).orElse(null);
        productoSave.setId(UUID.randomUUID().toString());
        productoSave.setName(product.name());
        productoSave.setPrice(product.price());
        productoSave.setQuantity(product.quantity());
        productoSave.setCategory(category);
        return productrepesitory.save(productoSave);
    }
    @MutationMapping
    public Product updateProduct(@Argument String id,@Argument ProcductRequestDTO product){
        Product productoSave=new Product();
        Category category=categoryrepesitory.findById(product.categoryId()).orElse(null);
        productoSave.setId(id);
        productoSave.setName(product.name());
        productoSave.setPrice(product.price());
        productoSave.setQuantity(product.quantity());
        productoSave.setCategory(category);
        return productrepesitory.save(productoSave);
    }

    @MutationMapping
    public void deleteProduct(@Argument String id){
         productrepesitory.deleteById(id);
    }
}
