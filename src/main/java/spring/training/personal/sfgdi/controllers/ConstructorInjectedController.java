package spring.training.personal.sfgdi.controllers;

import spring.training.personal.sfgdi.services.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ConstructorInjectedController {

    private final GreetingService greetingService;

    // in case of contructor injection, there is no need to add the "autowired" annotation since Spring 4.2
    public ConstructorInjectedController(
            @Qualifier("constructorGreetingService") final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello() {
        return greetingService.sayGreeting();
    }
}
