<#--
textarea.id
textarea.type
textarea.name
textarea.title
textarea.value
textarea.placeholder
textarea.attrs
textarea.classes

-->


<textarea  ${(textarea.id??)?string('id="'+textarea.id+'"',"")}
${(textarea.name??)?string('name="'+textarea.name+'"',"")}
    <#if textarea.title??>

        title="<@spring.message textarea.title/>"
    </#if>

    <#if textarea.placeholder??>
        placeholder="<@spring.message textarea.placeholder/>"
    </#if>
        class="<#list textarea.classes as cls>${cls} </#list>"
<#list textarea.attrs as attr>
    ${attr.key}="${attr.value}"
</#list>
       >${textarea.value}</textarea>