<#import "style.ftl" as st>
<html>
<body>
<@st.style>
</@st.style>
<div class="main">
    <div>
        <form method="post">
            <input type="text" name="orderedDate" placeholder="orderedDate">
            <input type="text" name="executionDate" placeholder="executionDate">

            <select name="points" style="width:150px;">
                <#if points??>
                    <#list points as point>
                        <option value="${point.id}">${point.address}</option>
                    </#list>
                </#if>
            </select>
            <select name="clients" style="width:150px;">
                <#if clients??>
                    <#list clients as client>
                        <option value="${client.id}">${client.firstName}</option>
                    </#list>
                </#if>
            </select>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>

    <table>
        <tr>
            <th>id</th>
            <th>orderedDate</th>
            <th>executionDate</th>
            <th>lastName</th>
            <th>address</th>
            <th>delete</th>
            <th>update</th>
        </tr>
        <#list orders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.orderedDate}</td>
                <td>${order.executionDate}</td>
                <td>${order.client.lastName}</td>
                <td>${order.point.address}</td>
                <td><a href="/orders/delete/${order.id}">Delete</a></td>
                <td><a href="/ordersUpdates/${order.id}">Update</a></td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>