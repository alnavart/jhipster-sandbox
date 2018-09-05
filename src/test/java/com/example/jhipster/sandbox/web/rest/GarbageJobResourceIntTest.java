package com.example.jhipster.sandbox.web.rest;

import com.example.jhipster.sandbox.SandboxApp;

import com.example.jhipster.sandbox.domain.GarbageJob;
import com.example.jhipster.sandbox.repository.GarbageJobRepository;
import com.example.jhipster.sandbox.service.GarbageJobService;
import com.example.jhipster.sandbox.service.dto.GarbageJobDTO;
import com.example.jhipster.sandbox.service.mapper.GarbageJobMapper;
import com.example.jhipster.sandbox.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.example.jhipster.sandbox.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GarbageJobResource REST controller.
 *
 * @see GarbageJobResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SandboxApp.class)
public class GarbageJobResourceIntTest {

    private static final String DEFAULT_DEPARTMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REAL_JOB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REAL_JOB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REAL_JOB_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_REAL_JOB_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_REAL_SALARY = 1L;
    private static final Long UPDATED_REAL_SALARY = 2L;

    private static final Integer DEFAULT_DISMISS_AFTER_MAX_MONTHS = 1;
    private static final Integer UPDATED_DISMISS_AFTER_MAX_MONTHS = 2;

    private static final Boolean DEFAULT_ALLOW_RENEW = false;
    private static final Boolean UPDATED_ALLOW_RENEW = true;

    @Autowired
    private GarbageJobRepository garbageJobRepository;


    @Autowired
    private GarbageJobMapper garbageJobMapper;
    

    @Autowired
    private GarbageJobService garbageJobService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGarbageJobMockMvc;

    private GarbageJob garbageJob;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GarbageJobResource garbageJobResource = new GarbageJobResource(garbageJobService);
        this.restGarbageJobMockMvc = MockMvcBuilders.standaloneSetup(garbageJobResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GarbageJob createEntity(EntityManager em) {
        GarbageJob garbageJob = new GarbageJob()
            .departmentName(DEFAULT_DEPARTMENT_NAME)
            .realJobName(DEFAULT_REAL_JOB_NAME)
            .realJobDescription(DEFAULT_REAL_JOB_DESCRIPTION)
            .realSalary(DEFAULT_REAL_SALARY)
            .dismissAfterMaxMonths(DEFAULT_DISMISS_AFTER_MAX_MONTHS)
            .allowRenew(DEFAULT_ALLOW_RENEW);
        return garbageJob;
    }

    @Before
    public void initTest() {
        garbageJob = createEntity(em);
    }

    @Test
    @Transactional
    public void createGarbageJob() throws Exception {
        int databaseSizeBeforeCreate = garbageJobRepository.findAll().size();

        // Create the GarbageJob
        GarbageJobDTO garbageJobDTO = garbageJobMapper.toDto(garbageJob);
        restGarbageJobMockMvc.perform(post("/api/garbage-jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garbageJobDTO)))
            .andExpect(status().isCreated());

        // Validate the GarbageJob in the database
        List<GarbageJob> garbageJobList = garbageJobRepository.findAll();
        assertThat(garbageJobList).hasSize(databaseSizeBeforeCreate + 1);
        GarbageJob testGarbageJob = garbageJobList.get(garbageJobList.size() - 1);
        assertThat(testGarbageJob.getDepartmentName()).isEqualTo(DEFAULT_DEPARTMENT_NAME);
        assertThat(testGarbageJob.getRealJobName()).isEqualTo(DEFAULT_REAL_JOB_NAME);
        assertThat(testGarbageJob.getRealJobDescription()).isEqualTo(DEFAULT_REAL_JOB_DESCRIPTION);
        assertThat(testGarbageJob.getRealSalary()).isEqualTo(DEFAULT_REAL_SALARY);
        assertThat(testGarbageJob.getDismissAfterMaxMonths()).isEqualTo(DEFAULT_DISMISS_AFTER_MAX_MONTHS);
        assertThat(testGarbageJob.isAllowRenew()).isEqualTo(DEFAULT_ALLOW_RENEW);
    }

    @Test
    @Transactional
    public void createGarbageJobWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = garbageJobRepository.findAll().size();

        // Create the GarbageJob with an existing ID
        garbageJob.setId(1L);
        GarbageJobDTO garbageJobDTO = garbageJobMapper.toDto(garbageJob);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGarbageJobMockMvc.perform(post("/api/garbage-jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garbageJobDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GarbageJob in the database
        List<GarbageJob> garbageJobList = garbageJobRepository.findAll();
        assertThat(garbageJobList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDepartmentNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = garbageJobRepository.findAll().size();
        // set the field null
        garbageJob.setDepartmentName(null);

        // Create the GarbageJob, which fails.
        GarbageJobDTO garbageJobDTO = garbageJobMapper.toDto(garbageJob);

        restGarbageJobMockMvc.perform(post("/api/garbage-jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garbageJobDTO)))
            .andExpect(status().isBadRequest());

        List<GarbageJob> garbageJobList = garbageJobRepository.findAll();
        assertThat(garbageJobList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGarbageJobs() throws Exception {
        // Initialize the database
        garbageJobRepository.saveAndFlush(garbageJob);

        // Get all the garbageJobList
        restGarbageJobMockMvc.perform(get("/api/garbage-jobs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(garbageJob.getId().intValue())))
            .andExpect(jsonPath("$.[*].departmentName").value(hasItem(DEFAULT_DEPARTMENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].realJobName").value(hasItem(DEFAULT_REAL_JOB_NAME.toString())))
            .andExpect(jsonPath("$.[*].realJobDescription").value(hasItem(DEFAULT_REAL_JOB_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].realSalary").value(hasItem(DEFAULT_REAL_SALARY.intValue())))
            .andExpect(jsonPath("$.[*].dismissAfterMaxMonths").value(hasItem(DEFAULT_DISMISS_AFTER_MAX_MONTHS)))
            .andExpect(jsonPath("$.[*].allowRenew").value(hasItem(DEFAULT_ALLOW_RENEW.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getGarbageJob() throws Exception {
        // Initialize the database
        garbageJobRepository.saveAndFlush(garbageJob);

        // Get the garbageJob
        restGarbageJobMockMvc.perform(get("/api/garbage-jobs/{id}", garbageJob.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(garbageJob.getId().intValue()))
            .andExpect(jsonPath("$.departmentName").value(DEFAULT_DEPARTMENT_NAME.toString()))
            .andExpect(jsonPath("$.realJobName").value(DEFAULT_REAL_JOB_NAME.toString()))
            .andExpect(jsonPath("$.realJobDescription").value(DEFAULT_REAL_JOB_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.realSalary").value(DEFAULT_REAL_SALARY.intValue()))
            .andExpect(jsonPath("$.dismissAfterMaxMonths").value(DEFAULT_DISMISS_AFTER_MAX_MONTHS))
            .andExpect(jsonPath("$.allowRenew").value(DEFAULT_ALLOW_RENEW.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingGarbageJob() throws Exception {
        // Get the garbageJob
        restGarbageJobMockMvc.perform(get("/api/garbage-jobs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGarbageJob() throws Exception {
        // Initialize the database
        garbageJobRepository.saveAndFlush(garbageJob);

        int databaseSizeBeforeUpdate = garbageJobRepository.findAll().size();

        // Update the garbageJob
        GarbageJob updatedGarbageJob = garbageJobRepository.findById(garbageJob.getId()).get();
        // Disconnect from session so that the updates on updatedGarbageJob are not directly saved in db
        em.detach(updatedGarbageJob);
        updatedGarbageJob
            .departmentName(UPDATED_DEPARTMENT_NAME)
            .realJobName(UPDATED_REAL_JOB_NAME)
            .realJobDescription(UPDATED_REAL_JOB_DESCRIPTION)
            .realSalary(UPDATED_REAL_SALARY)
            .dismissAfterMaxMonths(UPDATED_DISMISS_AFTER_MAX_MONTHS)
            .allowRenew(UPDATED_ALLOW_RENEW);
        GarbageJobDTO garbageJobDTO = garbageJobMapper.toDto(updatedGarbageJob);

        restGarbageJobMockMvc.perform(put("/api/garbage-jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garbageJobDTO)))
            .andExpect(status().isOk());

        // Validate the GarbageJob in the database
        List<GarbageJob> garbageJobList = garbageJobRepository.findAll();
        assertThat(garbageJobList).hasSize(databaseSizeBeforeUpdate);
        GarbageJob testGarbageJob = garbageJobList.get(garbageJobList.size() - 1);
        assertThat(testGarbageJob.getDepartmentName()).isEqualTo(UPDATED_DEPARTMENT_NAME);
        assertThat(testGarbageJob.getRealJobName()).isEqualTo(UPDATED_REAL_JOB_NAME);
        assertThat(testGarbageJob.getRealJobDescription()).isEqualTo(UPDATED_REAL_JOB_DESCRIPTION);
        assertThat(testGarbageJob.getRealSalary()).isEqualTo(UPDATED_REAL_SALARY);
        assertThat(testGarbageJob.getDismissAfterMaxMonths()).isEqualTo(UPDATED_DISMISS_AFTER_MAX_MONTHS);
        assertThat(testGarbageJob.isAllowRenew()).isEqualTo(UPDATED_ALLOW_RENEW);
    }

    @Test
    @Transactional
    public void updateNonExistingGarbageJob() throws Exception {
        int databaseSizeBeforeUpdate = garbageJobRepository.findAll().size();

        // Create the GarbageJob
        GarbageJobDTO garbageJobDTO = garbageJobMapper.toDto(garbageJob);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restGarbageJobMockMvc.perform(put("/api/garbage-jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garbageJobDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GarbageJob in the database
        List<GarbageJob> garbageJobList = garbageJobRepository.findAll();
        assertThat(garbageJobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGarbageJob() throws Exception {
        // Initialize the database
        garbageJobRepository.saveAndFlush(garbageJob);

        int databaseSizeBeforeDelete = garbageJobRepository.findAll().size();

        // Get the garbageJob
        restGarbageJobMockMvc.perform(delete("/api/garbage-jobs/{id}", garbageJob.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GarbageJob> garbageJobList = garbageJobRepository.findAll();
        assertThat(garbageJobList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GarbageJob.class);
        GarbageJob garbageJob1 = new GarbageJob();
        garbageJob1.setId(1L);
        GarbageJob garbageJob2 = new GarbageJob();
        garbageJob2.setId(garbageJob1.getId());
        assertThat(garbageJob1).isEqualTo(garbageJob2);
        garbageJob2.setId(2L);
        assertThat(garbageJob1).isNotEqualTo(garbageJob2);
        garbageJob1.setId(null);
        assertThat(garbageJob1).isNotEqualTo(garbageJob2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GarbageJobDTO.class);
        GarbageJobDTO garbageJobDTO1 = new GarbageJobDTO();
        garbageJobDTO1.setId(1L);
        GarbageJobDTO garbageJobDTO2 = new GarbageJobDTO();
        assertThat(garbageJobDTO1).isNotEqualTo(garbageJobDTO2);
        garbageJobDTO2.setId(garbageJobDTO1.getId());
        assertThat(garbageJobDTO1).isEqualTo(garbageJobDTO2);
        garbageJobDTO2.setId(2L);
        assertThat(garbageJobDTO1).isNotEqualTo(garbageJobDTO2);
        garbageJobDTO1.setId(null);
        assertThat(garbageJobDTO1).isNotEqualTo(garbageJobDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(garbageJobMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(garbageJobMapper.fromId(null)).isNull();
    }
}
