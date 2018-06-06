<th
<#if head.sortable>sortable</#if>
<#if head.width??>width="${head.width}" </#if>
<#if head.sortable>sortOrder="${head.sortOrder}" </#if>
<#if head.sortable>sortFieldName="${head.sortFieldName}" </#if>
> <@spring.message head.name/>
    <#if head.sortable>
        <span class="layui-table-sort layui-inline" lay-sort="${head.sort}">
            <i class="layui-edge layui-table-sort-asc"></i>
            <i class="layui-edge layui-table-sort-desc"></i>
        </span>
    </#if>
</th>