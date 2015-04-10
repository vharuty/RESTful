package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.MediaType;
//import org.codehaus.jackson.map.ObjectMapper;

@Configuration
@EnableMongoRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        RepositoryRestConfiguration restConfiguration = ctx.getBean(RepositoryRestConfiguration.class);
        //restConfiguration.setLimitParamName(limitParamName)
        
        restConfiguration.setDefaultPageSize(30);
    
	restConfiguration.setDefaultMediaType(MediaType.APPLICATION_JSON);
	restConfiguration.setMaxPageSize(60);
       
        restConfiguration.useHalAsDefaultJsonMediaType(false);
        
        restConfiguration.setReturnBodyOnCreate(true);
        
		
		
	}
}