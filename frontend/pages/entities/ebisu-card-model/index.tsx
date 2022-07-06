import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EbisuCardModel from './ebisu-card-model';
import EbisuCardModelDetail from './ebisu-card-model-detail';
import EbisuCardModelUpdate from './ebisu-card-model-update';
import EbisuCardModelDeleteDialog from './ebisu-card-model-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EbisuCardModelUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EbisuCardModelUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EbisuCardModelDetail} />
      <ErrorBoundaryRoute path={match.url} component={EbisuCardModel} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EbisuCardModelDeleteDialog} />
  </>
);

export default Routes;
