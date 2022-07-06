import React from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppSelector } from 'app/config/store';

export const DeckList = (props: RouteComponentProps<{ url: string }>) => {
  const deckList = useAppSelector(state => state.deck.entities);
  const loading = useAppSelector(state => state.deck.loading);

  const { match } = props;

  return (
    <div>
      <h2 id="deck-heading" data-cy="DeckHeading">
        Decks
        <div className="d-flex justify-content-end">
          {/*<Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>*/}
          {/*  <FontAwesomeIcon icon="sync" spin={loading}/> Refresh List*/}
          {/*</Button>*/}
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Deck
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {deckList && deckList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Visibility</th>
                <th>Description</th>
                <th>Image Url</th>
                <th>Last Updated</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {deckList.map((deck, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}${deck.id}`} color="link" size="sm">
                      {deck.id}
                    </Button>
                  </td>
                  <td>{deck.title}</td>
                  <td>{deck.visibility ? 'true' : 'false'}</td>
                  <td>{deck.description}</td>
                  <td>{deck.imageUrl}</td>
                  <td>{deck.lastUpdated ? <TextFormat type="date" value={deck.lastUpdated} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${deck.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${deck.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${deck.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Decks found</div>
        )}
      </div>
    </div>
  );
};
