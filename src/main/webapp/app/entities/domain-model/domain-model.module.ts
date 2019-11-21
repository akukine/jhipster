import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhispterSharedModule } from 'app/shared/shared.module';
import { DomainModelComponent } from './domain-model.component';
import { DomainModelDetailComponent } from './domain-model-detail.component';
import { domainModelRoute, domainModelPopupRoute } from './domain-model.route';

const ENTITY_STATES = [...domainModelRoute, ...domainModelPopupRoute];

@NgModule({
  imports: [JhispterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [DomainModelComponent, DomainModelDetailComponent],
  entryComponents: []
})
export class JhispterDomainModelModule {}
