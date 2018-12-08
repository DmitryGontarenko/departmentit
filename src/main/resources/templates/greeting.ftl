<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<h5>
    Hello, <#if user??>${name}<#else>guest</#if>
</h5>
<div>This is a web-service that will be work</div>
</@c.page>
