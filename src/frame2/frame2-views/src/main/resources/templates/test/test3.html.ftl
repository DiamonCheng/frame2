
<#assign x = frame2root/>

<@frame2macro frame2root=x></@frame2macro>

<#macro frame2macro frame2root>
<#assign x=frame2root/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<ul>

    <#--frame2root = 2333 :-->
    <#--${frame2root.toString()}<br/>-->
    <#list x.viewList as view>
        <#--frame2root=view :
        <#assign frame2root=view/>
    ${frame2root.toString()}<br/>
        view:
        ${view.toString()}<br/>
        view.templateName:
        ${view.templateName}<br/>
        <#include view.templateName />-->
        <#import view.templateName as n/>
        <li>
            <@n.frame2macro view/>
        </li>
    </#list>

</ul>
</body>
</html>
</#macro>
