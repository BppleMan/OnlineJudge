/**
 * Created by BppleMan on 2017/11/13.
 */

$(document).ready(function () {
    $("ul.pagination").find("a").each(function () {
        if (this.text != "Prev" && this.text != "Next"){
            if (this.href == document.location.href) {
                $(this).parent().addClass('active'); // this.className = 'active';
            }
        }
    });
});

function jumpToPage(contextPath) {
    var url = new String(contextPath + "/problem?page=");
    url += $("input[name='pageInputNumber']").val();
    window.location.href = url;
}

function searchProblem(contextPath) {
    console.log($("#type").val());
    var type = $("#type").val();
    var keyWord = $("input[name='keyWord']").val();
    if (keyWord == null || keyWord.trim() == "")
        return;
    location.href = contextPath + "/problem/list_problem?page=1&tp=" + type + "&kw=" + keyWord;
}

function selectType(type, text, contextPath) {
    $("#type").val(type);
    $("#type-button").html(text + " <span class='caret'></span>");
    if (type != "label") {
        $(".search-keyword").html("<div class='input-group'>\n" +
            "<input id='keyWord' name='keyWord' type='text' class='form-control'>\n" +
            "<span class='input-group-btn'><button class='btn btn-default' type='button' onclick='searchProblem(\""+contextPath+"\")'>搜索</button></span>\n" +
            "</div>");
    } else {
        $(".search-keyword").html("<div class='tagsinput-primary'>\n" +
            "<input id='keyWord' name='keyWord' class='tagsinput' data-role='tagsinput'>\n" +
            "</div>\n" +
            "<span class='help-block'>每输入一个标签按下回车键或英文半角符逗号以确定</span>\n");
        $(".tagsinput").tagsinput();
        var button = $("<button class='btn btn-default' type='button' onclick='searchProblem(\""+contextPath+"\")'></button>").text("搜索");
        var div = $("<div class='<div class=\"input-group\">'></div>");
        div.append(button);
        $(".search-keyword").append(div);
    }
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