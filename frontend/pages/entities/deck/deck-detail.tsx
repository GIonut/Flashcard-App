import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './deck.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DeckDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const deckEntity = useAppSelector(state => state.deck.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="deckDetailsHeading">Deck</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{deckEntity.id}</dd>
          <dt>
            <span id="title">Title</span>
          </dt>
          <dd>{deckEntity.title}</dd>
          <dt>
            <span id="visibility">Visibility</span>
          </dt>
          <dd>{deckEntity.visibility ? 'true' : 'false'}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{deckEntity.description}</dd>
          <dt>
            <span id="imageUrl">Image Url</span>
          </dt>
          <dd>{deckEntity.imageUrl}</dd>
          <dt>
            <span id="lastUpdated">Last Updated</span>
          </dt>
          <dd>
            {deckEntity.lastUpdated ? <TextFormat value={deckEntity.lastUpdated} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>Portal</dt>
          <dd>{deckEntity.portal ? deckEntity.portal.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/deck" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/deck/${deckEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default DeckDetail;
