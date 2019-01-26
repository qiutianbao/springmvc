/**
* jQuery ligerUI 1.0.0
* 
* Author leoxie [ gd_star@163.com ] 
* 
*/

(function ($)
{
    ///	<param name="$" type="jQuery"></param>
    $.fn.ligerFrom = function (p)
    {
        p = p || {};
        return this.each(function ()
        {
            $(":text[ltype=text]", this).ligerTextBox();

            $("select", this).ligerComboBox();

            $(":text[ltype=int]", this).ligerSpinner({ type: 'int' });

            $(":text[ltype=date]", this).ligerDateEditor();

            $("input:radio", this).ligerRadio();

            $('input:checkbox', this).ligerCheckBox();
        });
    };

})(jQuery);