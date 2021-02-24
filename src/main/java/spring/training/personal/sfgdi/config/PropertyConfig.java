package spring.training.personal.sfgdi.config;

import spring.training.personal.sfgdi.examplebeans.FakeDataSource;
import spring.training.personal.sfgdi.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
//@PropertySource({"classpath:datasource.properties", "classpath:jms.properties" })
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:jms.properties"),
})
// the externalized file classpath, with classpath in Maven being /src/main/resources
public class PropertyConfig {

    @Autowired
    Environment env;

    // go read that value from the externalized file
    @Value("${guru.username}")
    String user;

    @Value("${guru.password}")
    String password;

    @Value("${guru.dburl}")
    String url;

    @Value("${guru.jms.username}")
    String jmsUser;

    @Value("${guru.jms.password}")
    String jmsPassword;

    @Value("${guru.jms.url}")
    String jmsUrl;

    @Bean
    public FakeDataSource fakeDataSource() {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUser(user);
        fakeDataSource.setPassword(env.getProperty("PASSWORD"));
        fakeDataSource.setUrl(url);
        return fakeDataSource;
    }

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUsername(jmsUser);
        fakeJmsBroker.setPassword(jmsPassword);
        fakeJmsBroker.setUrl(jmsUrl);
        return fakeJmsBroker;
    }

    // Note about the static annotation for below function:
    // Any BeanFactoryPostProcessor beans (This includes PropertySourcesPlaceholderConfigurer) have to be declared static.
    // This ensure that it is instantiated  early in the spring container life-cycle and before the containing @Configuration
    // class so that it can resolve @Value annotation.

    // the PropertySourcesPlaceholderConfigurer is what is going to read the externalized file for us
    // starting with Spring5, it is actually redundant : From Official Doc
    // Use this if you need to customize configuration such as the placeholder syntax, etc.
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
}
