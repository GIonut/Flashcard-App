import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CardAnswer from './card-answer';
import CardAnswerDetail from './card-answer-detail';
import CardAnswerUpdate from './card-answer-update';
import CardAnswerDeleteDialog from './card-answer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CardAnswerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CardAnswerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CardAnswerDetail} />
      <ErrorBoundaryRoute path={match.url} component={CardAnswer} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CardAnswerDeleteDialog} />
  </>
);

export default Routes;
