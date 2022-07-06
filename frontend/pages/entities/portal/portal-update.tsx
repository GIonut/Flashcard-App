import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IAppUser } from 'app/shared/model/app-user.model';
import { getEntities as getAppUsers } from 'app/entities/app-user/app-user.reducer';
import { getEntity, updateEntity, createEntity, reset } from './portal.reducer';
import { IPortal } from 'app/shared/model/portal.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PortalUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const appUsers = useAppSelector(state => state.appUser.entities);
  const portalEntity = useAppSelector(state => state.portal.entity);
  const loading = useAppSelector(state => state.portal.loading);
  const updating = useAppSelector(state => state.portal.updating);
  const updateSuccess = useAppSelector(state => state.portal.updateSuccess);
  const handleClose = () => {
    props.history.push('/portal');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getAppUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...portalEntity,
      ...values,
      followers: mapIdList(values.followers),
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
          ...portalEntity,
          followers: portalEntity?.followers?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bachelorThesisApp.portal.home.createOrEditLabel" data-cy="PortalCreateUpdateHeading">
            Create or edit a Portal
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="portal-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Portal Name"
                id="portal-portalName"
                name="portalName"
                data-cy="portalName"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  minLength: { value: 5, message: 'This field is required to be at least 5 characters.' },
                  maxLength: { value: 32, message: 'This field cannot be longer than 32 characters.' },
                }}
              />
              <ValidatedField label="Visibility" id="portal-visibility" name="visibility" data-cy="visibility" check type="checkbox" />
              <ValidatedField label="Description" id="portal-description" name="description" data-cy="description" type="textarea" />
              <ValidatedField
                label="Image Url"
                id="portal-imageUrl"
                name="imageUrl"
                data-cy="imageUrl"
                type="text"
                validate={{
                  maxLength: { value: 256, message: 'This field cannot be longer than 256 characters.' },
                }}
              />
              <ValidatedField label="Last Updated" id="portal-lastUpdated" name="lastUpdated" data-cy="lastUpdated" type="date" />
              <ValidatedField
                label="Times Accessed"
                id="portal-timesAccessed"
                name="timesAccessed"
                data-cy="timesAccessed"
                type="text"
                validate={{
                  min: { value: 0, message: 'This field should be at least 0.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField label="Follower" id="portal-follower" data-cy="follower" type="select" multiple name="followers">
                <option value="" key="0" />
                {appUsers
                  ? appUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/portal" replace color="info">
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

export default PortalUpdate;
