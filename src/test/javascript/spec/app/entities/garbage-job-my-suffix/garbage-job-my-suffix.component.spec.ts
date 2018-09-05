/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SandboxTestModule } from '../../../test.module';
import { GarbageJobMySuffixComponent } from 'app/entities/garbage-job-my-suffix/garbage-job-my-suffix.component';
import { GarbageJobMySuffixService } from 'app/entities/garbage-job-my-suffix/garbage-job-my-suffix.service';
import { GarbageJobMySuffix } from 'app/shared/model/garbage-job-my-suffix.model';

describe('Component Tests', () => {
    describe('GarbageJobMySuffix Management Component', () => {
        let comp: GarbageJobMySuffixComponent;
        let fixture: ComponentFixture<GarbageJobMySuffixComponent>;
        let service: GarbageJobMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SandboxTestModule],
                declarations: [GarbageJobMySuffixComponent],
                providers: []
            })
                .overrideTemplate(GarbageJobMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GarbageJobMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GarbageJobMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new GarbageJobMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.garbageJobs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
