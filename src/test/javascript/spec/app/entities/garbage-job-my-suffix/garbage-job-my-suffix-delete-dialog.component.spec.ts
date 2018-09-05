/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SandboxTestModule } from '../../../test.module';
import { GarbageJobMySuffixDeleteDialogComponent } from 'app/entities/garbage-job-my-suffix/garbage-job-my-suffix-delete-dialog.component';
import { GarbageJobMySuffixService } from 'app/entities/garbage-job-my-suffix/garbage-job-my-suffix.service';

describe('Component Tests', () => {
    describe('GarbageJobMySuffix Management Delete Component', () => {
        let comp: GarbageJobMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<GarbageJobMySuffixDeleteDialogComponent>;
        let service: GarbageJobMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SandboxTestModule],
                declarations: [GarbageJobMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(GarbageJobMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GarbageJobMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GarbageJobMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
