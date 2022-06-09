package com.konoPlace.konoplace.repositories;

import com.konoPlace.konoplace.models.MesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<MesaModel,Long> {
}
