package com.example.jhipster.sandbox.service.mapper;

import com.example.jhipster.sandbox.domain.*;
import com.example.jhipster.sandbox.service.dto.GarbageJobDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GarbageJob and its DTO GarbageJobDTO.
 */
@Mapper(componentModel = "spring", uses = {JobMapper.class})
public interface GarbageJobMapper extends EntityMapper<GarbageJobDTO, GarbageJob> {

    @Mapping(source = "job.id", target = "jobId")
    GarbageJobDTO toDto(GarbageJob garbageJob);

    @Mapping(source = "jobId", target = "job")
    GarbageJob toEntity(GarbageJobDTO garbageJobDTO);

    default GarbageJob fromId(Long id) {
        if (id == null) {
            return null;
        }
        GarbageJob garbageJob = new GarbageJob();
        garbageJob.setId(id);
        return garbageJob;
    }
}
