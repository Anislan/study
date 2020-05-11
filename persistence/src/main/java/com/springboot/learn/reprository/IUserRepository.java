package com.springboot.learn.reprository;

import com.springboot.learn.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserRepository extends JpaSpecificationExecutor<User>,CrudRepository<User,Long>{
    User findByUserName(String userName);
}
