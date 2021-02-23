package spring.training.personal.sfgdi.config;

import spring.training.personal.sfgdi.services.GreetingRepository;
import spring.training.personal.sfgdi.services.GreetingService;
import spring.training.personal.sfgdi.services.GreetingServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class GreetingServiceConfig {

    // this injects GreetingRepository (it is a @Component) into GreetingServiceFactory, and the created factory is registered
    // as a Bean thanks to the @Bean annotation of this method
    @Bean
    GreetingServiceFactory greetingServiceFactory(GreetingRepository repository) {
        return new GreetingServiceFactory(repository);
    }

    // this injects GreetingServiceFactory (it is a @Bean thanks to previous method) to call its createGreetingService method
    // and the injected GreetingRepository of the factory is injected in the GreetingService because it was injected in the
    // factory at creation
    // The newly created GreetingService is registered as the Primary (thanks to @Primary) Bean (thanks to @Bean) greetingService
    // for profiles default, en
    // Primary was necessary because of the ContructorGreetingService, GreetingServiceImpl, SetterInjectedGreetingService and
    // PropertyInjectedGreetingService which are not limited to some profiles but also implements the GreetingService interface
    // and are defined as Bean (through @Service)
    @Bean
    @Primary
    @Profile({"default", "en"})
    GreetingService primaryGreetingService(GreetingServiceFactory greetingServiceFactory) {
        return greetingServiceFactory.createGreetingService("en");
    }

    // The newly created GreetingService is registered as the Primary (thanks to @Primary) Bean (thanks to @Bean) greetingService
    // for profiles es
    @Bean
    @Primary
    @Profile({"es"})
    GreetingService primarySpanishGreetingService(GreetingServiceFactory greetingServiceFactory) {
        return greetingServiceFactory.createGreetingService("es");
    }

    // The newly created GreetingService is registered as the Primary (thanks to @Primary) Bean (thanks to @Bean) greetingService
    // for profiles de
    @Bean
    @Primary
    @Profile({"de"})
    GreetingService primaryGermanGreetingService(GreetingServiceFactory greetingServiceFactory) {
        return greetingServiceFactory.createGreetingService("de");
    }
}
