/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SandboxTestModule } from '../../../test.module';
import { GarbageJobMySuffixDetailComponent } from 'app/entities/garbage-job-my-suffix/garbage-job-my-suffix-detail.component';
import { GarbageJobMySuffix } from 'app/shared/model/garbage-job-my-suffix.model';

describe('Component Tests', () => {
    describe('GarbageJobMySuffix Management Detail Component', () => {
        let comp: GarbageJobMySuffixDetailComponent;
        let fixture: ComponentFixture<GarbageJobMySuffixDetailComponent>;
        const route = ({ data: of({ garbageJob: new GarbageJobMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SandboxTestModule],
                declarations: [GarbageJobMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GarbageJobMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GarbageJobMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.garbageJob).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
