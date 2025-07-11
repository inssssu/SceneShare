package bitc.full502.sceneshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SceneShareController {

    @GetMapping
    public String index() {
        return "index";
    }



}
