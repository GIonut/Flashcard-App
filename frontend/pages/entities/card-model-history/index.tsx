import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CardModelHistory from './card-model-history';
import CardModelHistoryDetail from './card-model-history-detail';
import CardModelHistoryUpdate from './card-model-history-update';
import CardModelHistoryDeleteDialog from './card-model-history-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CardModelHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CardModelHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CardModelHistoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={CardModelHistory} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CardModelHistoryDeleteDialog} />
  </>
);

export default Routes;
