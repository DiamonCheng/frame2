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
        })

    });

    //***  validator  ***//
    validator.register();

    //*** page bar ***//
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        $(".page-bar").each(function () {
            var $this = $(this);
            var totalCount = $this.attr("totalCount");
            if (totalCount == null) totalCount = 0;
            var pageNo = $this.attr("pageNo");
            if (pageNo == null) pageNo = 0;
            var pageSize = $this.attr("pageSize");
            if (pageSize == null) pageSize = 0;
            //执行一个laypage实例
            laypage.render({
                elem: this,//注意，这里的 test1 是 ID，不用加 # 号
                count: totalCount, //数据总数，从服务端得到
                limit: pageSize,
                limits: [5, 10, 20, 100],
                curr: parseInt(pageNo) + 1,
                next: window.lang.pageBar.next,
                prev: window.lang.pageBar.prev,
                total1: window.lang.pageBar.total1,
                total2: window.lang.pageBar.total2,
                itemPage: window.lang.pageBar.itemPage,
                to: window.lang.pageBar.to,
                pageString: window.lang.pageBar.pageString,
                submit: window.lang.pageBar.submit,
                layout: ['prev', 'page', 'next', 'count', 'limit', 'skip'],
                jump: function (page, first) {
                    if (!first) {
                        console.log(page.curr - 1,
                            page.limit)
                    }
                }
            });
        });

    });
});