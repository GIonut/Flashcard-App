import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './card-resource.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const CardResourceDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const cardResourceEntity = useAppSelector(state => state.cardResource.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="cardResourceDetailsHeading">CardResource</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{cardResourceEntity.id}</dd>
          <dt>
            <span id="content">Content</span>
          </dt>
          <dd>{cardResourceEntity.content}</dd>
          <dt>
            <span id="type">Type</span>
          </dt>
          <dd>{cardResourceEntity.type}</dd>
          <dt>Card</dt>
          <dd>{cardResourceEntity.card ? cardResourceEntity.card.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/card-resource" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/card-resource/${cardResourceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CardResourceDetail;
