var basePath;

var USERNAME, PASSWORD, EMAIL, TELEPHONE, NICKNAME;

function changeNickname() {
    var formData = new Object();
    formData.tp = NICKNAME;
    formData.val = $("#nickname").val();
    var url = basePath + "/user/information/change_info";
    var jqXhr = $.post(url, formData);
    jqXhr.done(function (result) {
        if (result == "success") {
            alert("success", "修改昵称成功！")
        } else {
            alert("danger", "修改昵称失败：" + result)
        }
    })
}

function changeEmail() {
    var formData = new Object();
    formData.tp = EMAIL;
    formData.val = $("#email").val();
    var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
    if (!reg.test(formData.val)) {
        alert("danger", "邮箱格式不合法！")
        return
    }
    var url = basePath + "/user/information/change_info";
    var jqXhr = $.post(url, formData);
    jqXhr.done(function (result) {
        if (result == "success") {
            alert("success", "换绑邮箱成功！")
        } else {
            alert("danger", "换绑邮箱失败：" + result)
        }
    })
}

function changeTelephone() {
    var formData = new Object();
    formData.tp = TELEPHONE;
    formData.val = $("#telephone").val();
    var reg = new RegExp("^1[3|4|5|8][0-9]\\d{4,8}$");
    console.log(reg.test(formData.val));
    if (!reg.test(formData.val)) {
        alert("danger", "手机格式不合法！")
        return
    }
    var url = basePath + "/user/information/change_info";
    var jqXhr = $.post(url, formData);
    jqXhr.done(function (result) {
        if (result == "success") {
            alert("success", "换绑手机成功！")
        } else {
            alert("danger", "换绑手机失败：" + result)
        }
    })
}

function changePassword() {
    var formData = new Object();
    formData.tp = PASSWORD;
    if ($("#newPassword").val() == $("#newPasswordRepeat").val()) {
        formData.val = $("#newPassword").val();
        formData.oldP = $("#oldPassword").val();
        var url = basePath + "/user/information/change_info";
        var jqXhr = $.post(url, formData);
        jqXhr.done(function (result) {
            if (result == "success") {
                alert("success", "密码修改成功！")
            } else {
                alert("danger", "密码修改失败：" + result)
            }
        })
    }
}

function inputValidateNewPassword(newPasswordRepeat) {
    var npdiv = $("#newPassword").parent();
    var nprdiv = $(newPasswordRepeat).parent();
    //new password value
    var npv = $("#newPassword").val();
    //new password repeat value
    var nprv = $(newPasswordRepeat).val();
    if (npv != nprv) {
        npdiv.attr("class", "col-lg-10 has-error");
        nprdiv.attr("class", "col-lg-8 has-error");
    } else if (nprv == npv) {
        npdiv.attr("class", "col-lg-10 has-success");
        nprdiv.attr("class", "col-lg-8 has-success");
    }
}

function getVerificationCode(button) {
    var formData = new Object();
    formData.tp = button.name;
    $.ajax({
        url : basePath + "/user/information/verification_code",
        type : "POST",
        data : formData
    }).done(function (result) {
        console.log(result);
        alert(result, "获取验证码"+result)
    })
}

function alert(option, text) {
    $("#alert").attr("class", "col-lg-8 alert alert-dismissable alert-" +  option);
    $("#alert").css("display", "block");
    $("#alert").children("*").eq(1).text(text);
}

function closeAlert() {
    $("#alert").css("display", "none");
}