<#import "parts/common.ftl" as c>

<@c.page>
    List of personal computers
    <table>
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>PC</th>
        </tr>
        </thead>
        <tbody>
        <#list employeepc as epc>
            <tr>
                <td>${epc.employee.firstName}</td>
                <td>${epc.employee.lastName}</td>
                <td>${epc.pc.id}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>