package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.House;
import peaksoft.service.AgencyService;
import peaksoft.service.HouseService;

@Controller
@RequestMapping("houses")
@RequiredArgsConstructor
public class HouseApi {
    private final HouseService service;
    private final AgencyService agencyService;

    @GetMapping()
    public String getAllCustomer(Model model) {
        model.addAttribute("houses", service.getAllHouses());
        return "house/house";
    }

    @GetMapping("/createHouse")
    public String createHouse(Model model){
        model.addAttribute("house", new House());
        model.addAttribute("agencies",agencyService.getAllAgencies());
        return "house/newHouse";
    }
    @PostMapping("/saveHouse")
    public String saveHouse(@ModelAttribute("house") House house,
                            @RequestParam("agencyName") Long agencyId){
        service.saveHouse(agencyId,house);
        return "redirect:/houses";
    }
    @GetMapping("/{id}/delete")
    public String deleteHouse(@PathVariable("id") Long id) {
        service.deleteHouseById(id);
        return "redirect:/houses";

    }
    @GetMapping("/house/{id}")
    public String getHouseById(@PathVariable("id") Long id, Model  model){
        model.addAttribute("house", service.getHouseById(id));
        return "house/infoHouse";
    }

    @GetMapping("/update/{id}")
    public String updateHouse(@PathVariable("id") Long id,Model model){
        model.addAttribute("houses", service.getHouseById(id));
        model.addAttribute("agencies", agencyService.getAllAgencies());
        return "house/editHouse";
    }
    @PostMapping("/saveUpdate/{id}")
    public String saveUpdate(@ModelAttribute("houses") House house,@PathVariable("id") Long id){
        service.updateHouse(id,house);
        return "redirect:/houses";
    }
    @GetMapping("/sort")
    public String getAllSortedHouse(Model model,@RequestParam("sort") String word){
        model.addAttribute("houses",service.getAllSortedHouse(word));
        return "house/sortHouse";
    }
}
