package com.konoPlace.konoplace.repositories;

import com.konoPlace.konoplace.models.ReservaModel;
import com.konoPlace.konoplace.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel,Long> {
    public Optional<ReservaModel> findByDate(String email);

    public Optional<ReservaModel> findByUser(Optional<UserModel> user);
    public List<ReservaModel> findAll();
}
