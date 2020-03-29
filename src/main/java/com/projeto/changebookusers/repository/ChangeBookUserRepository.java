package com.projeto.changebookusers.repository;

import com.projeto.changebookusers.domain.ChangeBookUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeBookUserRepository extends PagingAndSortingRepository<ChangeBookUser, String> {
    ChangeBookUser findByEmail(String email);
}
