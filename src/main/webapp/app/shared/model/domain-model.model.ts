export interface IDomainModel {
  id?: number;
  name?: string;
  type?: string;
  dateCreate?: number;
}

export class DomainModel implements IDomainModel {
  constructor(public id?: number, public name?: string, public type?: string, public dateCreate?: number) {}
}
