package com.projeto.changebookusers.repository;

import com.projeto.changebookusers.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByCpf(String cpf);
    Boolean existsByCpf(String cpf);
}
