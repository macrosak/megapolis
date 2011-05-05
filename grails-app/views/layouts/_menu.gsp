%{--
  - Copyright 2010-2011 by MegapolisTeam
  -
  - This file is part of Megapolis.
  -
  - Megapolis is free software: you can redistribute it and/or modify
  - it under the terms of the GNU Lesser General Public License as published by
  - the Free Software Foundation, either version 3 of the License, or
  - (at your option) any later version.
  -
  - Megapolis is distributed in the hope that it will be useful,
  - but WITHOUT ANY WARRANTY; without even the implied warranty of
  - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  - GNU Lesser General Public License for more details.
  -
  - You should have received a copy of the GNU Lesser General Public License
  - along with Megapolis.  If not, see <http://www.gnu.org/licenses/>.
  --}%
<div id="menuDiv">
  <ul class="menu">
    <li><g:link controller="city" action="show">City</g:link></li>
    <li><span onclick="profileDialog.show()">Profile</span></li>
    <li><g:link controller="player" action="myBuildings">My Buildings</g:link></li>
    %{--<li><g:link action="economy" >Economy</g:link></li>--}%
    <li><g:link controller="city" action="buyBuild">Build</g:link></li>
    <li><g:link controller="city" action="roads">Roads</g:link></li>
  </ul>

  <div class="clear" style="margin-bottom: 10px;">&nbsp;</div>
</div>