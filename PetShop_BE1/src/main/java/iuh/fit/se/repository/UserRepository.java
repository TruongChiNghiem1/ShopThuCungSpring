package iuh.fit.se.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import iuh.fit.se.entities.User;

@RepositoryRestResource(collectionResourceRel = "users",path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmailAddress(String emailAddress);
	Optional<User> findByUsername(String username);

}