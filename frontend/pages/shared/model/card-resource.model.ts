import { ICard } from 'app/shared/model/card.model';
import { ResourceType } from 'app/shared/model/enumerations/resource-type.model';

export interface ICardResource {
  id?: number;
  content?: string;
  type?: ResourceType;
  card?: ICard | null;
}

export const defaultValue: Readonly<ICardResource> = {};
