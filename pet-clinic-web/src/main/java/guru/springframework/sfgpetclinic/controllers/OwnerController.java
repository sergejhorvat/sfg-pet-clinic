package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners") // to use shorted mappings bellow
@Controller
public class OwnerController {

    //@RequestMapping({"/owners","/owners/index","/owners/index.html"})
    @RequestMapping({"","/","/index","/index.html"})
    public String listOwners(){

        return  "owners/index";
    }
}
