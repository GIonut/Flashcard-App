import { IDeck } from 'app/shared/model/deck.model';
import { CardType } from 'app/shared/model/enumerations/card-type.model';

export interface ICard {
  id?: number;
  title?: string;
  type?: CardType;
  thumbnailUrl?: string | null;
  deck?: IDeck | null;
}

export const defaultValue: Readonly<ICard> = {};
