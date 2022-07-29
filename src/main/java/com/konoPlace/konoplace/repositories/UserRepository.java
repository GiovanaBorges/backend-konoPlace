package com.konoPlace.konoplace.repositories;

import com.konoPlace.konoplace.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    public UserModel findByEmail(String email);
    public Optional<UserModel> findById(Long id);

}
