import dayjs from 'dayjs';
import { IPortal } from 'app/shared/model/portal.model';

export interface IDeck {
  id?: number;
  title?: string;
  visibility?: boolean;
  description?: string | null;
  imageUrl?: string | null;
  lastUpdated?: string | null;
  portal?: IPortal | null;
}

export const defaultValue: Readonly<IDeck> = {
  visibility: false,
};
