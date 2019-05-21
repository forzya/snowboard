<#import "style.ftl" as st>
<html>
<body>
<@st.style>
</@st.style>
<div class="main">
    <form method="post">
        <input type="text" name="model" placeholder="model">
        <input type="text" name="type" placeholder="type">
        <input type="text" name="price" placeholder="price">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Изменить</button>
    </form>
</div>
</body>
</html>