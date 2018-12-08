<#import "parts/common.ftl" as c>

<@c.page>
<h5 class="mb-5">User editor</h5>

<form action="/user" method="post">

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Username:</label>
        <div class="col-sm-6">
            <input class="form-control" type="text" name="username" value="${user.username}">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Role:</label>
        <div class="col-sm-6">
            <#list roles as role>
            <input class="mr-1" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} >${role}
            </#list>
        </div>
    </div>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button class="btn btn-primary" type="submit">Save</button>
</form>
</@c.page>