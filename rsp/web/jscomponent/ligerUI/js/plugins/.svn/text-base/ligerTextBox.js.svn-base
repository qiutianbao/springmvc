/**
* jQuery ligerUI 1.0.0
* 
* Author leoxie [ gd_star@163.com ] 
* 
*/

(function ($)
{
    ///	<param name="$" type="jQuery"></param>
    $.fn.ligerTextBox = function (p)
    {
        return this.each(function ()
        {
            p = p || {};
            p = $.extend({
                onBeforeInput: false,
                onInputed: false,
                onChangeValue: false,
                selectedText: false,
                selectedValue: false,
                width: false,
                nullText: null     //文本框为空时显示的值
            }, p);
            if (this.usedTextBox) return;
            var g = {};
            g.inputText = $(this);
            //外层
            g.wrapper = g.inputText.wrap('<div class="l-text"></div>').parent();
            if (!g.inputText.hasClass("l-text-field"))
                g.inputText.addClass("l-text-field");
            if (!p.width)
            {
                if (g.inputText.attr("lwidth")) p.width = parseInt(g.inputText.attr("lwidth"));
            }
            if (p.width)
            {
                g.wrapper.css({ width: p.width });
                g.inputText.css({ width: p.width - 2 });
            }
            if (p.height)
            {
                g.wrapper.height(p.height);
                g.inputText.height(p.height - 2);
            }
            g.nullText = p.nullText;
            if (g.nullText == null)
            {
                if (g.inputText.attr("nulltext")) g.nullText = g.inputText.attr("nulltext");
            }
            if (this.value == "")
            {
                if (g.nullText != null)
                    this.value = g.nullText;
                $(this).addClass("l-text-field-null");
            }

            g.inputText.blur(function ()
            {
                if (this.value == "")
                {
                    if (g.nullText != null)
                        this.value = g.nullText;
                    $(this).addClass("l-text-field-null");
                }
                else
                {
                    $(this).removeClass("l-text-field-null");
                }
            }).focus(function ()
            {
                if ($(this).hasClass("l-text-field-null"))
                {
                    this.value = "";
                    $(this).removeClass("l-text-field-null");
                }
            }).change(function ()
            {
                if (p.onChangeValue)
                {
                    p.onChangeValue(this.value);
                }
            });

            this.usedTextBox = true;
        });
    };

})(jQuery);