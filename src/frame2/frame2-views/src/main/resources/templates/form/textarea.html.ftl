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
        title="<@spring.message textarea.title/>"
        placeholder="<@spring.message textarea.placeholder/>"
        class="<#list textarea.classes as cls>${cls} </#list>"
<#list textarea.attrs as attr>
    ${attr.key}="${attr.value}"
</#list>
       >${textarea.value}</textarea>