/**
 * Created by BppleMan on 2017/11/13.
 */

var basePath;
var labels;

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
        var searchKeyWord = $(".search-keyword");
        if (type == "label") {
            searchKeyWord.empty();
            var select1 = $("<select name='labelTypeSelect' placeholder='选择分类' onchange='listenLabelSelect(this)'></select>");
            select1.addClass("text-center form-control select select-inverse select-block mbl");
            var nullOption = $("<option></option>");
            select1.append(nullOption);
            for (var label in labels) {
                var option = $("<option value="+ label +"></option>").text(label);
                select1.append(option);
            }
            var select2 = $("<select name='labelValueSelect' placeholder='选择题型' onchange='listenLabelSelect(this)'></select>").html("<option></option>");
            select2.addClass("text-center form-control select select-inverse select-block mbl");
            var searchBtn = $("<button type='button' class='btn btn-default btn-block' onclick='searchProblem()'>搜索</button>")
            searchKeyWord.append(select1);
            searchKeyWord.append(select2);
            searchKeyWord.append(searchBtn);
            searchKeyWord.append("<input name='keyWord' type='hidden' class='form-control' value='${keyWord}'>");
            select1.select2();
            select2.select2();
        } else {
            var div = $("<div class='input-group'></div>");
            var input = $("<input id='keyWord' name='keyWord' type='text' class='form-control'>");
            var span = $("<span class='input-group-btn'><button class='btn btn-default' type='button' onclick='searchProblem()'>搜索</button></span>");
            div.append(input);
            div.append(span);
            searchKeyWord.html(div);
        }
    });
});

function listenLabelSelect(select) {
    var type = $(select).val();
    var select2 = $(select).parent().find("select").eq(1);
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
    location.href = basePath + "/problem/list_problem?page=1&tp=" + type + "&kw=" + keyWord;
}

function inputChange(input, page_max) {
    var toPage = $(input).val();
    if (toPage >= 1 && toPage <= page_max) {
        $("input[type=number]").val(toPage);
    } else {
        alert("需要输入1 ~ " + page_max + "之间的值");
    }
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