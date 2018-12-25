<#macro listNode nodeList,nodeName>
<dl class="tree-select-list">
    <#list nodeList as node>
        <dd class="tree-select-node">
            <span class="tree-select-expand"></span>
            <label class="tree-select-node-label">
                <input class="tree-select-node-checkbox" lay-ignore
                   <#if node.nodeTitle??>
                   title="<@spring.message node.nodeTitle/>"
                   </#if>
                       type="checkbox" name="${nodeName}"
                       value="${node.nodeValue}" ${node.checked?string('checked="checked"','')}/>
                <div class="tree-select-node-wrap">
                    <i class="${node.nodeIcon}"></i>
                <@spring.message node.nodeText/>
                </div>
            </label>
        <#if (node.children??) && (node.children?size gt 0) >
            <@listNode node.children nodeName />
        </#if>
        </dd>
    </#list>
</dl>
</#macro>
<div class="tree-select" ${(id??)?string('id="'+id+'"','')}>
    <@listNode nodeList,nodeName />
    <script src="/resources/js/common/tree-select.js"></script>
</div>