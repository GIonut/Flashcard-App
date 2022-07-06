import dayjs from 'dayjs';
import { IAppUser } from 'app/shared/model/app-user.model';

export interface IPortal {
  id?: number;
  portalName?: string;
  visibility?: boolean;
  description?: string | null;
  imageUrl?: string | null;
  lastUpdated?: string | null;
  timesAccessed?: number | null;
  followers?: IAppUser[] | null;
}

export const defaultValue: Readonly<IPortal> = {
  visibility: false,
};
