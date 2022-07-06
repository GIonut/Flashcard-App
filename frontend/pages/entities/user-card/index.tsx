import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserCard from './user-card';
import UserCardDetail from './user-card-detail';
import UserCardUpdate from './user-card-update';
import UserCardDeleteDialog from './user-card-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserCardUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserCardUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserCardDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserCard} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UserCardDeleteDialog} />
  </>
);

export default Routes;
