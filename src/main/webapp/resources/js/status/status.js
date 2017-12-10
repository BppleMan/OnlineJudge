/**
 * Created by BppleMan on 2017/11/20.
 */
$(document).ready(function () {
    $("ul.pagination").find("a").each(function () {
        if (this.text != "Prev" && this.text != "Next"){
            if (this.href == document.location.href) {
                $(this).parent().addClass("active"); // this.className = 'active';
            }
        }
    });
});

var trs;
var userIds = null;
var userNames = null;
var problemIds = null;
var problemTitles = null;

$(function () {
    trs = $("#tbody-status").children();
});

function changeUserColumn(switcher, contextPath) {
    if (userIds == null) {
        userIds = getIds(1);
    }
    if ($(switcher).prop("checked") == true) {
        $("#th-user-id").text("UserName");
        if (userNames == null) {
            getContent(userIds, contextPath, "/getUsername", 1, "user");
        } else {
            changeToContent(userNames, userIds, 1);
        }
    } else {
        $("#th-user-id").text("User ID");
        changeToId(userIds, 1);
    }
}

function changeProblemColumn(switcher, contextPath) {
    if (problemIds == null) {
        problemIds = getIds(2);
    }
    if ($(switcher).prop("checked") == true) {
        $("#th-problem-id").text("Problem Title");
        if (problemTitles == null) {
            getContent(problemIds, contextPath, "/getProblemTitle", 2, "problem");
        } else {
            changeToContent(problemTitles, problemIds, 2);
        }
    } else {
        $("#th-problem-id").text("Problem ID");
        changeToId(problemIds, 2);
    }
}

function getContent(ids, contextPath, getPath, column, flag) {
    var content = null;
    $.ajax({
        url : contextPath + "/status" + getPath,
        type : "POST",
        contentType : "application/json",
        dataType : "JSON",
        data : JSON.stringify(ids),
        success : function (data) {
            if (flag == "user")
                userNames = data;
            else
                problemTitles = data;
            changeToContent(data, ids, column);
        },
        error : function (data) {
            bootbox.alert("error" + data);
        }
    });
    // return content;
}

function getIds(column) {
    var arr = new Array();
    for (var i = 0; i < trs.length; i++) {
        var tr = $(trs[i]);
        var tds = tr.children();
        var td = tds.eq(column);
        var a = td.children().first();
        var id = a.text();
        arr.push(parseInt(id.trim()));
    }
    return arr;
}

function changeToContent(content, ids, column) {
    for (var i = 0; i < trs.length; i++) {
        var tr = $(trs[i]);
        var tds = tr.children();
        var td = tds.eq(column);
        var a = td.children().first();
        a.text(content[ids[i]]);
    }
}

function changeToId(ids, column) {
    for (var i = 0; i < trs.length; i++) {
        var tr = $(trs[i]);
        var tds = tr.children();
        var td = tds.eq(column);
        var a = td.children().first();
        a.text(ids[i]);
    }
}

function showContest(contextPath, contestId) {
    location.href = contextPath + "/contest/show_contest?contestId=" + contestId;
}