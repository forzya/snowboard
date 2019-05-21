<#import "style.ftl" as st>
<html>
<body>
<@st.style>
</@st.style>
<div class="main">
    <form method="post">
        <input type="text" name="address" placeholder="address">
        <input type="text" name="phone" placeholder="phone">
        <input type="text" name="email" placeholder="email">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Изменить</button>
    </form>
</div>
</body>
</html>