/**
 * Created by BppleMan on 2017/11/13.
 */

var basePath;
var labels;

$(function () {
    $("select").select2().change(function () {
        var type = $(this).val();
        $("#type").val(type);
        var shouldChange = $("#should-change");
        if (type == "label") {
            shouldChange.empty();
            var searchItem1 = $("<div class='search-item'></div>");
            var select1 = $("<select name='labelTypeSelect' placeholder='选择分类' onchange='listenLabelSelect(this)'></select>");
            select1.addClass("text-center form-control select select-primary select-block mbl");
            var nullOption = $("<option></option>");
            select1.append(nullOption);
            for (var label in labels) {
                var option = $("<option value="+ label +"></option>").text(label);
                select1.append(option);
            }
            searchItem1.append(select1);

            var searchItem2 = $("<div class='search-item'></div>");
            var select2 = $("<select name='labelValueSelect' placeholder='选择题型' onchange='listenLabelSelect(this)'></select>").html("<option></option>");
            select2.addClass("text-center form-control select select-primary select-block mbl");
            searchItem2.append(select2);

            shouldChange.append(searchItem1);
            shouldChange.append(searchItem2);
            shouldChange.append("<input name='keyWord' type='hidden' class='form-control'>");
            select1.select2();
            select2.select2();
        } else {
            shouldChange.empty();
            var searchItem = $("<div class='search-item'></div>");
            var input = $("<input name=\"keyWord\" type='text' class='form-control'>");
            searchItem.append(input);
            shouldChange.append(searchItem);
        }
    });
});

function listenLabelSelect(select) {
    var type = $(select).val();
    var select2 = $("select[name='labelValueSelect']");
    if (select.name == "labelTypeSelect") {
        select2.empty();
        select2.append("<option></option>");
        for (var i = 0; i < labels[type].length; i++) {
            var obj = labels[type][i];
            select2.append("<option>"+obj+"</option>");
        }
        select2.select2();
    } else if (select.name == "labelValueSelect") {
        $("input[name='keyWord']").val($(select).val());
    }
}

function searchProblem() {
    console.log($("#type").val());
    var type = $("#type").val();
    var keyWord = $("input[name='keyWord']").val();
    if (keyWord == null || keyWord.trim() == "")
        return;
    location.href = basePath + "/bg/problem/list_problem?page=1&tp=" + type + "&kw=" + keyWord;
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