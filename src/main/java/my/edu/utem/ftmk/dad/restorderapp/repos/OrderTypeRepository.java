package my.edu.utem.ftmk.dad.restorderapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.restorderapp.models.OrderType;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Long> {

}
