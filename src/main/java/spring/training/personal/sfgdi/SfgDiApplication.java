package spring.training.personal.sfgdi;

import spring.training.personal.sfgdi.controllers.ContructorInjectedController;
import spring.training.personal.sfgdi.controllers.I18nController;
import spring.training.personal.sfgdi.controllers.MyController;
import spring.training.personal.sfgdi.controllers.PetController;
import spring.training.personal.sfgdi.controllers.PropertyInjectedController;
import spring.training.personal.sfgdi.controllers.SetterInjectedController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// when doing component scan on a package, it does scan a whole package tree, not just on the package but on its sub-packages inside too
// note that adding the component scan overwrites the default behavior of Spring Boot so need to add all packages
@ComponentScan(basePackages = {"spring.training.services", "spring.training.personal"})
public class SfgDiApplication {

    public static void main(String[] args) {
        // SpringApplication.run(SfgDiApplication.class, args); builds then returns the Spring Application Context
        ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

        PetController petController = ctx.getBean("petController", PetController.class);

        System.out.println("--- The Best Pet is ---");
        System.out.println(petController.whichPetIsTheBest());

        I18nController i18nController = (I18nController) ctx.getBean("i18nController");
        System.out.println(i18nController.sayHello());

        // when Spring creates beans, the string name of the bean is going to be the class name starting with a lower case letter
        // Note that MyController was never actively instantiated, it is done automatically by Spring here when we ask
        // for an instance of it from the Context
        MyController myController = (MyController) ctx.getBean("myController");

        System.out.println(" -------------- Primary");
        System.out.println(myController.sayHello());

        System.out.println(" -------------- Property");

        PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx
                .getBean("propertyInjectedController");

        System.out.println(propertyInjectedController.getGreeting());

        System.out.println(" -------------- Setter");

        SetterInjectedController setterInjectedController = (SetterInjectedController) ctx
                .getBean("setterInjectedController");

        System.out.println(setterInjectedController.getGreeting());

        System.out.println(" -------------- Constructor");

        ContructorInjectedController contructorInjectedController = (ContructorInjectedController) ctx
                .getBean("contructorInjectedController");

        System.out.println(contructorInjectedController.getGreeting());
    }

}
