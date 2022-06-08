package com.konoPlace.konoplace.repositories;

import com.konoPlace.konoplace.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UserRepository extends JpaRepository<UserModel,Long> {

}
