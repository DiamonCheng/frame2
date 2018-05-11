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
        title="<@spring.message input.title/>"
${(input.value??)?string('value="'+input.value+'"',"")}
        placeholder="<@spring.message input.placeholder/>"
        class="<#list input.classes as cls>${cls} </#list>"
        autocomplete="off"
<#list input.attrs as attr>
    ${attr.key}="${attr.value}"
</#list>
       />