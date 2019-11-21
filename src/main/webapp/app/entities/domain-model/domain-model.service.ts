import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDomainModel } from 'app/shared/model/domain-model.model';

type EntityResponseType = HttpResponse<IDomainModel>;
type EntityArrayResponseType = HttpResponse<IDomainModel[]>;

@Injectable({ providedIn: 'root' })
export class DomainModelService {
  public resourceUrl = SERVER_API_URL + 'api/domain-models';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDomainModel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDomainModel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
