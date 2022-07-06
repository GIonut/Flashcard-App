import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ICard } from 'app/shared/model/card.model';
import { getEntities as getCards } from 'app/entities/card/card.reducer';
import { getEntity, updateEntity, createEntity, reset } from './card-answer.reducer';
import { ICardAnswer } from 'app/shared/model/card-answer.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { ResourceType } from 'app/shared/model/enumerations/resource-type.model';

export const CardAnswerUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const cards = useAppSelector(state => state.card.entities);
  const cardAnswerEntity = useAppSelector(state => state.cardAnswer.entity);
  const loading = useAppSelector(state => state.cardAnswer.loading);
  const updating = useAppSelector(state => state.cardAnswer.updating);
  const updateSuccess = useAppSelector(state => state.cardAnswer.updateSuccess);
  const resourceTypeValues = Object.keys(ResourceType);
  const handleClose = () => {
    props.history.push('/card-answer');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getCards({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...cardAnswerEntity,
      ...values,
      card: cards.find(it => it.id.toString() === values.card.toString()),
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
          type: 'AUDIO',
          ...cardAnswerEntity,
          card: cardAnswerEntity?.card?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bachelorThesisApp.cardAnswer.home.createOrEditLabel" data-cy="CardAnswerCreateUpdateHeading">
            Create or edit a CardAnswer
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="card-answer-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Evaluation"
                id="card-answer-evaluation"
                name="evaluation"
                data-cy="evaluation"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  min: { value: 0, message: 'This field should be at least 0.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Content"
                id="card-answer-content"
                name="content"
                data-cy="content"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 1024, message: 'This field cannot be longer than 1024 characters.' },
                }}
              />
              <ValidatedField label="Type" id="card-answer-type" name="type" data-cy="type" type="select">
                {resourceTypeValues.map(resourceType => (
                  <option value={resourceType} key={resourceType}>
                    {resourceType}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField id="card-answer-card" name="card" data-cy="card" label="Card" type="select">
                <option value="" key="0" />
                {cards
                  ? cards.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/card-answer" replace color="info">
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

export default CardAnswerUpdate;
