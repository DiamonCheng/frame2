<form ${(id??)?string('id="'+id+'"',"")} action="${action}" method="${method}">
    <#list contents as content>
        ${RENDER(content)}
    </#list>
</form>