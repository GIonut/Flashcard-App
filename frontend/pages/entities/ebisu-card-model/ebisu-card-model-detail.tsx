import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './ebisu-card-model.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const EbisuCardModelDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const ebisuCardModelEntity = useAppSelector(state => state.ebisuCardModel.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="ebisuCardModelDetailsHeading">EbisuCardModel</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{ebisuCardModelEntity.id}</dd>
          <dt>
            <span id="alpha">Alpha</span>
          </dt>
          <dd>{ebisuCardModelEntity.alpha}</dd>
          <dt>
            <span id="beta">Beta</span>
          </dt>
          <dd>{ebisuCardModelEntity.beta}</dd>
          <dt>
            <span id="halflife">Halflife</span>
          </dt>
          <dd>{ebisuCardModelEntity.halflife}</dd>
        </dl>
        <Button tag={Link} to="/ebisu-card-model" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ebisu-card-model/${ebisuCardModelEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default EbisuCardModelDetail;
