import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication from './authentication';
import applicationProfile from './application-profile';

import administration from 'app/modules/administration/administration.reducer';
import userManagement from 'app/modules/administration/user-management/user-management.reducer';
import register from 'app/modules/account/register/register.reducer';
import activate from 'app/modules/account/activate/activate.reducer';
import password from 'app/modules/account/password/password.reducer';
import settings from 'app/modules/account/settings/settings.reducer';
import passwordReset from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import appUser from 'app/entities/app-user/app-user.reducer';
// prettier-ignore
import portal from 'app/entities/portal/portal.reducer';
// prettier-ignore
import deck from 'app/entities/deck/deck.reducer';
// prettier-ignore
import card from 'app/entities/card/card.reducer';
// prettier-ignore
import cardResource from 'app/entities/card-resource/card-resource.reducer';
// prettier-ignore
import cardAnswer from 'app/entities/card-answer/card-answer.reducer';
// prettier-ignore
import userCard from 'app/entities/user-card/user-card.reducer';
// prettier-ignore
import cardModelHistory from 'app/entities/card-model-history/card-model-history.reducer';
// prettier-ignore
import ebisuCardModel from 'app/entities/ebisu-card-model/ebisu-card-model.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer = {
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  appUser,
  portal,
  deck,
  card,
  cardResource,
  cardAnswer,
  userCard,
  cardModelHistory,
  ebisuCardModel,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
};

export default rootReducer;
