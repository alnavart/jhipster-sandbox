import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SandboxSharedModule } from 'app/shared';
import {
    GarbageJobMySuffixComponent,
    GarbageJobMySuffixDetailComponent,
    GarbageJobMySuffixUpdateComponent,
    GarbageJobMySuffixDeletePopupComponent,
    GarbageJobMySuffixDeleteDialogComponent,
    garbageJobRoute,
    garbageJobPopupRoute
} from './';

const ENTITY_STATES = [...garbageJobRoute, ...garbageJobPopupRoute];

@NgModule({
    imports: [SandboxSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GarbageJobMySuffixComponent,
        GarbageJobMySuffixDetailComponent,
        GarbageJobMySuffixUpdateComponent,
        GarbageJobMySuffixDeleteDialogComponent,
        GarbageJobMySuffixDeletePopupComponent
    ],
    entryComponents: [
        GarbageJobMySuffixComponent,
        GarbageJobMySuffixUpdateComponent,
        GarbageJobMySuffixDeleteDialogComponent,
        GarbageJobMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SandboxGarbageJobMySuffixModule {}
