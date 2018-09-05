import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IGarbageJobMySuffix } from 'app/shared/model/garbage-job-my-suffix.model';
import { GarbageJobMySuffixService } from './garbage-job-my-suffix.service';
import { IJobMySuffix } from 'app/shared/model/job-my-suffix.model';
import { JobMySuffixService } from 'app/entities/job-my-suffix';

@Component({
    selector: 'jhi-garbage-job-my-suffix-update',
    templateUrl: './garbage-job-my-suffix-update.component.html'
})
export class GarbageJobMySuffixUpdateComponent implements OnInit {
    private _garbageJob: IGarbageJobMySuffix;
    isSaving: boolean;

    jobs: IJobMySuffix[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private garbageJobService: GarbageJobMySuffixService,
        private jobService: JobMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ garbageJob }) => {
            this.garbageJob = garbageJob;
        });
        this.jobService.query().subscribe(
            (res: HttpResponse<IJobMySuffix[]>) => {
                this.jobs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.garbageJob.id !== undefined) {
            this.subscribeToSaveResponse(this.garbageJobService.update(this.garbageJob));
        } else {
            this.subscribeToSaveResponse(this.garbageJobService.create(this.garbageJob));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGarbageJobMySuffix>>) {
        result.subscribe((res: HttpResponse<IGarbageJobMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackJobById(index: number, item: IJobMySuffix) {
        return item.id;
    }
    get garbageJob() {
        return this._garbageJob;
    }

    set garbageJob(garbageJob: IGarbageJobMySuffix) {
        this._garbageJob = garbageJob;
    }
}
