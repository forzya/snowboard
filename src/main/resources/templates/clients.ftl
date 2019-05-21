<#import "style.ftl" as st>
<html>

<body>
<@st.style>
</@st.style>
<div class="main">
    <div>
        <form method="post">
            <input type="text" name="firstName" placeholder="firstName">
            <input type="text" name="lastName" placeholder="lastName">
            <input type="text" name="phone" placeholder="phone">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>

    <table>
        <tr>
            <th>id</th>
            <th>firstName</th>
            <th>lastName</th>
            <th>phone</th>
            <th>delete</th>
            <th>update</th>
        </tr>
        <#list clients as client>
            <tr>
                <td>${client.id}</td>
                <td>${client.firstName}</td>
                <td>${client.lastName}</td>
                <td>${client.phone}</td>
                <td><a href="/clients/delete/${client.id}">Delete</a></td>
                <td><a href="/clientsUpdates/${client.id}">Update</a></td>
            </tr>
        </#list>
    </table>
</div>

</body>
</html>