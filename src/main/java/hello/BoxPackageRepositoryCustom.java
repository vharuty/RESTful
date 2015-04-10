package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface BoxPackageRepositoryCustom  {
	//BoxPackage yourCustomMethod(String userName);
	BoxPackage yourCustomMethod(String userName);
	 Users selectUserInfo(String id);
	 
	 ArrayList<Users> selectUserInfo_collection(String id);
		public HashMap<String,List<BoxPackage>> getPackageByDestination(float lang, float lant) ;
		public void obsoletePackage(@PathVariable("id") String id);
}
