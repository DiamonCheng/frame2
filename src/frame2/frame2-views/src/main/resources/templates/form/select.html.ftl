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
    <#if select.title??>
        title="<@spring.message select.title/>"
    </#if>
        class="<#list select.classes as cls>${cls} </#list>"
<#list select.attrs?keys as key>
    ${key}="${select.attrs[key]}"
</#list>
       >
<#list select.options as option>
    <option value="${option.value}" <#if option.selected>selected</#if>
    <#list option.attrs?keys as key>
        ${key}="${option.attrs[key]}"
    </#list>> <@spring.message option.text/> </option>
</#list>
</select>