package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners") // to use shorted mappings bellow
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    // Constructor injection
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //@RequestMapping({"/owners","/owners/index","/owners/index.html"})
    @RequestMapping({"","/","/index","/index.html"})

    // Bring a model when call a listOwner
    public String listOwners(Model model){

        // MVC inject modelto Thymeleaf and add attribute owner that finds all owners
        model.addAttribute("owners", ownerService.findAll());

        // use model in index page of owners
        return  "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners(){
        return "notImplemented";
    }
}
