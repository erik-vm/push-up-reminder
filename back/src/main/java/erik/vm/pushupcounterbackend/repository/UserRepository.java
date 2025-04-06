package erik.vm.pushupcounterbackend.repository;

import erik.vm.pushupcounterbackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
}
