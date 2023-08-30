package vmware.services.gateway.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vmware.services.gateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String username);
    User findFirstByEmail(String email);
    Optional<User> findByEmail(String email);

    @Query(value = "select DISTINCT u from User u where u.userName = :username")
    Optional<User> findByUserName(String username);


}
