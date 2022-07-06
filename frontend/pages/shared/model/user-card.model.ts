import { IAppUser } from 'app/shared/model/app-user.model';
import { ICard } from 'app/shared/model/card.model';

export interface IUserCard {
  id?: number;
  appUser?: IAppUser | null;
  card?: ICard | null;
}

export const defaultValue: Readonly<IUserCard> = {};
