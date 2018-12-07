<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>




<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <div>
        <form method="post">
            <div class="form-group">
                <input type="text" name="text" placeholder="Введите сообщение"
                       class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if order??>${order.text}</#if>"  />
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>

            <!--<input type="text" name="tag" placeholder="Тэг"-->
                   <!--class="form-control $({tagError??}?string('is-invalid', ''))"-->
                   <!--value="<#if order??>${order.tag}</#if>" >-->
            <div class="form-group">
                <select name="tag" class="form-control" id="exampleFormControlSelect1">
                    <option>fix</option>
                    <option>clear</option>
                    <option>tune</option>
                    <option>diagnostic</option>
                    <option>install</option>
                </select>
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>

    <div class="card-columns">
        <#list orders as order>
            <div class="card my-3 <#if order.status == "NEW" || order.status == "IN_PROGRESS">border-primary<#else>secondary-primary</#if>">

                    <div class="card-header">${order.status} Order ${order.id}</div>
                    <div class="m-2">
                        <h6 class="card-subtitle mb-2 text-muted"><a href="#" class="badge badge-secondary">${order.tag}</a></h6>
                        <p class="card-text">${order.text}</p>

                        <p class="card-text"><small class="text-muted">
                            <cite title="Source Title">${order.author.employee.lastName} ${order.author.employee.firstName}, ${order.author.employee.postId.name}</cite></br>
                            <cite title="Source Title">${order.author.employee.subDivId.name}, ${order.author.employee.subDivId.campusId.name}</cite></br>
                            <cite title="Source Title">${order.author.employee.subDivId.phone}</cite>
                        </small></p>

                        <div class="form-inline">
                            <form action="inProgressOrder" method="post" class="mr-1">
                                <input type="hidden" value="${order.id}" name="orderId">
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                                <#if order.status != "CLOSED" && order.status != "IN_PROGRESS"><button type="submit" class="btn btn-primary">IN PROGRESS</button></#if>
                            </form>

                            <form action="closeOrder" method="post">
                                <input type="hidden" value="${order.id}" name="orderId">
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                                <#if order.status != "CLOSED"><button type="submit" class="btn btn-primary">CLOSED</button></#if>
                            </form>
                        </div>

                    </div>
                    <div class="card-footer text-muted">${order.createdAt?string('dd.MM.yyyy HH:mm:ss')}</div>

            </div>
        <#else>
        No order
        </#list>
    </div>

</@c.page>