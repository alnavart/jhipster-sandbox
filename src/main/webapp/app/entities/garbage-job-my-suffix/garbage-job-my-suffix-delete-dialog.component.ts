import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGarbageJobMySuffix } from 'app/shared/model/garbage-job-my-suffix.model';
import { GarbageJobMySuffixService } from './garbage-job-my-suffix.service';

@Component({
    selector: 'jhi-garbage-job-my-suffix-delete-dialog',
    templateUrl: './garbage-job-my-suffix-delete-dialog.component.html'
})
export class GarbageJobMySuffixDeleteDialogComponent {
    garbageJob: IGarbageJobMySuffix;

    constructor(
        private garbageJobService: GarbageJobMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.garbageJobService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'garbageJobListModification',
                content: 'Deleted an garbageJob'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-garbage-job-my-suffix-delete-popup',
    template: ''
})
export class GarbageJobMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ garbageJob }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GarbageJobMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.garbageJob = garbageJob;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
