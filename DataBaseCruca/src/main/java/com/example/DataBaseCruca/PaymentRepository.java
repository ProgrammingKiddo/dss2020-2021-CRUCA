package com.example.DataBaseCruca;
/**
 * 
 * @author Mar�a
 * @author Fran
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment,Integer>{

}
