package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


  
@RepositoryRestResource(path = "packages")
public interface BoxPackageRepository extends MongoRepository<BoxPackage, String> , BoxPackageRepositoryCustom {

	/* @RequestMapping("Bpackage/yourCustomMethod")
	 @ResponseBody
	 BoxPackage yourCustomMethod(@RequestParam("userName") String userName); */
	
//	 @RequestMapping("/ggg") 
//	 @ResponseBody
	 BoxPackage yourCustomMethod(String userName);
	 

	 @RequestMapping(value = "/destination", method = RequestMethod.GET)
	List<BoxPackage> findByPostalCode( @Param("postalCode") String postalCode);

	Users selectUserInfo(String id); //package id
	 ArrayList<Users> selectUserInfo_collection(@Param("id") String id);
	// List<BoxPackage> getPackageByDestination(@Param("postalCode") String postalCode) ;

	
	 
	 /*
	      @RequestMapping(value = "{id}", method = RequestMethod.GET)
    TodoDTO findById(@PathVariable("id") String id) {
        return service.findById(id);
    }
	  */

}
