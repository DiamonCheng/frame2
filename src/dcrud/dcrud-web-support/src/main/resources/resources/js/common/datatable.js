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
            var limits = $this.attr("pageSizes").split(",");
            for (var i in limits) {
                limits[i] = parseInt(limits[i]);
            }
            //执行一个laypage实例
            laypage.render({
                elem: this,//注意，这里的 test1 是 ID，不用加 # 号
                count: totalCount, //数据总数，从服务端得到
                limit: pageSize,
                limits: limits,
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
                            .append($("<input type='hidden' name='pageNo'>").val(page.curr - 1))
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
    //console.log(sortList);
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
        var order = $(this).attr("sortOrder");
        $sort.find("input[name=orderBy][fieldName='" + sortField + "']").remove();
        $('<input type="hidden" name="orderBy">').attr("fieldname", sortField).val(sortField + " " + order).prependTo($sort);
        $('#pageForm')
            .append($("<input type='hidden' name='pageSize'>").val($("#page-bar").attr("pageSize")))
            .submit();
        e.stopPropagation();
    });
    var changeSortFunction = function (e) {
        var sortField = $(e).attr("sortFieldName");
        var order = $(e).attr("sort");
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
    };
    $('#pageForm').find(".data-table table th[sortable]").click(function () {
        // 由于拖拽和排序点击冲突，故直接把排序的单击事件写进拖拽里面
        //changeSortFunction(this);
    });
    $('.query-panel-head')
    // .attr('unselectable', 'on')
    // .css('user-select', 'none')
        .on('selectstart', false);

    // data table column draggable
    // 由于拖拽和排序点击冲突，故直接把排序的单击事件写进拖拽里面
    /**
     * Created by ywj on 2017/10/24.
     */
    var tabSize = tabSize || {};
    tabSize.init = function (elem) {
        var i,
            self,
            table = elem,
            header = table.rows[0],
            tableX = header.clientWidth,
            length = header.cells.length;

        for (i = 0; i < length; i++) {
            header.cells[i].onmousedown = function (e) {
                self = this;
                if (event.offsetX > self.offsetWidth - 10) {
                    self.mouseDown = true;
                    self.oldX = event.x;
                    self.oldWidth = self.offsetWidth;
                }
            };
            header.cells[i].onmousemove = function (e) {
                if (event.offsetX > this.offsetWidth - 10) {
                    this.style.cursor = 'col-resize';
                } else {
                    this.style.cursor = 'default';
                }
                if (self == undefined) {
                    self = this;
                }
                if (self.mouseDown != null && self.mouseDown == true) {
                    self.style.cursor = 'default';
                    if (self.oldWidth + (event.x - self.oldX) > 0) {
                        self.width = self.oldWidth + (event.x - self.oldX);
                    }
                    self.style.width = self.width;
                    table.style.width = tableX + (event.x - self.oldX) + 'px';
                    self.style.cursor = 'col-resize';
                }
            };
            table.onmouseup = function (e) {
                if (self == undefined) {
                    self = this;
                }
                if (self.mouseDown != null && self.mouseDown == true) {
                    self.mouseDown = false;
                    self.style.cursor = 'default';
                    tableX = header.clientWidth;
                } else if ($(e.target).is("#pageForm .data-table table th[sortable]")) {
                    changeSortFunction(e.target);
                }


            };
        }
    };
    tabSize.init($('#pageForm').find(".data-table>table")[0]);
    $('.data-table>table th')
    // .attr('unselectable', 'on')
    // .css('user-select', 'none')
        .on('selectstart', false);
});