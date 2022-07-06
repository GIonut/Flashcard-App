import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IDeck } from 'app/shared/model/deck.model';
import { getEntities as getDecks } from 'app/entities/deck/deck.reducer';
import { getEntity, updateEntity, createEntity, reset } from './card.reducer';
import { ICard } from 'app/shared/model/card.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { CardType } from 'app/shared/model/enumerations/card-type.model';

export const CardUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const decks = useAppSelector(state => state.deck.entities);
  const cardEntity = useAppSelector(state => state.card.entity);
  const loading = useAppSelector(state => state.card.loading);
  const updating = useAppSelector(state => state.card.updating);
  const updateSuccess = useAppSelector(state => state.card.updateSuccess);
  const cardTypeValues = Object.keys(CardType);
  const handleClose = () => {
    props.history.push('/card');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getDecks({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...cardEntity,
      ...values,
      deck: decks.find(it => it.id.toString() === values.deck.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          type: 'FLASHHCARDS',
          ...cardEntity,
          deck: cardEntity?.deck?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bachelorThesisApp.card.home.createOrEditLabel" data-cy="CardCreateUpdateHeading">
            Create or edit a Card
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="card-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Title"
                id="card-title"
                name="title"
                data-cy="title"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 32, message: 'This field cannot be longer than 32 characters.' },
                }}
              />
              <ValidatedField label="Type" id="card-type" name="type" data-cy="type" type="select">
                {cardTypeValues.map(cardType => (
                  <option value={cardType} key={cardType}>
                    {cardType}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Thumbnail Url"
                id="card-thumbnailUrl"
                name="thumbnailUrl"
                data-cy="thumbnailUrl"
                type="text"
                validate={{
                  maxLength: { value: 256, message: 'This field cannot be longer than 256 characters.' },
                }}
              />
              <ValidatedField id="card-deck" name="deck" data-cy="deck" label="Deck" type="select">
                <option value="" key="0" />
                {decks
                  ? decks.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/card" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default CardUpdate;
