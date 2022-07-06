import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IEbisuCardModel } from 'app/shared/model/ebisu-card-model.model';
import { getEntities as getEbisuCardModels } from 'app/entities/ebisu-card-model/ebisu-card-model.reducer';
import { IUserCard } from 'app/shared/model/user-card.model';
import { getEntities as getUserCards } from 'app/entities/user-card/user-card.reducer';
import { getEntity, updateEntity, createEntity, reset } from './card-model-history.reducer';
import { ICardModelHistory } from 'app/shared/model/card-model-history.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { CardModelType } from 'app/shared/model/enumerations/card-model-type.model';

export const CardModelHistoryUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const ebisuCardModels = useAppSelector(state => state.ebisuCardModel.entities);
  const userCards = useAppSelector(state => state.userCard.entities);
  const cardModelHistoryEntity = useAppSelector(state => state.cardModelHistory.entity);
  const loading = useAppSelector(state => state.cardModelHistory.loading);
  const updating = useAppSelector(state => state.cardModelHistory.updating);
  const updateSuccess = useAppSelector(state => state.cardModelHistory.updateSuccess);
  const cardModelTypeValues = Object.keys(CardModelType);
  const handleClose = () => {
    props.history.push('/card-model-history');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getEbisuCardModels({}));
    dispatch(getUserCards({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...cardModelHistoryEntity,
      ...values,
      cardModelHistory: ebisuCardModels.find(it => it.id.toString() === values.cardModelHistory.toString()),
      userCard: userCards.find(it => it.id.toString() === values.userCard.toString()),
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
          type: 'EBISU',
          ...cardModelHistoryEntity,
          cardModelHistory: cardModelHistoryEntity?.cardModelHistory?.id,
          userCard: cardModelHistoryEntity?.userCard?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bachelorThesisApp.cardModelHistory.home.createOrEditLabel" data-cy="CardModelHistoryCreateUpdateHeading">
            Create or edit a CardModelHistory
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField name="id" required readOnly id="card-model-history-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Time Stamp"
                id="card-model-history-timeStamp"
                name="timeStamp"
                data-cy="timeStamp"
                type="date"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Type" id="card-model-history-type" name="type" data-cy="type" type="select">
                {cardModelTypeValues.map(cardModelType => (
                  <option value={cardModelType} key={cardModelType}>
                    {cardModelType}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Recall In Hours"
                id="card-model-history-recallInHours"
                name="recallInHours"
                data-cy="recallInHours"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  min: { value: 0, message: 'This field should be at least 0.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                id="card-model-history-cardModelHistory"
                name="cardModelHistory"
                data-cy="cardModelHistory"
                label="Card Model History"
                type="select"
              >
                <option value="" key="0" />
                {ebisuCardModels
                  ? ebisuCardModels.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="card-model-history-userCard" name="userCard" data-cy="userCard" label="User Card" type="select">
                <option value="" key="0" />
                {userCards
                  ? userCards.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/card-model-history" replace color="info">
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

export default CardModelHistoryUpdate;
