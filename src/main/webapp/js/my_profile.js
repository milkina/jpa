function saveUserSettings(contextPath)
{   var result = confirm("Are you sure you want to save?");
    if (result) {
        var formName = document.getElementById('ProfileForm');
        formName.action = contextPath + "/servlet/ChangeUserSettings";
        formName.method = "POST";
        formName.submit();
    }
}
function openChangePasswordWindow(contextPath) {
    window.location.href  =contextPath + '/person/ChangePassword.jsp';
}