import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CardResource from './card-resource';
import CardResourceDetail from './card-resource-detail';
import CardResourceUpdate from './card-resource-update';
import CardResourceDeleteDialog from './card-resource-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CardResourceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CardResourceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CardResourceDetail} />
      <ErrorBoundaryRoute path={match.url} component={CardResource} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CardResourceDeleteDialog} />
  </>
);

export default Routes;
