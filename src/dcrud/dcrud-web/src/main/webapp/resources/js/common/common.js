(function ($) {
    var getParam = function (url) {
        var reg_url = /^[^\?]+\?([\w\W]+)$/,
            reg_para = /([^&=]+)=([\w\W]*?)(&|$|#)/g,
            arr_url = reg_url.exec(url),
            ret = {};
        if (arr_url && arr_url[1]) {
            var str_para = arr_url[1], result;
            while ((result = reg_para.exec(str_para)) != null) {
                ret[result[1]] = result[2];
            }
        }
        return ret;
    };
    $(document).on("click", "a.lang", function () {
        var href = window.location.href;
        var index = href.indexOf("?");
        var lang = $(this).attr('lang');
        if (index < 0) {
            window.location.href = href + "?lang=" + lang;
        } else {
            var param = getParam(href);
            param.lang = lang;
            href = href.substring(0, index);
            var i = 0;
            for (var f in param) {
                if (i == 0) {
                    href += "?"
                } else {
                    href += "&";
                }
                href = href + f + "=" + param[f];
                i++;
            }
            window.location.href = href;
        }

    });
})($);

$(function () {
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;
        form.render();
    });

    //***  validator  ***//
    validator.register();
});