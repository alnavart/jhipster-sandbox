import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { SandboxRegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { SandboxCountryMySuffixModule } from './country-my-suffix/country-my-suffix.module';
import { SandboxLocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { SandboxDepartmentMySuffixModule } from './department-my-suffix/department-my-suffix.module';
import { SandboxTaskMySuffixModule } from './task-my-suffix/task-my-suffix.module';
import { SandboxEmployeeMySuffixModule } from './employee-my-suffix/employee-my-suffix.module';
import { SandboxJobMySuffixModule } from './job-my-suffix/job-my-suffix.module';
import { SandboxJobHistoryMySuffixModule } from './job-history-my-suffix/job-history-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        SandboxRegionMySuffixModule,
        SandboxCountryMySuffixModule,
        SandboxLocationMySuffixModule,
        SandboxDepartmentMySuffixModule,
        SandboxTaskMySuffixModule,
        SandboxEmployeeMySuffixModule,
        SandboxJobMySuffixModule,
        SandboxJobHistoryMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SandboxEntityModule {}
