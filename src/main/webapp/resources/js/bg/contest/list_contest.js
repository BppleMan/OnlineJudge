/**
 * Created by BppleMan on 2017/11/13.
 */

$(function () {
    $("#condition").select2().change(function () {
        var condition = $(this).val();
        var keyWord = $("#keyWordItem");
        if (condition === "type") {
            var select2 = $("<select id='keyWord' placeholder='选择类型' class='text-center form-control select select-primary select-block mbl'></select>");
            var nullOption = $("<option></option>");
            select2.append(nullOption);
            for (var i in types) {
                var option = $("<option value="+types[i]+"></option>").text(types[i]);
                select2.append(option);
            }
            keyWord.empty()
            keyWord.append(select2)
            select2.select2()
        } else if (condition === "status") {
            var select2 = $("<select id='keyWord' placeholder='选择状态' class='text-center form-control select select-primary select-block mbl'></select>");
            var nullOption = $("<option></option>");
            select2.append(nullOption);
            for (var i in statuses) {
                var option = $("<option value="+statuses[i]+"></option>").text(statuses[i]);
                select2.append(option);
            }
            keyWord.empty()
            keyWord.append(select2)
            select2.select2()
        } else {
            var input = $("<input id='keyWord' type='text' class='form-control'>")
            keyWord.empty()
            keyWord.append(input)
        }
    });
});

function searchContest() {
    var type = $("#condition").val()
    var keyWord = $("#keyWord").val()
    if (type == null || $.trim(type) === "") {
        bootbox.alert("请选择搜索条件")
    } else if (keyWord == null || $.trim(keyWord) === "") {
        bootbox.alert("请选择|输入关键词")
    }
    console.log(type)
    console.log(keyWord)
    location.href = basePath + "/bg/contest/list_contest?page=1&tp=" + type + "&kw=" + keyWord;
}


Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}