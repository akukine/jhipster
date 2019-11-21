import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'domain-model',
        loadChildren: () => import('./domain-model/domain-model.module').then(m => m.JhispterDomainModelModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhispterEntityModule {}
