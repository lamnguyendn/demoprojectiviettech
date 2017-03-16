package spring.demo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import spring.demo.entity.UserEntity;

/**
 * Created by vhphat on 10/8/2016.
 */
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update UserEntity u set u.activationCode = null where u.email = ?1 and u.activationCode = ?2")
    int activateUser(String email, String activationCode);

    UserEntity findByEmail(String email);
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByEmailAndActivationCodeNotNull(String email);
}
