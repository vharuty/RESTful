package hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories
@ComponentScan
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
class ApplicationConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "e-store";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new Mongo();
	}

	@Override
	protected String getMappingBasePackage() {
		return "hello";
	}
}