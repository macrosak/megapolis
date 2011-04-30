/*
 * Copyright 2010-2011 by MegapolisTeam
 *
 * This file is part of Megapolis.
 *
 * Megapolis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Megapolis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Megapolis.  If not, see <http://www.gnu.org/licenses/>.
 */

// script.aculo.us scriptaculous.js v1.8.3, Thu Oct 08 11:23:33 +0200 2009

var Scriptaculous = {
  Version: '1.8.3',
  require: function(libraryName) {
    try{
      // inserting via DOM fails in Safari 2.0, so brute force approach
      document.write('<script type="text/javascript" src="'+libraryName+'"><\/script>');
    } catch(e) {
      // for xhtml+xml served content, fall back to DOM methods
      var script = document.createElement('script');
      script.type = 'text/javascript';
      script.src = libraryName;
      document.getElementsByTagName('head')[0].appendChild(script);
    }
  },
  REQUIRED_PROTOTYPE: '1.6.0.3',
  load: function() {
    function convertVersionString(versionString) {
      var v = versionString.replace(/_.*|\./g, '');
      v = parseInt(v + '0'.times(4-v.length));
      return versionString.indexOf('_') > -1 ? v-1 : v;
    }

    if((typeof Prototype=='undefined') ||
       (typeof Element == 'undefined') ||
       (typeof Element.Methods=='undefined') ||
       (convertVersionString(Prototype.Version) <
        convertVersionString(Scriptaculous.REQUIRED_PROTOTYPE)))
       throw("script.aculo.us requires the Prototype JavaScript framework >= " +
        Scriptaculous.REQUIRED_PROTOTYPE);

    var js = /scriptaculous\.js(\?.*)?$/;
    $$('head script[src]').findAll(function(s) {
      return s.src.match(js);
    }).each(function(s) {
      var path = s.src.replace(js, ''),
      includes = s.src.match(/\?.*load=([a-z,]*)/);
      (includes ? includes[1] : 'builder,effects,dragdrop,controls,slider,sound').split(',').each(
       function(include) { Scriptaculous.require(path+include+'.js') });
    });
  }
};

Scriptaculous.load();