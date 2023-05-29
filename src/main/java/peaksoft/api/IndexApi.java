package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.service.AgencyService;

@Controller
@RequestMapping("index")
@RequiredArgsConstructor
public class IndexApi {
    private final AgencyService service;
    @GetMapping
    public String getAllAgencies(Model model) {
        model.addAttribute("index", service.getAllAgencies());
        return "index";
    }

}
