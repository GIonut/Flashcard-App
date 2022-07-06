import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './card-answer.reducer';
import { ICardAnswer } from 'app/shared/model/card-answer.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const CardAnswer = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const cardAnswerList = useAppSelector(state => state.cardAnswer.entities);
  const loading = useAppSelector(state => state.cardAnswer.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="card-answer-heading" data-cy="CardAnswerHeading">
        Card Answers
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Card Answer
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {cardAnswerList && cardAnswerList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Evaluation</th>
                <th>Content</th>
                <th>Type</th>
                <th>Card</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cardAnswerList.map((cardAnswer, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${cardAnswer.id}`} color="link" size="sm">
                      {cardAnswer.id}
                    </Button>
                  </td>
                  <td>{cardAnswer.evaluation}</td>
                  <td>{cardAnswer.content}</td>
                  <td>{cardAnswer.type}</td>
                  <td>{cardAnswer.card ? <Link to={`card/${cardAnswer.card.id}`}>{cardAnswer.card.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cardAnswer.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cardAnswer.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cardAnswer.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Card Answers found</div>
        )}
      </div>
    </div>
  );
};

export default CardAnswer;
