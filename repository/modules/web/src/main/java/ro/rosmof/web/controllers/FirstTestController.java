package ro.rosmof.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.rosmof.model.entities.User;
import ro.rosmof.services.ErrorService;
import ro.rosmof.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Controller
public class FirstTestController {

    private static final Logger logger = LoggerFactory.getLogger(FirstTestController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ErrorService errorService;

    @RequestMapping(value = "/diploma", method = RequestMethod.GET)
    public String getHomeRequest(HttpServletRequest request, HttpServletResponse response) {
        logger.info("controller method called");
        try {

            List<User> userList = new ArrayList<>();
            IntStream.range(1, 10).forEach(i -> {
                User u = new User();
                u.setUsername("swe" + new Random().nextInt());
                u.setPassword("^^^^^");
                userList.add(u);
            });

            userService.saveUserLists(userList);
        } catch (Exception e) {
            logger.info("Exception on controller level - XXXX");
            errorService.saveErrorWithNewTransaction(e);
        }

        return "home_view";
    }
}
