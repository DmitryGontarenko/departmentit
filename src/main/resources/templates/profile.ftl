<#import "parts/common.ftl" as c>

<@c.page>
<h5>${username}</h5>
<form method="post">
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><label> Email: <input type="email" name="email" value="${email!''}"/> </label></div>
    <div><label> First name: <input type="text" name="firstName" value="${firstName!''}"/> </label></div>
    <div><label> Last name: <input type="text" name="lastName" value="${lastName!''}"/> </label></div>
    <div><label> Post: <input type="text" disabled="true" name="postId" value="${postId!''}"/> </label></div>
    <div><label> Sub division: <input type="text" disabled="true" name="subDivId" value="${subDivId!''}"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit">Save</button>
</form>
</@c.page>
