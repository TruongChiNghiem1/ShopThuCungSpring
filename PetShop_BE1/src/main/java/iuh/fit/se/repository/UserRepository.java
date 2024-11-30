package iuh.fit.se.repository;

import iuh.fit.se.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByEmailAddress(String emailAddress);

	User findByEmailAddressAndPassword(String emailAddress, String password);
}
