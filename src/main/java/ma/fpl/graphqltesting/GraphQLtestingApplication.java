package ma.fpl.graphqltesting;

import ma.fpl.graphqltesting.entities.Category;
import ma.fpl.graphqltesting.entities.Product;
import ma.fpl.graphqltesting.repesitory.Categoryrepesitory;
import ma.fpl.graphqltesting.repesitory.Productrepesitory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class GraphQLtestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQLtestingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(Categoryrepesitory categoryrepesitory, Productrepesitory productrepesitory){
        return args -> {
            Random random=new Random();
            List.of("Computer","Printer","Smartphone").forEach(cat->{
                Category category=Category.builder().name(cat).build();
                categoryrepesitory.save(category);



            });
            categoryrepesitory.findAll().forEach(category -> {
                for (int i=0;i<10;i++){
                    Product product= Product.builder().id(UUID.randomUUID().toString()).name("Computer"+i).price(100+Math.random()*50000).quantity(random.nextInt(100)).category(category).build();
                productrepesitory.save(product);
                }
            });
        };
    }
}
