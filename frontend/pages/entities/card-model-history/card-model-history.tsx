import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './card-model-history.reducer';
import { ICardModelHistory } from 'app/shared/model/card-model-history.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const CardModelHistory = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const cardModelHistoryList = useAppSelector(state => state.cardModelHistory.entities);
  const loading = useAppSelector(state => state.cardModelHistory.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="card-model-history-heading" data-cy="CardModelHistoryHeading">
        Card Model Histories
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Card Model History
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {cardModelHistoryList && cardModelHistoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Time Stamp</th>
                <th>Type</th>
                <th>Recall In Hours</th>
                <th>Card Model History</th>
                <th>User Card</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cardModelHistoryList.map((cardModelHistory, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${cardModelHistory.id}`} color="link" size="sm">
                      {cardModelHistory.id}
                    </Button>
                  </td>
                  <td>
                    {cardModelHistory.timeStamp ? (
                      <TextFormat type="date" value={cardModelHistory.timeStamp} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{cardModelHistory.type}</td>
                  <td>{cardModelHistory.recallInHours}</td>
                  <td>
                    {cardModelHistory.cardModelHistory ? (
                      <Link to={`ebisu-card-model/${cardModelHistory.cardModelHistory.id}`}>{cardModelHistory.cardModelHistory.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {cardModelHistory.userCard ? (
                      <Link to={`user-card/${cardModelHistory.userCard.id}`}>{cardModelHistory.userCard.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cardModelHistory.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cardModelHistory.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cardModelHistory.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Card Model Histories found</div>
        )}
      </div>
    </div>
  );
};

export default CardModelHistory;
