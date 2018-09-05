package com.example.jhipster.sandbox.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.jhipster.sandbox.service.GarbageJobService;
import com.example.jhipster.sandbox.web.rest.errors.BadRequestAlertException;
import com.example.jhipster.sandbox.web.rest.util.HeaderUtil;
import com.example.jhipster.sandbox.service.dto.GarbageJobDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing GarbageJob.
 */
@RestController
@RequestMapping("/api")
public class GarbageJobResource {

    private final Logger log = LoggerFactory.getLogger(GarbageJobResource.class);

    private static final String ENTITY_NAME = "garbageJob";

    private final GarbageJobService garbageJobService;

    public GarbageJobResource(GarbageJobService garbageJobService) {
        this.garbageJobService = garbageJobService;
    }

    /**
     * POST  /garbage-jobs : Create a new garbageJob.
     *
     * @param garbageJobDTO the garbageJobDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new garbageJobDTO, or with status 400 (Bad Request) if the garbageJob has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/garbage-jobs")
    @Timed
    public ResponseEntity<GarbageJobDTO> createGarbageJob(@Valid @RequestBody GarbageJobDTO garbageJobDTO) throws URISyntaxException {
        log.debug("REST request to save GarbageJob : {}", garbageJobDTO);
        if (garbageJobDTO.getId() != null) {
            throw new BadRequestAlertException("A new garbageJob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GarbageJobDTO result = garbageJobService.save(garbageJobDTO);
        return ResponseEntity.created(new URI("/api/garbage-jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /garbage-jobs : Updates an existing garbageJob.
     *
     * @param garbageJobDTO the garbageJobDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated garbageJobDTO,
     * or with status 400 (Bad Request) if the garbageJobDTO is not valid,
     * or with status 500 (Internal Server Error) if the garbageJobDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/garbage-jobs")
    @Timed
    public ResponseEntity<GarbageJobDTO> updateGarbageJob(@Valid @RequestBody GarbageJobDTO garbageJobDTO) throws URISyntaxException {
        log.debug("REST request to update GarbageJob : {}", garbageJobDTO);
        if (garbageJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GarbageJobDTO result = garbageJobService.save(garbageJobDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, garbageJobDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /garbage-jobs : get all the garbageJobs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of garbageJobs in body
     */
    @GetMapping("/garbage-jobs")
    @Timed
    public List<GarbageJobDTO> getAllGarbageJobs() {
        log.debug("REST request to get all GarbageJobs");
        return garbageJobService.findAll();
    }

    /**
     * GET  /garbage-jobs/:id : get the "id" garbageJob.
     *
     * @param id the id of the garbageJobDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the garbageJobDTO, or with status 404 (Not Found)
     */
    @GetMapping("/garbage-jobs/{id}")
    @Timed
    public ResponseEntity<GarbageJobDTO> getGarbageJob(@PathVariable Long id) {
        log.debug("REST request to get GarbageJob : {}", id);
        Optional<GarbageJobDTO> garbageJobDTO = garbageJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(garbageJobDTO);
    }

    /**
     * DELETE  /garbage-jobs/:id : delete the "id" garbageJob.
     *
     * @param id the id of the garbageJobDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/garbage-jobs/{id}")
    @Timed
    public ResponseEntity<Void> deleteGarbageJob(@PathVariable Long id) {
        log.debug("REST request to delete GarbageJob : {}", id);
        garbageJobService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
