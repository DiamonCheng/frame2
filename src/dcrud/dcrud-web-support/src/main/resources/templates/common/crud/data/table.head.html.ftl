<th
<#if head.sortable>sortable</#if>
<#if head.width??>width="${head.width}" </#if>
<#if head.sortable>sortOrder="${head.sortOrder}" </#if>
<#if head.sortable>sort="${head.sort}" </#if>
<#if head.sortable>sortFieldName="${head.sortFieldName}" </#if>
> <@spring.message head.name/>
    <#if head.sortable>
        <span class="layui-table-sort layui-inline" lay-sort="${head.sort}">
            <i class="layui-edge layui-table-sort-asc sort-btn" sortFieldName="${head.sortFieldName}" order="ASC"></i>
            <i class="layui-edge layui-table-sort-desc sort-btn" sortFieldName="${head.sortFieldName}" order="DESC"></i>
        </span>
    </#if>
</th>