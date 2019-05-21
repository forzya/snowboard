<#import "style.ftl" as st>
<html>
<body>
<@st.style>
</@st.style>
<div class="main">
    <div>
        <form method="post">
            <input type="text" name="price" placeholder="price">
            <input type="text" name="count" placeholder="count">

            <select name="products" style="width:150px;">
                <#if products??>
                    <#list products as product>
                        <option value="${product.id}">${product.model}</option>
                    </#list>
                </#if>
            </select>

            <select name="orders" style="width:150px;">
                <#if orders??>
                    <#list orders as order>
                        <option value="${order.id}">${order.orderedDate}</option>
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
            <th>price</th>
            <th>count</th>
            <th>model</th>
            <th>orderedDate</th>
            <th>delete</th>
            <th>update</th>
        </tr>
        <#list ordered as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.price}</td>
                <td>${order.count}</td>
                <td>${order.product.model}</td>
                <td>${order.orders.orderedDate}</td>
                <td><a href="/ordered/delete/${order.id}">Delete</a></td>
                <td><a href="/orderedUpdates/${order.id}">Update</a></td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>