import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './portal.reducer';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntitiesWithPortal } from 'app/entities/deck/deck.reducer';
import { DeckList } from 'app/entities/deck/deck-list';

export const PortalDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
    dispatch(getEntitiesWithPortal(props.match.params.id));
  }, []);

  const portalEntity = useAppSelector(state => state.portal.entity);

  const decksMatch = { ...props.match, url: `/deck`, params: null };

  return (
    <Row>
      <Col md="8">
        <h2 data-cy="portalDetailsHeading">Portal</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{portalEntity.id}</dd>
          <dt>
            <span id="portalName">Portal Name</span>
          </dt>
          <dd>{portalEntity.portalName}</dd>
          <dt>
            <span id="visibility">Visibility</span>
          </dt>
          <dd>{portalEntity.visibility ? 'true' : 'false'}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{portalEntity.description}</dd>
          <dt>
            <span id="imageUrl">Image Url</span>
          </dt>
          <dd>{portalEntity.imageUrl}</dd>
          <dt>
            <span id="lastUpdated">Last Updated</span>
          </dt>
          <dd>
            {portalEntity.lastUpdated ? <TextFormat value={portalEntity.lastUpdated} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="timesAccessed">Times Accessed</span>
          </dt>
          <dd>{portalEntity.timesAccessed}</dd>
          <dt>Follower</dt>
          <dd>
            {portalEntity.followers
              ? portalEntity.followers.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {portalEntity.followers && i === portalEntity.followers.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/portal" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/portal/${portalEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
      <DeckList {...{ ...props, match: decksMatch }} />
    </Row>
  );
};

export default PortalDetail;
