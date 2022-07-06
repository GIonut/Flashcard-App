import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './app-user.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const AppUserDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const appUserEntity = useAppSelector(state => state.appUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="appUserDetailsHeading">AppUser</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{appUserEntity.id}</dd>
          <dt>
            <span id="lastLogin">Last Login</span>
          </dt>
          <dd>
            {appUserEntity.lastLogin ? <TextFormat value={appUserEntity.lastLogin} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="invalidLogins">Invalid Logins</span>
          </dt>
          <dd>{appUserEntity.invalidLogins}</dd>
          <dt>
            <span id="lastInvalidLogin">Last Invalid Login</span>
          </dt>
          <dd>
            {appUserEntity.lastInvalidLogin ? (
              <TextFormat value={appUserEntity.lastInvalidLogin} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>App User</dt>
          <dd>{appUserEntity.appUser ? appUserEntity.appUser.id : ''}</dd>
          <dt>Owner</dt>
          <dd>{appUserEntity.owner ? appUserEntity.owner.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/app-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/app-user/${appUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default AppUserDetail;
