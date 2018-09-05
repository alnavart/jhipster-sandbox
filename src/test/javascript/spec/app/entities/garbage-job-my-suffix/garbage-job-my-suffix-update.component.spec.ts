/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SandboxTestModule } from '../../../test.module';
import { GarbageJobMySuffixUpdateComponent } from 'app/entities/garbage-job-my-suffix/garbage-job-my-suffix-update.component';
import { GarbageJobMySuffixService } from 'app/entities/garbage-job-my-suffix/garbage-job-my-suffix.service';
import { GarbageJobMySuffix } from 'app/shared/model/garbage-job-my-suffix.model';

describe('Component Tests', () => {
    describe('GarbageJobMySuffix Management Update Component', () => {
        let comp: GarbageJobMySuffixUpdateComponent;
        let fixture: ComponentFixture<GarbageJobMySuffixUpdateComponent>;
        let service: GarbageJobMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SandboxTestModule],
                declarations: [GarbageJobMySuffixUpdateComponent]
            })
                .overrideTemplate(GarbageJobMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GarbageJobMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GarbageJobMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GarbageJobMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.garbageJob = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GarbageJobMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.garbageJob = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
