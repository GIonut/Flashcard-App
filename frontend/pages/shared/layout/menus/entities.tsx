import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';

import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu" data-cy="entity" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/app-user">
      App User
    </MenuItem>
    <MenuItem icon="asterisk" to="/portal">
      Portal
    </MenuItem>
    <MenuItem icon="asterisk" to="/deck">
      Deck
    </MenuItem>
    <MenuItem icon="asterisk" to="/card">
      Card
    </MenuItem>
    <MenuItem icon="asterisk" to="/card-resource">
      Card Resource
    </MenuItem>
    <MenuItem icon="asterisk" to="/card-answer">
      Card Answer
    </MenuItem>
    <MenuItem icon="asterisk" to="/user-card">
      User Card
    </MenuItem>
    <MenuItem icon="asterisk" to="/card-model-history">
      Card Model History
    </MenuItem>
    <MenuItem icon="asterisk" to="/ebisu-card-model">
      Ebisu Card Model
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
