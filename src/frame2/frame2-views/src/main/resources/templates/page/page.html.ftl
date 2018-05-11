<!doctype html>
<html lang="en">
<head>
    <title><@spring.message title/></title>
    <#list headResources as headResource>
        ${headResource}
    </#list>
</head>
<body>
<#list components as component>
    ${RENDER(component)}
</#list>
<#list bottomJsResources as bottomJsResource>
    ${bottomJsResource}
</#list>
</div>
</body>
</html>