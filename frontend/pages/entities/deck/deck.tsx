import React, { useEffect } from 'react';
import { RouteComponentProps } from 'react-router-dom';

import { getEntities } from './deck.reducer';
import { useAppDispatch } from 'app/config/store';
import { DeckList } from 'app/entities/deck/deck-list';

export const Deck = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return <DeckList {...props} />;
};

export default Deck;
