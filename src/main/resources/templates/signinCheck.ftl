<#assign user = signInUser?if_exists>
<#assign id = user.id?if_exists>
<#if id != "">
<script type="text/javascript">
    window.location.replace("/")
</script>
</#if>