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
<#import "/spring.ftl" as spring />
<#escape x as x?html>
    ${frame2root.name}
    <#assign n="message"/>
    <#--<@spring.message code="???"/>-->
</#escape>

</body>
</html>