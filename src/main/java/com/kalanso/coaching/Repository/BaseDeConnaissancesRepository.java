package com.kalanso.coaching.Repository;


import com.kalanso.coaching.Model.BaseDeConnaissances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseDeConnaissancesRepository extends JpaRepository<BaseDeConnaissances, Long> {
}
