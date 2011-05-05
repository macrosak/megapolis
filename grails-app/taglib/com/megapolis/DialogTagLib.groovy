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

package com.megapolis

class DialogTagLib {

    def importDialogs = {
        def defaultCss = resource(dir:'css/window',file:'default.css')
        def alphaCss = resource(dir:'css/window',file:'alphacube.css')

        out << javascript(src:"prototype/window.js") << "\n"
		out << javascript(src:"prototype/window_effects.js") << "\n"
		out << javascript(src:"prototype/window_ext.js") << "\n"
		out << javascript(src:"prototype/tooltip.js") << "\n"
		out << javascript(src:"prototype/effects.js") << "\n"
        out << "<link href=\"$defaultCss\" rel=\"stylesheet\" type=\"text/css\"/>" << "\n"
        out << "<link href=\"$alphaCss\" rel=\"stylesheet\" type=\"text/css\"/>" << "\n"

    }

    def window = { attrs, body ->
        out << '<script type="text/javascript">\n'
        def name = attrs.name ?: "window_${new Random().nextLong()}"
        out << "var $name = new Window({className: 'alphacube'"
        if(attrs.width)
            out << ",  width: ${attrs.width}"
        else
            out << ",  width: 400"
        if(attrs.height)
            out << ", height: ${attrs.height}"
        if(attrs.zIndex)
            out << ", zIndex: ${attrs.zIndex}"
        if(attrs.resizable)
            out << ", resizable:  ${attrs.resizable}"
        if(attrs.title)
            out << ", title: '${attrs.title}'"
        if(attrs.showEffect)
            out << ", showEffect: Effect.${attrs.showEffect}"
        if(attrs.hideEffect)
            out << ", hideEffect: Effect.${attrs.hideEffect}"
        if(attrs.draggable)
            out << ", draggable: ${attrs.draggable}"
        if(attrs.wiredDrag)
            out << ", wiredDrag: ${attrs.wiredDrag}"
        out << "})\n"

        out << '</script>'

        out << "<div id='${name}_content_div' style='display: none'>${body()}</div>"


        out << '<script type="text/javascript">\n'
        out << "${name}.setContent('${name}_content_div', false, false)\n"
        out << '</script>'
    }

    def dialog = { attrs, body ->
        def name = attrs.name ?: "dialog_${new Random().nextLong()}"
        out << "<div id='${name}_content_div' style='display: none'>${body()}</div>"

        out << '<script type="text/javascript">\n'
        out << "var $name = new Object();\n"
        out << "${name}.hide = function() { Dialog.closeInfo() };\n"
        out << "${name}.show = function() {\n"
        out << "${name}.window = Dialog.info(\$('${name}_content_div').innerHTML, {className: 'alphacube', closable:true"
        if(attrs.top)
            out << ",  top: ${attrs.top}"
        if(attrs.left)
            out << ", left: ${attrs.left}"
        if(attrs.width)
            out << ",  width: ${attrs.width}"
        else
            out << ",  width: 400"   
        if(attrs.height)
            out << ", height: ${attrs.height}"
        if(attrs.zIndex)
            out << ", zIndex: ${attrs.zIndex}"
        if(attrs.resizable)
            out << ", resizable:  ${attrs.resizable}"
        if(attrs.title)
            out << ", title: '${attrs.title}'"
        if(attrs.showEffect)
            out << ", showEffect: Effect.${attrs.showEffect}"
        if(attrs.hideEffect)
            out << ", hideEffect: Effect.${attrs.hideEffect}"
        if(attrs.draggable)
            out << ", draggable: ${attrs.draggable}"
        if(attrs.wiredDrag)
            out << ", wiredDrag: ${attrs.wiredDrag}"
        out << "});\n}\n"
        out << '</script>'
    }
}
