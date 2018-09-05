package com.example.jhipster.sandbox.service.impl;

import com.example.jhipster.sandbox.service.GarbageJobService;
import com.example.jhipster.sandbox.domain.GarbageJob;
import com.example.jhipster.sandbox.repository.GarbageJobRepository;
import com.example.jhipster.sandbox.service.dto.GarbageJobDTO;
import com.example.jhipster.sandbox.service.mapper.GarbageJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing GarbageJob.
 */
@Service
@Transactional
public class GarbageJobServiceImpl implements GarbageJobService {

    private final Logger log = LoggerFactory.getLogger(GarbageJobServiceImpl.class);

    private final GarbageJobRepository garbageJobRepository;

    private final GarbageJobMapper garbageJobMapper;

    public GarbageJobServiceImpl(GarbageJobRepository garbageJobRepository, GarbageJobMapper garbageJobMapper) {
        this.garbageJobRepository = garbageJobRepository;
        this.garbageJobMapper = garbageJobMapper;
    }

    /**
     * Save a garbageJob.
     *
     * @param garbageJobDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GarbageJobDTO save(GarbageJobDTO garbageJobDTO) {
        log.debug("Request to save GarbageJob : {}", garbageJobDTO);
        GarbageJob garbageJob = garbageJobMapper.toEntity(garbageJobDTO);
        garbageJob = garbageJobRepository.save(garbageJob);
        return garbageJobMapper.toDto(garbageJob);
    }

    /**
     * Get all the garbageJobs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<GarbageJobDTO> findAll() {
        log.debug("Request to get all GarbageJobs");
        return garbageJobRepository.findAll().stream()
            .map(garbageJobMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one garbageJob by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GarbageJobDTO> findOne(Long id) {
        log.debug("Request to get GarbageJob : {}", id);
        return garbageJobRepository.findById(id)
            .map(garbageJobMapper::toDto);
    }

    /**
     * Delete the garbageJob by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GarbageJob : {}", id);
        garbageJobRepository.deleteById(id);
    }
}
