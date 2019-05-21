<#import "style.ftl" as st>
<html>
<body>
<@st.style>
</@st.style>
<div class="main">
    <form method="post">
        <input type="text" name="firstName" placeholder="firstName">
        <input type="text" name="lastName" placeholder="lastName">
        <input type="text" name="phone" placeholder="phone">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Изменить</button>
    </form>
</div>
</body>
</html>