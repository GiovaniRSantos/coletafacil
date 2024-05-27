package com.api.coletafacil.repositories;


import com.api.coletafacil.models.BasedescarteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasedescarteRepository extends JpaRepository<BasedescarteModel, Integer> {
    // Métodos específicos, se necessário
}
