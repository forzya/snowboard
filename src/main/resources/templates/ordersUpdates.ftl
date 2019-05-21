<#import "style.ftl" as st>
<html>
<body>
<@st.style>
</@st.style>
<div class="main">
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
        <button type="submit">Изменить</button>
    </form>
</div>
</body>
</html>