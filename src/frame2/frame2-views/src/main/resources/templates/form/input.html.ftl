<#--
input.id
input.type
input.name
input.title
input.value
input.placeholder
input.attrs
input.classes

-->


<input  ${(input.id??)?string('id="'+input.id+'"',"")}
        type="${input.type}"
${(input.name??)?string('name="'+input.name+'"',"")}
    <#if input.title??>
        title="<@spring.message input.title/>"
    </#if>
${(input.value??)?string('value="'+input.value+'"',"")}
    <#if input.placeholder??>
        placeholder="<@spring.message input.placeholder/>"
    </#if>
        class="<#list input.classes as cls>${cls} </#list>"
        autocomplete="off"
<#list input.attrs?keys as key>
    ${key}="${input.attrs[key]}"
</#list>
       />