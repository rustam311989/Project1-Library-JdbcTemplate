package nachren.controllers;

import nachren.dao.BookDAO;
import nachren.dao.PersonDAO;
import nachren.models.Book;
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
@RequestMapping("/books")
public class BookController {


    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final DataSource dataSource;


    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO, DataSource dataSource) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.dataSource = dataSource;
    }


    @GetMapping()
    public String index(Model model){
        // Получим людей из дао и передадим их на представление
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        // получим книгу из дао и передадим ее на представление
        Book book = bookDAO.show(id);
        //model.addAttribute("person", new Person());
        model.addAttribute("book", book);
        model.addAttribute("owner", personDAO.show(book.getPerson_id()));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "books/new";
        }
        //System.out.println(person.getPerson_id());
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){

        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/doFree")
    public String doFree(@PathVariable("id") int id){
        bookDAO.update2(id, null);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/toAppoint")
    public String toAppoint(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookDAO.update2(id,person.getPerson_id());
        return "redirect:/books/{id}";
    }

}
