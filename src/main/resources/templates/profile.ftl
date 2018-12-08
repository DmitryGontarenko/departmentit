<#import "parts/common.ftl" as c>

<@c.page>
<h5 class="mb-5">${username}</h5>
<form method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password: </label>
        <div class="col-sm-6">
            <input type="password" class="form-control" name="password"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email: </label>
        <div class="col-sm-6">
            <input type="email" class="form-control" name="email" value="${email!''}"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> First name: </label>
        <div class="col-sm-6">
            <input type="text" class="form-control" name="firstName" value="${firstName!''}"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Last name: </label>
        <div class="col-sm-6">
            <input type="text" class="form-control" name="lastName" value="${lastName!''}"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Post: </label>
        <div class="col-sm-6">
            <input type="text" class="form-control" disabled="true" name="postId" value="${postId!''}"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Sub division: </label>
        <div class="col-sm-6">
            <input type="text" class="form-control" disabled="true" name="subDivId" value="${subDivId!''}"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Save</button>
</form>
</@c.page>
