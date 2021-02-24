package spring.training.personal.sfgdi;

import spring.training.personal.sfgdi.controllers.ConstructorInjectedController;
import spring.training.personal.sfgdi.controllers.MyController;
import spring.training.personal.sfgdi.controllers.PropertyInjectedController;
import spring.training.personal.sfgdi.controllers.SetterInjectedController;
import spring.training.personal.sfgdi.examplebeans.FakeDataSource;
import spring.training.personal.sfgdi.examplebeans.FakeJmsBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SfgDiApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

        MyController controller = (MyController) ctx.getBean("myController");

        FakeDataSource fakeDataSource = (FakeDataSource) ctx.getBean(FakeDataSource.class);
        System.out.println(fakeDataSource.getUser());
        System.out.println(fakeDataSource.getPassword());
        System.out.println(fakeDataSource.getUrl());

        FakeJmsBroker fakeJmsBroker = (FakeJmsBroker) ctx.getBean(FakeJmsBroker.class);
        System.out.println(fakeJmsBroker.getUsername());
    }

}
