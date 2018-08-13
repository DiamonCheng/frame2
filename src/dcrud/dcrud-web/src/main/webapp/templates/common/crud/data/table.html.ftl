<section class="layui-collapse layui-card ">
    <div class="layui-card-body data-table" lay-size="sm">
        <table class="layui-table" ${(dataTable.id??)?string('id="'+dataTable.id+'"',"")}>
            <thead>
            <tr>
                <#list dataTable.tableHeadViews as tableHeadView>
                    ${RENDER(tableHeadView)}
                </#list>
                <th>option</th>
            </tr>
            </thead>
            <tbody>
            <#list dataTable.tableRows as tableRow>
            <tr>
                    <#list tableRow as dataCell>
                        ${RENDER(dataCell)}
                    </#list>
                <td>
                    <a href="javascript:" class="layui-btn layui-btn-xs">修改</a>
                    <a href="javascript:" class="layui-btn layui-btn-primary layui-btn-xs">详情</a>
                    <a href="javascript:" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
                </td>
            </tr>
            </#list>



            </tbody>
        </table>
    </div>
</section>
<#if dataTable.pageable>
<section class="layui-collapse layui-card ">
    <div class="layui-card-body page-bar" id="page-bar" totalCount="${dataTable.totalCount?c}"
         pageNo="${dataTable.pageNo?c}" pageSize="${dataTable.pageSize?c}">
    </div>
    <script src="/resources/js/common/datatable.js"></script>
</section>
</#if>
