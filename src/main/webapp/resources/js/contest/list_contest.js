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
    var url = new String(contextPath + "/contest/contest_list/");
    url += $("input[name='pageInputNumber']").val();
    window.location.href = url;
}

var contextPath_global;
var search_result_contests;
var current_page_number;
var page_count;
var countPerPage = 10;

function search(contextPath) {
    contextPath_global = contextPath;
    var keyWord = $("#keyWord").val();
    $.ajax({
        url : contextPath_global + "/contest/search",
        type : "POST",
        // contentType : "application/json",
        dataType : "json",
        data : {"keyWord":keyWord},
        // data : JSON.stringify(param),
        success : function(data){
            search_result_contests = data;
            current_page_number = 1;
            updatePageNumber();
        }
    });
}

function updatePageNumber() {
    $("li").remove(".page_number_li");
    var pageCount = search_result_contests.length / countPerPage;
    var head_pre = $("#page_number_head_pre");
    var foot_pre = $("#page_number_foot_pre");
    for (var i = 0; i < pageCount; i++) {
        var a = $("<a onclick='changePage(" + (i + 1) + ")'></a>").text(i + 1);
        var li = $("<li class='page_number_li'></li>");
        li.append(a);
        if (i == 0)
            $(li).addClass("active");
        var li_clone = li.clone();
        head_pre.after(li);
        foot_pre.after(li_clone);
        head_pre = li
        foot_pre = li_clone;
        page_count = i + 1;
    }
    $("input[name='pageInputNumber']").attr('oninput', "inputChange(this, "+page_count+")");
    updateGoButton();
    updatePreAndNext();
    updateTable(current_page_number);
}

function updateTable(pageNumber) {
    $("#problems_table_body").empty();
    var color = new Array(["success"],["warning"],["info"]);
    for (var i = (pageNumber-1) * countPerPage; i < pageNumber * countPerPage; i++) {
        if (search_result_contests[i] == null)
            break;
        var tr = $("<tr class="+color[i%3]+"></tr>")
        var id = $("<td></td>").text(search_result_contests[i].id);
        var title = $("<td></td>");
        var a = $("<a href="+contextPath_global+"/contest?contestId="+search_result_contests[i].id+">").text(search_result_contests[i].name);
        title.append(a);
        var startTime = $("<td></td>").text(search_result_contests[i].startTime);
        var endTime = $("<td></td>").text(search_result_contests[i].endTime);
        var duration = $("<td></td>").text(search_result_contests[i].duration);
        var status = $("<td></td>").text(search_result_contests[i].status);
        var type = $("<td></td>").text(search_result_contests[i].type);
        var author = $("<td></td>").text(search_result_contests[i].author);
        tr.append(id);
        tr.append(title);
        tr.append(startTime);
        tr.append(endTime);
        tr.append(duration);
        tr.append(status);
        tr.append(type);
        tr.append(author);
        $("#problems_table_body").append(tr);
    }
}

function changePage(pageNumber) {
    $("ul.pagination").find("a").each(function () {
        if (this.text != "Prev" && this.text != "Next"){
            if (this.text == pageNumber) {
                $(this).parent().addClass("active");
            } else {
                $(this).parent().removeClass("active");
            }
        } else {
            $(this).parent().removeClass("active");
        }
    });
    current_page_number = Number(pageNumber);
    updateTable(pageNumber);
    $("ul.pagination").find("a").each(function () {
        if (this.text != "Prev" && this.text != "Next"){
            if (this.text == pageNumber) {
                $(this).parent().addClass("active");
            } else {
                $(this).parent().removeClass("active");
            }
        } else {
            $(this).parent().removeClass("active");
        }
    });
}

function prePage() {
    current_page_number = (current_page_number == 1 ? 1 : current_page_number - 1);
    changePage(current_page_number);
}

function nextPage() {
    current_page_number = (current_page_number >= page_count ? page_count : current_page_number + 1);
    changePage(current_page_number);
}

function updatePreAndNext() {
    var head_pre = $("#page_number_head_pre");
    var foot_pre = $("#page_number_foot_pre");
    var head_next = $("#page_number_head_next");
    var foot_next = $("#page_number_foot_next");
    $(head_pre).children().removeAttr("href");
    $(head_pre).children().attr("onclick","prePage()");
    $(foot_pre).children().removeAttr("href");
    $(foot_pre).children().attr("onclick","prePage()");
    $(head_next).children().removeAttr("href");
    $(head_next).children().attr("onclick","nextPage()");
    $(foot_next).children().removeAttr("href");
    $(foot_next).children().attr("onclick","nextPage()");
}

function updateGoButton() {
    $("button.btn.btn-default.go-button").attr("onclick", "goPage()");
}

function goPage() {
    current_page_number = Number($("input[type=number]").val());
    $("input[type=number]").val("");
    changePage(current_page_number);
}

function inputChange(input, page_max) {
    var toPage = $(input).val();
    if (toPage >= 1 && toPage <= page_max) {
        $("input[type=number]").val(toPage);
    } else {
        alert("需要输入1 ~ " + page_max + "之间的值");
    }
}

function showContest(contextPath, contestAuthor, contestType, contestId, username) {
    if (username == null || username == "") {
        bootbox.alert("请先登录！");
    } else if (contestAuthor == username) {
        location.href = contextPath + "/contest/show_contest?contestId=" + contestId;
    } else if (contestType == "public") {
        location.href = contextPath + "/contest/show_contest?contestId=" + contestId;
    } else if (contestType == "password") {
        showConfirm(contextPath, contestId);
    } else if (contestType == "class") {
        //    ajax get userInfo
    }
}

function showConfirm(contextPath, contestId) {
    var input_group = $("<div class='input-group'></div>");
    var input_group_addon = $("<span class='input-group-addon'>密码</span>");
    var input = $("<input type='password' class='form-control' />");
    input_group.append(input_group_addon);
    input_group.append(input);
    bootbox.dialog({
        title : "修改密码",
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
                        url : contextPath + "/contest/check_contest_password",
                        type : "POST",
                        dataType : "json",
                        data : param,
                        success : function (result) {
                            if (result == true){
                                location.href = contextPath + "/contest/show_contest?contestId=" + contestId;
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