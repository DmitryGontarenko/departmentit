<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>




<@c.page>
<form method="get" action="main">
    <input type="text" name="filter" value="${filter?ifExists}">
    <button type="submit">Найти</button>
</form>

<div>
    <form method="post">
        <input type="text" name="text" placeholder="Введите сообщение"
               class="form-control ${(textError??)?string('is-invalid', '')}"
               value="<#if order??>${order.text}</#if>"  />
        <#if textError??>
        <div class="invalid-feedback">
            ${textError}
        </div>
        </#if>

        <input type="text" name="tag" placeholder="Тэг"
               class="form-control $({tagError??}?string('is-invalid', ''))"
               value="<#if order??>${order.tag}</#if>" >
        <#if tagError??>
        <div class="invalid-feedback">
            ${tagError}
        </div>
        </#if>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Добавить</button>
    </form>
</div>

<#list orders as order>
<div>
    <table>
        <thead>
        <tr>
            <th>Description</th>
            <th>Service</th>
            <th>Author</th>
            <th>Created at</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${order.text}</td>
            <td>${order.tag}</td>
            <td>${order.authorName}</td>
            <td>${order.createdAt?string('dd.MM.yyyy HH:mm:ss')}</td>
            <td>${order.status}</td>
        </tr>
        </tbody>
    </table>
</div>
<#else>
No order
</#list>

</@c.page>