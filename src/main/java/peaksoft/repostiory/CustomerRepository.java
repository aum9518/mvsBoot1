package peaksoft.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Modifying
    @Query("update Customer c set c.agencies = :agencyId where c.id = :customerId")
    void assignCustomerToAgency(@Param("agencyId") Long agencyId, @Param("customerId") Long customerId);

}
