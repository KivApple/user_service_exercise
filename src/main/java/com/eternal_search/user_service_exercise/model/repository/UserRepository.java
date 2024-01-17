package com.eternal_search.user_service_exercise.model.repository;

import com.eternal_search.user_service_exercise.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
