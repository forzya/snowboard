<#import "style.ftl" as st>
<html>
<body>
<@st.style>
</@st.style>
<div class="main">
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
</body>
</html>