import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DomainModel } from 'app/shared/model/domain-model.model';
import { DomainModelService } from './domain-model.service';
import { DomainModelComponent } from './domain-model.component';
import { DomainModelDetailComponent } from './domain-model-detail.component';
import { IDomainModel } from 'app/shared/model/domain-model.model';

@Injectable({ providedIn: 'root' })
export class DomainModelResolve implements Resolve<IDomainModel> {
  constructor(private service: DomainModelService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDomainModel> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((domainModel: HttpResponse<DomainModel>) => domainModel.body));
    }
    return of(new DomainModel());
  }
}

export const domainModelRoute: Routes = [
  {
    path: '',
    component: DomainModelComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'jhispterApp.domainModel.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DomainModelDetailComponent,
    resolve: {
      domainModel: DomainModelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhispterApp.domainModel.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const domainModelPopupRoute: Routes = [];
