import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGarbageJobMySuffix } from 'app/shared/model/garbage-job-my-suffix.model';
import { Principal } from 'app/core';
import { GarbageJobMySuffixService } from './garbage-job-my-suffix.service';

@Component({
    selector: 'jhi-garbage-job-my-suffix',
    templateUrl: './garbage-job-my-suffix.component.html'
})
export class GarbageJobMySuffixComponent implements OnInit, OnDestroy {
    garbageJobs: IGarbageJobMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private garbageJobService: GarbageJobMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.garbageJobService.query().subscribe(
            (res: HttpResponse<IGarbageJobMySuffix[]>) => {
                this.garbageJobs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGarbageJobs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGarbageJobMySuffix) {
        return item.id;
    }

    registerChangeInGarbageJobs() {
        this.eventSubscriber = this.eventManager.subscribe('garbageJobListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
