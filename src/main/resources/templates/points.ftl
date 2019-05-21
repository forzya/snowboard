<#import "style.ftl" as st>
<html>
<body>
<@st.style>
</@st.style>
<div class="main">
    <div>
        <form method="post">
            <input type="text" name="address" placeholder="address">
            <input type="text" name="phone" placeholder="phone">
            <input type="text" name="email" placeholder="email">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>

    <table>
        <tr>
            <th>id</th>
            <th>address</th>
            <th>phone</th>
            <th>email</th>
            <th>delete</th>
            <th>update</th>
        </tr>
        <#list points as point>
            <tr>
                <td>${point.id}</td>
                <td>${point.address}</td>
                <td>${point.phone}</td>
                <td>${point.email}</td>
                <td><a href="/points/delete/${point.id}">Delete</a></td>
                <td><a href="/pointsUpdates/${point.id}">Update</a></td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>