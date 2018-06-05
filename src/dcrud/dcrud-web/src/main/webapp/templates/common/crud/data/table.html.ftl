<section class="layui-collapse layui-card ">
    <div class="layui-card-body data-table" lay-size="sm">
        <table class="layui-table" ${(dataTable.id??)?string('id="'+dataTable.id+'"',"")}>
            <thead>
            <tr>
                <th>昵称
                    <span class="layui-table-sort layui-inline" lay-sort="desc">
                            <i class="layui-edge layui-table-sort-asc"></i>
                            <i class="layui-edge layui-table-sort-desc"></i>
                        </span>
                </th>
                <th>积分</th>
                <th>签名</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>贤心1</td>
                <td>66</td>
                <td>人生就像是一场修行a</td>
                <td>
                    <a href="javascript:" class="layui-btn layui-btn-xs">修改</a>
                    <a href="javascript:" class="layui-btn layui-btn-primary layui-btn-xs">详情</a>
                    <a href="javascript:" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
                </td>
            </tr>
            <tr>
                <td>贤心2</td>
                <td>88</td>
                <td>人生就像是一场修行b</td>
                <td>
                    <a href="javascript:" class="layui-btn layui-btn-xs">修改</a>
                    <a href="javascript:" class="layui-btn layui-btn-primary layui-btn-xs">详情</a>
                    <a href="javascript:" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
                </td>
            </tr>
            <tr>
                <td>贤心3</td>
                <td>33</td>
                <td>人生就像是一场修行c</td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>
</section>
<#if dataTable.pageable>
<section class="layui-collapse layui-card ">
    <div class="layui-card-body page-bar" id="page-bar" totalCount="${dataTable.totalCount}"
         pageNo="${dataTable.pageNo}" pageSize="${dataTable.pageSize}">
    </div>
    <script>
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
        });
    </script>
</section>
</#if>
