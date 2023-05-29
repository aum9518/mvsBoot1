package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Agency;
import peaksoft.service.AgencyService;

@Controller
@RequestMapping("/agencies")
@RequiredArgsConstructor
public class AgencyApi {
    private final AgencyService service;
@GetMapping
    public String getAllAgencies(Model model){
        model.addAttribute("agencies",service.getAllAgencies());
        return "agency/agency";
    }
    @GetMapping("/new")
    public String createAgency(Model model) {
        model.addAttribute("newAgency", new Agency());
        return "agency/newAgency";
    }

    @PostMapping("/save")
    public String saveAgency(@ModelAttribute("newAgency") Agency agency) {
        service.saveAgency(agency);
        return "redirect:/agencies";
    }
    @GetMapping("/{id}/delete")
    public String deleteAgency(@PathVariable("id") Long id){
        service.deleteAgencyById(id);
        return "redirect:/agencies";
    }
    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id")Long id, Model model){
        try{
            Agency agency = service.getAgencyById(id);
            if (agency  == null){
                return "errorPage";
            }
            model.addAttribute("editAgency",agency);
            return "agency/updateAgency";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "updateAgency";
    }
    @PostMapping("/updateAgency/{id}")
    public String saveUpdate(@ModelAttribute("editAgency") Agency agency, @PathVariable("id") Long id){
        service.updateAgency(id,agency);
        return "redirect:/agencies";
    }
    @GetMapping("/info")
   public String getAllAgencyHouse(Model model){
    model.addAttribute("agencyHouse" ,service.getAllAgencyHouse());
    return "agency/agencyInfo";
    }
    @GetMapping("/search")
    public String searchAgency(@RequestParam("word") String word,Model model){
        model.addAttribute("agencies",service.searchAgency(word));
        return "agency/searchAgency";
    }
}
