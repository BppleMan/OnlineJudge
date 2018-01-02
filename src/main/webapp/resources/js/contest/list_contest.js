/**
 * Created by BppleMan on 2017/11/13.
 */

var basePath;

$(function () {
    $("ul.pagination").find("a").each(function () {
        if (this.text != "Prev" && this.text != "Next"){
            if (this.href == document.location.href) {
                $(this).parent().addClass("active");
            }
        }
    });

    $("select").select2().change(function () {
        var type = $(this).val();
        $("#type").val(type);
    });
});


function searchContest() {
    console.log($("#type").val());
    var type = $("#type").val();
    var keyWord = $("input[name='keyWord']").val();
    if (keyWord == null || keyWord.trim() == "")
        return;
    location.href = basePath + "/contest/list_contest?page=1&tp=" + type + "&kw=" + keyWord;
}

function showContest(contestId) {
    var url = basePath + "/contest/should_input_password";
    $.post(url, {"contestId" : contestId}, function (result) {
        if (result == "login") {
            showShouldLogin();
        } else if (result == "password") {
            showInputPassword(contestId);
        } else if (result == "noPassword") {
            goToShowContest(contestId, 1);
        }
    })
}

function showShouldLogin() {
    bootbox.alert("请先登录！");
}

function showInputPassword(contestId) {
    var input_group = $("<div class='input-group'></div>");
    var input_group_addon = $("<span class='input-group-addon'>密码</span>");
    var input = $("<input type='password' class='form-control' />");
    input_group.append(input_group_addon);
    input_group.append(input);
    bootbox.dialog({
        title : "输入密码",
        message : input_group,
        buttons : {
            "cancel" : {
                "label" : "<i class='icon-info'></i> 取消",
                "className" : "btn btn-danger",
                "callback" : function() { }
            },
            "success" : {
                "label" : "<i class='icon-ok'></i> 确定",
                "className" : "btn btn-primary",
                "callback" : function() {
                    var param = new Object();
                    param.contestId = contestId;
                    param.password = $(input).val();
                    $.ajax({
                        url : basePath + "/contest/check_contest_password",
                        type : "POST",
                        dataType : "json",
                        data : param,
                        success : function (result) {
                            if (result == true){
                                goToShowContest(contestId, 1);
                            } else {
                                bootbox.alert("密码错误！");
                            }
                        }
                    });
                }
            }
        }
    });
}

function goToShowContest(contestId, page) {
    location.href = basePath + "/contest/show_contest?page=" + page + "&contestId=" + contestId;
}