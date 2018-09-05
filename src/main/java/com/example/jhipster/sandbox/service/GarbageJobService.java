package com.example.jhipster.sandbox.service;

import com.example.jhipster.sandbox.service.dto.GarbageJobDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing GarbageJob.
 */
public interface GarbageJobService {

    /**
     * Save a garbageJob.
     *
     * @param garbageJobDTO the entity to save
     * @return the persisted entity
     */
    GarbageJobDTO save(GarbageJobDTO garbageJobDTO);

    /**
     * Get all the garbageJobs.
     *
     * @return the list of entities
     */
    List<GarbageJobDTO> findAll();


    /**
     * Get the "id" garbageJob.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GarbageJobDTO> findOne(Long id);

    /**
     * Delete the "id" garbageJob.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
