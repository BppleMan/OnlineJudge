/**
 * Created by BppleMan on 2017/11/23.
 */
$(function () {
    var form_group = null;
    $("input[type='radio']").change(function () {
        if ($(this).val() == "password") {
            if (form_group == null) {
                form_group = $("<div class='form-group'></div>");
                var label = $("<label class='col-md-3'></label>").text("输入密码:");
                var password = $("<input type='password' name='password' class='form-control input-group'>");
                form_group.append(label);
                form_group.append(password);
                $(".center-row").before(form_group);
            }
        } else {
            if (form_group != null) {
                form_group.remove();
                form_group = null;
            }
        }
    });
    // http://www.bootcss.com/p/bootstrap-datetimepicker/
    // 相关api查看这里
    $("#datetimepicker").datetimepicker({
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

});

function submit_contest() {
    var name = $("input.form-control[name='name']").val();
    var date = $("input.form-control[name='startTime']").val();
    if ((name == null || name == "") && (date == null || date == "")) {
        alert("竞赛名称和开始日期不能为空！");
        return false;
    } else if ((name == null || name == "") && date != null) {
        alert("竞赛名称不能为空！");
        return false;
    } else if (name != null && (date == null || date == "")) {
        alert("开始日期不能为空！");
        return false;
    }
    return true;
}