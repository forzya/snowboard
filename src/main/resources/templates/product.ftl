<#import "style.ftl" as st>
<html>
<body>
<@st.style>
</@st.style>
<div class="main">
    <div>
        <form method="post">
            <input type="text" name="model" placeholder="model">
            <input type="text" name="type" placeholder="type">
            <input type="text" name="price" placeholder="price">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>

    <table>
        <tr>
            <th>id</th>
            <th>model</th>
            <th>type</th>
            <th>price</th>
            <th>delete</th>
            <th>update</th>
        </tr>
        <#list products as product>
            <tr>
                <td>${product.id}</td>
                <td>${product.model}</td>
                <td>${product.type}</td>
                <td>${product.price}</td>
                <td><a href="/product/delete/${product.id}">Delete</a></td>
                <td><a href="/productUpdates/${product.id}">Update</a></td>
            </tr>
        </#list>
    </table>

</div>
</body>
</html>