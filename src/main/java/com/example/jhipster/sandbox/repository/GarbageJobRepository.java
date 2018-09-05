package com.example.jhipster.sandbox.repository;

import com.example.jhipster.sandbox.domain.GarbageJob;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GarbageJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GarbageJobRepository extends JpaRepository<GarbageJob, Long> {

}
