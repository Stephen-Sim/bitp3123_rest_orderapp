package my.edu.utem.ftmk.dad.restorderapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import my.edu.utem.ftmk.dad.restorderapp.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
