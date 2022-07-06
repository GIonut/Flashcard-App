import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './card-model-history.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const CardModelHistoryDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const cardModelHistoryEntity = useAppSelector(state => state.cardModelHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="cardModelHistoryDetailsHeading">CardModelHistory</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{cardModelHistoryEntity.id}</dd>
          <dt>
            <span id="timeStamp">Time Stamp</span>
          </dt>
          <dd>
            {cardModelHistoryEntity.timeStamp ? (
              <TextFormat value={cardModelHistoryEntity.timeStamp} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="type">Type</span>
          </dt>
          <dd>{cardModelHistoryEntity.type}</dd>
          <dt>
            <span id="recallInHours">Recall In Hours</span>
          </dt>
          <dd>{cardModelHistoryEntity.recallInHours}</dd>
          <dt>Card Model History</dt>
          <dd>{cardModelHistoryEntity.cardModelHistory ? cardModelHistoryEntity.cardModelHistory.id : ''}</dd>
          <dt>User Card</dt>
          <dd>{cardModelHistoryEntity.userCard ? cardModelHistoryEntity.userCard.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/card-model-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/card-model-history/${cardModelHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CardModelHistoryDetail;
