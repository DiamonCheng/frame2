<#--

select
    id
    name
    title
    classes
    attrs
    options
        text
        value
        selected
        attrs
-->







<select  ${(select.id??)?string('id="'+select.id+'"',"")}
${(select.name??)?string('name="'+select.name+'"',"")}
        title="<@spring.message select.title/>"
        class="<#list select.classes as cls>${cls} </#list>"
<#list select.attrs as attr>
    ${attr.key}="${attr.value}"
</#list>
       >
<#list select.options as option>
    <option value="${option.value}" <#if option.selected>selected</#if>
    <#list option.attrs as attr>
        ${attr.key}="${attr.value}"
    </#list>> <@spring.message option.text/> </option>
</#list>
</select>