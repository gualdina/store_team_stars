package com.bootcamp.store.repository;

import com.bootcamp.store.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Primary
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
