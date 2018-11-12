/**
 *
 */
(function () {
    String.prototype.format = function (args) {
        if (arguments.length > 0) {
            var result = this;
            if (arguments.length == 1 && typeof (args) == "object") {
                for (var key in args) {
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            } else {
                for (var i = 0; i < arguments.length; i++) {
                    if (arguments[i] == undefined) {
                        return "";
                    } else {
                        var reg = new RegExp("({[" + i + "]})", "g");
                        result = result.replace(reg, arguments[i]);
                    }
                }
            }
            return result;
        } else {
            return this;
        }
    }
    window.ctx = (function () {
        var contextPath = "";//window.location.pathname.split("/")[1];
        var basePath = window.location.protocol + "//" + window.location.host/*+":"+local.port*/ + "/" + contextPath;
        return basePath;
    })();
    window.escapeHTML = function (str) {
        return $("div").text(str).html();
    };
    window.unescapeHTML = function (str) {
        return $("div").html(str).text();
    };
    window.submitParams = function (url, method, data) {
        var form = '<form id="{id}" action="{url}" method="{method}">{inputs}</form>';
        if (method == "get" || method == "GET" || method == "Get")
            for (var e in data) {
                data[e] = encodeURIComponent(data[e]);
            }
        else {
            for (var e in data) {
                data[e] = data[e].replace(/"/g, "&quot;");
            }
        }
        var inputsTemplate = "<input type=\"hidden\" name=\"{name}\" value=\"{value}\"/>";
        var inputs = "";
        if (!!data) for (e in data) {
            inputs += (inputsTemplate.format({name: e, value: data[e]}));
        }
        var id = encrypt(new Date());
        form = form.format({id: id, url: url, method: method, inputs: inputs});
        $(form).appendTo($("body"));
        document.getElementById(id).submit();
    };
    $.ajaxSetup({
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        dataType: 'JSON',
        complete: function (XMLHttpRequest, textStatus) {
            var sessionstatus = XMLHttpRequest.getResponseHeader("SESSION_STATUS"); // 通过XMLHttpRequest取得响应头，sessionstatus，
            if (sessionstatus == "TIME_OUT") {
                // 如果超时就处理 ，指定要跳转的页面
                window.location.href = ctx + "/login";
            }
        }, error: function (xhr, status, e) {
            if (xhr.status == 500) {
                var e = JSON.parse(xhr.responseText);
                console.error(e);
                frame.toastError("操作失败 " + e.message);
            } else {
                console.error("ajax请求出现异常:", xhr);
                console.error("status:", status);
                console.error(e);
                frame.toastError("出现异常，F12查看错误信息。");
            }
        }
    });
})();


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
    /**
     * multi-language
     */
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

/***
 * lay-ui 组件
 */
$(function () {
    $('form').addClass("layui-form");
    $('form select>option:gt(9)').parent().attr("lay-search", "");
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;
        form.render();
        form.on('select', function (data) {
            $(data.elem).change();
        });

        /* data picker */

        $("input.date-picker").each(function () {
            try {
                var $this = $(this);

                var option = $this.data("picker-option");
                if (!option) {
                    option = {};
                }
                if ((typeof option) === 'string') {
                    option = eval("(" + option + ")");
                }
                option.elem = this;
                option.lang = window.lang.lang;
                laydate.render(option);
            } catch (e) {
                console && console.error(e);
            }
        });
        $("input.date-range-picker").each(function () {
            try {
                var $this = $(this);
                var rangeOption = $this.data("range-option");
                if (!rangeOption) {
                    rangeOption = {};
                }
                if ((typeof rangeOption) === 'string') {
                    rangeOption = eval("(" + rangeOption + ")");
                }
                var $start = $('.date-range-picker-value:input[name=' + rangeOption.start + ']');
                var $end = $('.date-range-picker-value:input[name=' + rangeOption.end + ']');


                var option = $this.data("picker-option");
                if (!option) {
                    option = {};
                }
                if ((typeof option) === 'string') {
                    option = eval("(" + option + ")");
                }
                option.elem = this;
                option.lang = window.lang.lang;
                option.range = '~';

                option.done = function (value, date, endDate) {
                    if (value.indexOf('~') !== -1) {
                        var values = value.split('~');
                        $start.val(values[0] == null ? '' : values[0].trim());
                        $end.val(values[1] == null ? '' : values[1].trim());
                    } else {
                        $start.val("");
                        $end.val("");
                    }
                    // console.log(value); //得到日期生成的值，如：2017-08-18
                    // console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                    // console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                };
                laydate.render(option);
                var startVal = $start.val();
                var endVal = $end.val();
                if (startVal && endVal) {
                    $this.val(startVal + ' ~ ' + endVal);
                }
            } catch (e) {
                console && console.error(e);
            }
        });

    });

    //***  validator  ***//
    validator.register();

});