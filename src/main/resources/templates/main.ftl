<#import "style.ftl" as st>
<html>
<@st.style>
</@st.style>
<div>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Sign Out"/>
    </form>
</div>
</html>