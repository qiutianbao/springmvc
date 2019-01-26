/**
 * CoolInput Plugin
 * 
 * @version 1.5 (10/09/2009)
 * @requires jQuery v1.2.6+
 * @author Alex Weber <alexweber.com.br>
 * @author Evan Winslow <ewinslow@cs.stanford.edu> (v1.5)
 * @copyright Copyright (c) 2008-2009, Alex Weber
 * @see http://remysharp.com/2007/01/25/jquery-tutorial-text-box-hints/
 * 
 * Distributed under the terms of the GNU General Public License
 * http://www.gnu.org/licenses/gpl-3.0.html
 * 
 * 
 * added:
 * 
 * "activeClass" support.
 */
;
( function($) {
	$.fn.coolinput = function(b) {
		var c = {
			hint : null,
			source : "title",
			blurClass : "blur",
			activeClass : "active",
			iconClass : false,
			clearOnSubmit : true,
			clearOnFocus : true,
			persistent : true
		};
		if (b && typeof b == "object")
			$.extend(c, b);
		else
			c.hint = b;
		return this.each( function() {
			var d = $(this);
			var e = c.hint || d.attr(c.source);
			var f = c.blurClass;
			var f1 = c.activeClass;
			function g() {
				if (d.val() == "")
					d.val(e).removeClass(f1).addClass(f);
			}
			function h() {
				if (d.val() == e && d.hasClass(f))
					d.val("").removeClass(f).addClass(f1);
			}
			if (e) {
				if (c.persistent)
					d.blur(g);
				if (c.clearOnFocus)
					d.focus(h);
				if (c.clearOnSubmit) {
					d.parents("form:first").submit(h);
				}

				if (c.iconClass)
					d.addClass(c.iconClass);
				g()
			}
		})
	}
})(jQuery);