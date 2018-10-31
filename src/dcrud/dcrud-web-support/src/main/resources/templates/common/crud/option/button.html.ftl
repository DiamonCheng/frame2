<#if button.permissionCheck>
<a ${(button.id??)?string('id="'+button.id+'"',"")}
        class="${button.cls} <#list button.classes as cls>${cls} </#list>"
    <#list button.attrs?keys as key>
        ${key}="${button.attrs[key]}"
    </#list>
        title="<@spring.message button.title/>"
><@spring.message button.name/></a>
</#if>