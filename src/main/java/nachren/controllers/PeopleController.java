package nachren.controllers;

import nachren.dao.BookDAO;
import nachren.dao.PersonDAO;
import nachren.models.Person;
import nachren.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {


    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    private final DataSource dataSource;
    private final BookDAO bookDAO;


    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator, DataSource dataSource, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
        this.dataSource = dataSource;
        this.bookDAO = bookDAO;
    }


    @GetMapping()
    public String index(Model model){
        // Получим людей из дао и передадим их на представление
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        // получим одного человека из дао и передадим его на представление
        model.addAttribute("person", personDAO.show(id));
        // получим список книг человека из book дао и передадим его на представление
        model.addAttribute("personsBooks", bookDAO.getPersonsBooks(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute Person person){
       // model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){

        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/new";
        }
        //System.out.println(person.getPerson_id());
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){

        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

}
