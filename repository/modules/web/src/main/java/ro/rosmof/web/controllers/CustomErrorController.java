package ro.rosmof.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController {
    @RequestMapping("/badrequest")
    public void showError() {
        //do someting in here
    }
}
