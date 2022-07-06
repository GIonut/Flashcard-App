import dayjs from 'dayjs';
import { IEbisuCardModel } from 'app/shared/model/ebisu-card-model.model';
import { IUserCard } from 'app/shared/model/user-card.model';
import { CardModelType } from 'app/shared/model/enumerations/card-model-type.model';

export interface ICardModelHistory {
  id?: number;
  timeStamp?: string;
  type?: CardModelType;
  recallInHours?: number;
  cardModelHistory?: IEbisuCardModel | null;
  userCard?: IUserCard | null;
}

export const defaultValue: Readonly<ICardModelHistory> = {};
