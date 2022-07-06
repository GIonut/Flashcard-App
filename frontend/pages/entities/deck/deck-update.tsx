import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IPortal } from 'app/shared/model/portal.model';
import { getEntities as getPortals } from 'app/entities/portal/portal.reducer';
import { getEntity, updateEntity, createEntity, reset } from './deck.reducer';
import { IDeck } from 'app/shared/model/deck.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DeckUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const portals = useAppSelector(state => state.portal.entities);
  const deckEntity = useAppSelector(state => state.deck.entity);
  const loading = useAppSelector(state => state.deck.loading);
  const updating = useAppSelector(state => state.deck.updating);
  const updateSuccess = useAppSelector(state => state.deck.updateSuccess);
  const handleClose = () => {
    props.history.push('/deck');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getPortals({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...deckEntity,
      ...values,
      portal: portals.find(it => it.id.toString() === values.portal.toString()),
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
          ...deckEntity,
          portal: deckEntity?.portal?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bachelorThesisApp.deck.home.createOrEditLabel" data-cy="DeckCreateUpdateHeading">
            Create or edit a Deck
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="deck-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Title"
                id="deck-title"
                name="title"
                data-cy="title"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  minLength: { value: 5, message: 'This field is required to be at least 5 characters.' },
                  maxLength: { value: 64, message: 'This field cannot be longer than 64 characters.' },
                }}
              />
              <ValidatedField label="Visibility" id="deck-visibility" name="visibility" data-cy="visibility" check type="checkbox" />
              <ValidatedField label="Description" id="deck-description" name="description" data-cy="description" type="textarea" />
              <ValidatedField
                label="Image Url"
                id="deck-imageUrl"
                name="imageUrl"
                data-cy="imageUrl"
                type="text"
                validate={{
                  maxLength: { value: 256, message: 'This field cannot be longer than 256 characters.' },
                }}
              />
              <ValidatedField label="Last Updated" id="deck-lastUpdated" name="lastUpdated" data-cy="lastUpdated" type="date" />
              <ValidatedField id="deck-portal" name="portal" data-cy="portal" label="Portal" type="select">
                <option value="" key="0" />
                {portals
                  ? portals.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/deck" replace color="info">
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

export default DeckUpdate;
