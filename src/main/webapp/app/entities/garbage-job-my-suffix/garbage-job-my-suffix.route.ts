import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GarbageJobMySuffix } from 'app/shared/model/garbage-job-my-suffix.model';
import { GarbageJobMySuffixService } from './garbage-job-my-suffix.service';
import { GarbageJobMySuffixComponent } from './garbage-job-my-suffix.component';
import { GarbageJobMySuffixDetailComponent } from './garbage-job-my-suffix-detail.component';
import { GarbageJobMySuffixUpdateComponent } from './garbage-job-my-suffix-update.component';
import { GarbageJobMySuffixDeletePopupComponent } from './garbage-job-my-suffix-delete-dialog.component';
import { IGarbageJobMySuffix } from 'app/shared/model/garbage-job-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class GarbageJobMySuffixResolve implements Resolve<IGarbageJobMySuffix> {
    constructor(private service: GarbageJobMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((garbageJob: HttpResponse<GarbageJobMySuffix>) => garbageJob.body));
        }
        return of(new GarbageJobMySuffix());
    }
}

export const garbageJobRoute: Routes = [
    {
        path: 'garbage-job-my-suffix',
        component: GarbageJobMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sandboxApp.garbageJob.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'garbage-job-my-suffix/:id/view',
        component: GarbageJobMySuffixDetailComponent,
        resolve: {
            garbageJob: GarbageJobMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sandboxApp.garbageJob.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'garbage-job-my-suffix/new',
        component: GarbageJobMySuffixUpdateComponent,
        resolve: {
            garbageJob: GarbageJobMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sandboxApp.garbageJob.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'garbage-job-my-suffix/:id/edit',
        component: GarbageJobMySuffixUpdateComponent,
        resolve: {
            garbageJob: GarbageJobMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sandboxApp.garbageJob.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const garbageJobPopupRoute: Routes = [
    {
        path: 'garbage-job-my-suffix/:id/delete',
        component: GarbageJobMySuffixDeletePopupComponent,
        resolve: {
            garbageJob: GarbageJobMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sandboxApp.garbageJob.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
