package spring.training.personal.sfgdi.controllers;

import spring.training.services.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ContructorInjectedController {

    private final GreetingService greetingService;

    // in case of contructor injection, there is no need to add the "autowired" annotation since Spring 4.2
    public ContructorInjectedController(
            @Qualifier("constructorGreetingService") final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting() {
        return greetingService.sayGreeting();
    }
}
