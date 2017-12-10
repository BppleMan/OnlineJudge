/**
 * Created by BppleMan on 2017/11/24.
 */
var loadedTbody = new Array();
var selectedProblems = new Array();

function setSelectedProblems(problems) {
    for (var i = 0; i < problems.length; i++) {
        selectedProblems.push(problems[i].id);
        addToSidebarList(problems[i].id, problems[i].title);
    }
}

function loadSeletedProblem(contextPath, contestId) {
    $.ajax({
        url : contextPath + "/contest/load_selected_problem",
        type : "POST",
        dataType : "JSON",
        data : {"contestId":contestId},
        success : function (data) {
            setSelectedProblems(data);
        }
    });
}

function changeSidebar(switcher) {
    if ($(switcher).is(":checked")) {
        $('#wrapper').toggleClass('toggled',true);
        $('#wrapper-right').toggleClass('toggled',true);
    } else {
        $('#wrapper').toggleClass('toggled',false);
        $('#wrapper-right').toggleClass('toggled',false);
    }
};

function canSubmit() {
    // console.log(selectedProblems.hasOwnProperty());
    return true;
}

//找到所有相同problem对应的checkbox，对其统一进行反选
function checkSelected(problemId, flag) {
    $("table").find("input[type='checkbox']").each(function () {
        if ($(this).val() == problemId) {
            $(this).prop("checked", flag);
        }
    });
}

// 手风琴效果加载problems
function loadProblems(i, j, type, label, contextPath) {
    var needLoad = false;
    if (loadedTbody.length <= i) {
        needLoad = true;
    } else if (loadedTbody[i].length <= j) {
        needLoad = true;
    }
    if (needLoad == true) {
        var param = new Object();
        param.type = type;
        param.label = label;
        $.ajax({
            url : contextPath + "/contest/load_problem",
            type : "POST",
            dataType : "json",
            data : param,
            success : function (data) {
                updateTable(i, j, data);
            },
            error : function (data) {
                alert("error");
            }
        });
    }
}

// 更新手风琴中的table视图
function updateTable(i, j, problems) {
    var temp = "#problems-table-" + i + "-" + j;
    var table = $(temp);
    var tbody = $("<tbody></tbody>");
    for (var ii = 0; ii < problems.length; ii++) {
        var tr = $("<tr></tr>");
        var id = $("<td class='text-center'></td>").text(problems[ii].id);
        var title = $("<td class='text-center'></td>").text(problems[ii].title);
        var checkbox_td = $("<td class='text-center'></td>");
        var checkbox = $("<input type='checkbox' name='selected' onclick='clickCheckbox($(this), $(this).val(), $(this).text())' value="+problems[ii].id+">").text(problems[ii].title);
        if ($.inArray(problems[ii].id, selectedProblems) != -1) {
            checkbox.prop("checked", true);
        }
        checkbox_td.append(checkbox);
        tr.append(id);
        tr.append(title);
        tr.append(checkbox_td);
        tbody.append(tr);
    }
    table.append(tbody);
    if (loadedTbody.length <= i) {
        temp = new Array();
        temp.push(table);
        loadedTbody.push(temp);
    } else {
        temp = loadedTbody[i];
        temp.push(table);
    }

}

// 点击checkbox的时候
function clickCheckbox(checker, problemId, problemTitle) {
    // 如果checkbox被选中
    // 1、将id加入到map当中
    // 2、检查是否有别的相同的id
    // 3、将该problem加入到侧边栏中
    if ($(checker).prop("checked")) {
        selectedProblems.push(parseInt(problemId));
        checkSelected(problemId, true);
        addToSidebarList(problemId, problemTitle);
    } else {
        selectedProblems.splice($.inArray(problemId, selectedProblems),1);
        checkSelected(problemId, false);
        removeFromSidebarList(problemId);
    }
}

// 这是一个侧边栏中ul里的li的map集合
var sidebarLi = new Object();

// 添加一个li到sidebarList
function addToSidebarList(problemId, problemTitle) {
    var ul = $("#sidebar-wrapper ul");
    var li = $("<li></li>");
    var a = $("<a onclick=removeFromSidebarList(" + problemId + ")></a>").text(problemTitle);
    var span = $("<span class='fui-trash' style='float : right'></span>");
    a.append(span);
    li.append(a);
    ul.append(li);
    sidebarLi[problemId] = li;
}

// 删除一个li从sidebarList
function removeFromSidebarList(problemId) {
    var li = sidebarLi[problemId];
    $(li).remove();
    delete sidebarLi[problemId];
}