import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGarbageJobMySuffix } from 'app/shared/model/garbage-job-my-suffix.model';

@Component({
    selector: 'jhi-garbage-job-my-suffix-detail',
    templateUrl: './garbage-job-my-suffix-detail.component.html'
})
export class GarbageJobMySuffixDetailComponent implements OnInit {
    garbageJob: IGarbageJobMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ garbageJob }) => {
            this.garbageJob = garbageJob;
        });
    }

    previousState() {
        window.history.back();
    }
}
