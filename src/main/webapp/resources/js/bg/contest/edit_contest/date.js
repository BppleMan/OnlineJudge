var type = null;
$(function () {
    $("#startTime").datetimepicker({
        language:  'zh-CN',
        showSecond: true,
        showMillisec: true,
        timeFormat: 'hh:mm:ss:l',
        startDate: new Date,
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        minuteStep: 1
    });

    $("#type").change(function () {
        var temp = $("#type").val();
        var div = $("#type").parent().parent();
        if (temp === "password") {
            var col = $("<div class='col-lg-4'></div>")
            var input = $("<input class='form-control' name='password' type='password' placeholder='输入参赛密码'>")
            col.append(input)
            div.append(col)
        } else if (type === "password"){
            div.children().last().remove();
        }
        type = temp;
    })
})

function formValidate(form) {
    var formData = $(form)
    var type = $("#type").val();
    if (type == null || $.trim(type) === "") {
        bootbox.alert("需要选择一个类型")
        return false;
    }
    var input = $("<input type='hidden' name='type'>").val(type);
    formData.append(input);
}

function submitAndContinue() {
    var form = $("#contestForm")
    var action = form.attr("action")
    action += "?isContinue=true"
    form.attr("action", action);
    form.submit();
}