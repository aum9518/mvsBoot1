package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.Customer;
import peaksoft.exceptions.MyException;
import peaksoft.repostiory.AgencyRepository;
import peaksoft.repostiory.CustomerRepository;
import peaksoft.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl  implements CustomerService {
    private final CustomerRepository repository;
    private final AgencyRepository agency;

    @Override
    public void saveCustomer(Customer customer) {

        repository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        try{
            return repository.findById(id).orElseThrow(()->new MyException("Customer with id: "+id+" is not found"));
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateCustomer(Long id, Customer newCustomer) {
        try{
            Customer customer = repository.findById(id).orElseThrow(() -> new MyException("Customer with id: " + id + " is not found"));
            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            customer.setEmail(newCustomer.getEmail());
            customer.setDateOfBirth(newCustomer.getDateOfBirth());
            customer.setPhoneNumber(newCustomer.getPhoneNumber());
            repository.save(customer);
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCustomerById(Long id) {
   try{
       if (repository.existsById(id)){
           repository.deleteById(id);
       }else throw new MyException("Customer with id: " + id + " is not found");
   }catch (MyException e){
       System.out.println(e.getMessage());
   }
    }

    @Override
    public void assignCustomerToAgency(Long customerId, Long agencyId) {

        try{
            Customer customer = repository.findById(customerId).orElseThrow(() -> new MyException("Customer with id: " + customerId + " is not found"));
            Agency agency1 = agency.findById(agencyId).orElseThrow(() -> new MyException("Customer with id: " + agencyId + " is not found"));

            List<Customer>customers = new ArrayList<>();
            List<Agency> agencies = new ArrayList<>();

            customer.setAgencies(agencies);
            agency1.setCustomers(customers);

            customers.add(customer);
            agencies.add(agency1);

            repository.assignCustomerToAgency(agencyId, customerId);

        }catch (MyException e){
            System.out.println(e.getMessage());
        }

    }
}
