<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">DepartmentIT</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Orders </a>
            </li>
            <!--Раздел открыт только для администратора-->
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User list </a>
            </li>
            </#if>
            <!--Раздел открыт только для зарегистрированных пользователей-->
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/user/profile">Profile </a>
            </li>
            </#if>
        </ul>

        <!--Выводим имя пользователя-->
        <div class="navbar-text mr-3">${name}</div>
        <!--Если сессия с пользователем открыта-показана кнопка Log out-->
        <#if known><@l.logout /></#if>
    </div>
</nav>