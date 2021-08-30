package com.yiran.tacocloud.repository;

import com.yiran.tacocloud.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUseryByUsername(String username);
}
