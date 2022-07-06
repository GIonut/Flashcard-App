import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Portal from './portal';
import PortalDetail from './portal-detail';
import PortalUpdate from './portal-update';
import PortalDeleteDialog from './portal-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PortalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PortalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PortalDetail} />
      <ErrorBoundaryRoute path={match.url} component={Portal} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PortalDeleteDialog} />
  </>
);

export default Routes;
