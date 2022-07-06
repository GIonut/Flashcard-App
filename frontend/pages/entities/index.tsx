import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AppUser from './app-user';
import Portal from './portal';
import Deck from './deck';
import Card from './card';
import CardResource from './card-resource';
import CardAnswer from './card-answer';
import UserCard from './user-card';
import CardModelHistory from './card-model-history';
import EbisuCardModel from './ebisu-card-model';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}app-user`} component={AppUser} />
      <ErrorBoundaryRoute path={`${match.url}portal`} component={Portal} />
      <ErrorBoundaryRoute path={`${match.url}deck`} component={Deck} />
      <ErrorBoundaryRoute path={`${match.url}card`} component={Card} />
      <ErrorBoundaryRoute path={`${match.url}card-resource`} component={CardResource} />
      <ErrorBoundaryRoute path={`${match.url}card-answer`} component={CardAnswer} />
      <ErrorBoundaryRoute path={`${match.url}user-card`} component={UserCard} />
      <ErrorBoundaryRoute path={`${match.url}card-model-history`} component={CardModelHistory} />
      <ErrorBoundaryRoute path={`${match.url}ebisu-card-model`} component={EbisuCardModel} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
