package de.iu.ghostnetfishing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public String showPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "persons";
    }

    @GetMapping("/persons/new")
    public String showCreatePersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @PostMapping("/persons")
    public String savePerson(@ModelAttribute Person person) {
        personService.savePerson(person);
        return "redirect:/persons";
    }
    @GetMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return "redirect:/persons";
    }
}