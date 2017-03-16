package restful.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restful.entity.AccountEntity;

/**
 * Created by TungNguyen on 8/12/16.
 */
@Repository
public interface AccountRepository extends CrudRepository<AccountEntity,Integer> {
}
