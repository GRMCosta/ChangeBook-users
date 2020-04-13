package com.projeto.changebookusers.repository;

import com.projeto.changebookusers.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findByEmail(String email);
}
