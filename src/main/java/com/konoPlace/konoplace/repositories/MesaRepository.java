package com.konoPlace.konoplace.repositories;

import com.konoPlace.konoplace.models.MesaModel;
import com.konoPlace.konoplace.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<MesaModel,Long> {
    public MesaModel findByName(String name);
    public List<MesaModel> findAll();
}
