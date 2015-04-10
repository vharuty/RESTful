package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//http://45.56.67.168:8080/users/search/findByUserName?userName=Vika2


@RepositoryRestResource(path = "users")  
public interface UsersRepository extends MongoRepository<Users, String>, UsersRepositoryCustom{
	
Users findByUserName(@Param("userName") String userName);
	

	
//	@Query(value="{userName : ?0, password : ?1}")
// this method is replaied by a new one in the UsersRepositoryImple
	Users findByUserNameAndPassword( @Param("userName") String userName, @Param("password") String password);

}
