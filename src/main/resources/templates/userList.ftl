<#import "parts/common.ftl" as c>

<@c.page>
<h5 class="mb-5">List of users</h5>
<div class="my-md-4 container">
    <ul class="list-group">
        <#list users as user>
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <strong>${user.username}</strong>
            <#list user.roles as role>${role}<#sep>, </#list>
            <a href="/user/${user.id}" class="btn btn-primary">edit</a>
        </li>
        </#list>
    </ul>
</div>
</@c.page>