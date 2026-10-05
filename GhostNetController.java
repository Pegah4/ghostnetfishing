package de.iu.ghostnetfishing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GhostNetController {

    private final GhostNetService ghostNetService;
    private final PersonService personService;

    public GhostNetController(GhostNetService ghostNetService, PersonService personService) {
        this.ghostNetService = ghostNetService;
        this.personService = personService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/ghostnets/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ghostNet", new GhostNet());
        model.addAttribute("persons", personService.getAllPersons());
        return "ghostnet-form";
    }
    @GetMapping("/ghostnets")
    public String showGhostNets(Model model) {
        model.addAttribute("ghostNets", ghostNetService.getAllGhostNets());
        return "ghostnets";
    }
    @GetMapping("/ghostnets/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        GhostNet ghostNet = ghostNetService.getGhostNetById(id);
        model.addAttribute("ghostNet", ghostNet);
        model.addAttribute("persons", personService.getAllPersons());
        return "ghostnet-form";
    }
    @PostMapping("/ghostnets")
    public String saveGhostNet(@ModelAttribute GhostNet ghostNet,
                               @RequestParam(required = false) Long rescuerId) {

        if (ghostNet.getStatus() == null || ghostNet.getStatus().isBlank()) {
            ghostNet.setStatus("Gemeldet");
        }

        if (rescuerId != null) {
            Person rescuer = personService.getPersonById(rescuerId);
            ghostNet.setRescuer(rescuer);
        }

        ghostNetService.saveGhostNet(ghostNet);
        return "redirect:/ghostnets";
    }

}