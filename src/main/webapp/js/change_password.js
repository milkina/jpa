function changePassword(contextPath) {
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirmPassword').value;
    if (password.length == 0 || confirmPassword.length == 0) {
        alert("Please enter all required fields.");
        return;
    }
    if (password != confirmPassword) {
        alert("Password should be the same as Confirm Password.");
        return;
    }
    var formName = document.getElementById('ChangePasswordForm');
    formName.action = contextPath + "/servlet/ChangePassword";
    formName.method = "POST";
    formName.submit();
}