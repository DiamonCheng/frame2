/***
 *
 */

$(function () {
    //*** page bar ***//
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        $("#page-bar").each(function () {
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
                limits: $this.attr("pageSizes").split(","),
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
                        // console.log(page.curr - 1, page.limit);
                        $('#pageForm')
                            .append($("<input type='hidden' name='pageSize'>").val(page.limit))
                            .append($("<input type='hidden' name='pageNo'>").val(page.curr))
                            .submit();
                    }
                }
            });
        });
    });
    //** sort order *//
    var sortMap = {
        "DESC": "ASC",
        "ASC": "",
        "": "DESC"
    };
    var sortList = [];
    $('#pageForm').find(".data-table table th[sortable]").each(function (e) {
        sortList.push({
            order: $(this).attr("sortorder"),
            sort: $(this).attr("sort"),
            fieldName: $(this).attr("sortfieldname")
        });
    });
    console.log(sortList);
    var $sort = $('<div style="display:none;">');
    sortList.sort(function (a, b) {
        if (a.order == null) {
            return -1;
        }
        if (b.order == null) {
            return 1;
        }
        return parseInt(a.order) - parseInt(b.order);
    });
    for (var index in sortList) {
        var sort = sortList[index];
        if (sort.sort != "") {
            $sort.append($('<input type="hidden" name="orderBy">').attr("fieldname", sort.fieldName).val(sort.fieldName + " " + sort.sort));
        }
    }
    $('#pageForm').append($sort);
    $('#pageForm').find(".data-table table th[sortable] .sort-btn").click(function (e) {
        var sortField = $(this).attr("sortFieldName");
        var order = $(this).attr("order");
        $sort.find("input[name=orderBy][fieldName='" + sortField + "']").remove();
        $('<input type="hidden" name="orderBy">').attr("fieldname", sortField).val(sortField + " " + order).prependTo($sort);
        $('#pageForm')
            .append($("<input type='hidden' name='pageSize'>").val($("#page-bar").attr("pageSize")))
            .submit();
        e.stopPropagation();
    });
    $('#pageForm').find(".data-table table th[sortable]").click(function () {
        var sortField = $(this).attr("sortFieldName");
        var order = $(this).attr("sort");
        if (order == null || order === "") {
            order = "ASC"
        } else if (order.toUpperCase() === "ASC") {
            order = "DESC"
        } else if (order.toUpperCase() === "DESC") {
            order = "";
        }
        $sort.find("input[name=orderBy][fieldName='" + sortField + "']").remove();
        if (order != "") {
            $('<input type="hidden" name="orderBy">').attr("fieldname", sortField).val(sortField + " " + order).prependTo($sort);
        }
        $('#pageForm')
            .append($("<input type='hidden' name='pageSize'>").val($("#page-bar").attr("pageSize")))
            .submit();
    });
});