import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';
import { IPortal } from 'app/shared/model/portal.model';

export interface IAppUser {
  id?: number;
  lastLogin?: string | null;
  invalidLogins?: number | null;
  lastInvalidLogin?: string | null;
  appUser?: IUser | null;
  owner?: IPortal | null;
  portals?: IPortal[] | null;
}

export const defaultValue: Readonly<IAppUser> = {};
