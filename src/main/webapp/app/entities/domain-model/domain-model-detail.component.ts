import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDomainModel } from 'app/shared/model/domain-model.model';

@Component({
  selector: 'jhi-domain-model-detail',
  templateUrl: './domain-model-detail.component.html'
})
export class DomainModelDetailComponent implements OnInit {
  domainModel: IDomainModel;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ domainModel }) => {
      this.domainModel = domainModel;
    });
  }

  previousState() {
    window.history.back();
  }
}
