package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Customer;
import peaksoft.repostiory.AgencyRepository;
import peaksoft.service.AgencyService;
import peaksoft.service.CustomerService;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerApi {
    private final CustomerService service;
    private final AgencyService agency;

    @GetMapping
    public String getAllCustomer(Model model) {
        model.addAttribute("customers", service.getAllCustomers());
        return "customer/customer";
    }
    @GetMapping("/newCustomer")
    public String createCustomer(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "customer/newCustomer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("newCustomer") Customer customer) {
        service.saveCustomer(customer);
        return "redirect:/customers";
    }
    @GetMapping("/{id}/delete")
    public String deleteAgency(@PathVariable("id") Long id){
        service.deleteCustomerById(id);
        return "redirect:/customers";
    }
    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable("id") Long id,Model model){
        try{
            Customer customer = service.getCustomerById(id);
            if (customer == null){
                return "errorPage";
            }
            model.addAttribute("info",customer);
            return "customer/customerInfo";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "errorPage";
    }
    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id")Long id, Model model){
        try{

            Customer customer = service.getCustomerById(id);
            if (customer  == null){
                return "errorPage";
            }
            model.addAttribute("editCustomer",customer);
            return "customer/updateCustomer";
           /* if (customer.getPhoneNumber().startsWith("+996") || customer.getPhoneNumber().startsWith("0")){
                if (customer.getPhoneNumber().length()==10 || customer.getPhoneNumber().length()==13){
                }
            }*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "errorPage";
    }
    @PostMapping("/updateCustomer/{id}")
    public String saveUpdate(@ModelAttribute("editCustomer") Customer customer, @PathVariable("id") Long id){
        service.updateCustomer(id,customer);
        return "redirect:/customers";
    }
    @GetMapping("/assign/{id}")
    public  String assignCustomerToAgency(@PathVariable Long id,Model model){
        Customer customer = service.getCustomerById(id);
        model.addAttribute("customers",customer);
        model.addAttribute("agency",agency.getAllAgencies());
        return "customer/customerAssign";
    }
    @PostMapping("/saveAssign/{id}")
    public String saveAssign(@RequestParam("agencyName") Long agencyId,
                             @PathVariable Long id){
        service.assignCustomerToAgency(id,agencyId);
        return "redirect:/customers";
    }
}
