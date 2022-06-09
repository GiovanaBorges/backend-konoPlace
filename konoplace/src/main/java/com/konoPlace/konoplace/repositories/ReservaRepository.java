package com.konoPlace.konoplace.repositories;

import com.konoPlace.konoplace.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel,Long> {
}
