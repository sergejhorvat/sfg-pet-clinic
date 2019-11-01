package guru.springframework.sfgpetclinic.controllers;


import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners") // to use shorted mappings bellow
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    // Constructor injection
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // Initial web data binding to Java object via @initBinding
    @InitBinder
    public void setAllowedField(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
// Implementation has changed fot other @GetMapping
    //@RequestMapping({"/owners","/owners/index","/owners/index.html"})
//    @RequestMapping({"","/","/index","/index.html"})
//
//    // Bring a model when call a listOwner
//    public String listOwners(Model model){
//
//        // MVC inject modelto Thymeleaf and add attribute owner that finds all owners
//        model.addAttribute("owners", ownerService.findAll());
//
//        // use model in index page of owners
//        return  "owners/index";
//    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        //allow paremetress GET request for /owners to return all records
        if(owner.getLastName()==null){
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners bny last name
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName()+ "%");

        if(results.isEmpty()){
            // no owners found
            result.rejectValue("lastName","notFound","not found");
            return "owners/findOwners";
        }else if (results.size()== 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/"+ owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownerList";
        }

    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}
