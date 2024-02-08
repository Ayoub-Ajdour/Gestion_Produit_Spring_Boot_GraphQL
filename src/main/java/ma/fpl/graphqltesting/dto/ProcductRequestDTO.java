package ma.fpl.graphqltesting.dto;

public record ProcductRequestDTO (
         String id,
         String name,
         double price,
         int quantity,
         Long categoryId
){
}
