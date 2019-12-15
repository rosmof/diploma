package ro.rosmof.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FirstTestController {

    private static final Logger logger = LoggerFactory.getLogger(FirstTestController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomeRequest(HttpServletRequest request, HttpServletResponse response) {
        logger.info("controller method called");
        return "home_view";
    }
}
