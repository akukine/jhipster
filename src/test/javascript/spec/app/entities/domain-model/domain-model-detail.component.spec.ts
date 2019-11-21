import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhispterTestModule } from '../../../test.module';
import { DomainModelDetailComponent } from 'app/entities/domain-model/domain-model-detail.component';
import { DomainModel } from 'app/shared/model/domain-model.model';

describe('Component Tests', () => {
  describe('DomainModel Management Detail Component', () => {
    let comp: DomainModelDetailComponent;
    let fixture: ComponentFixture<DomainModelDetailComponent>;
    const route = ({ data: of({ domainModel: new DomainModel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhispterTestModule],
        declarations: [DomainModelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DomainModelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DomainModelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.domainModel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
