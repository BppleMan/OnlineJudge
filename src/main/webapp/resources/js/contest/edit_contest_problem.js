/**
 * Created by BppleMan on 2017/11/24.
 */
var basePath;
var selectedProblemIds = new Array();
// 这是一个侧边栏中ul里的li的map集合
var sidebarLi = new Object();

//设置已选题目
//包括初始化selectedProblems和sidebarLi
function setSelectedProblems() {
    var ul = $("ul.nav.sidebar-nav");
    var lis = $(ul).children();
    for (var i = 1; i < lis.length; i++) {
        var problemId = $(lis[i]).val();
        sidebarLi[problemId] = lis[i];
        selectedProblemIds.push(problemId);
    }
}

// 用于开/关边栏
function changeSidebar(switcher) {
    if ($(switcher).is(":checked")) {
        $('#wrapper').toggleClass('toggled',true);
        $('#wrapper-right').toggleClass('toggled',true);
    } else {
        $('#wrapper').toggleClass('toggled',false);
        $('#wrapper-right').toggleClass('toggled',false);
    }
};

function addOneSelectProblem(problemId, problemTitle) {
    selectedProblemIds.push(parseInt(problemId));
    checkSelected(problemId, true);
    addToSidebarList(problemId, problemTitle);
}

function removeOneSelectProblem(problemId) {
    selectedProblemIds.splice($.inArray(problemId, selectedProblemIds),1);
    checkSelected(problemId, false);
    removeFromSidebarList(problemId);
}

// 点击checkbox的时候
function clickCheckbox(checker, problemId, problemTitle) {
    // 如果checkbox被选中
    // 1、将id加入到map当中
    // 2、检查是否有别的相同的id
    // 3、将该problem加入到侧边栏中
    if ($(checker).prop("checked")) {
        addOneSelectProblem(problemId, problemTitle);
    } else {
        removeOneSelectProblem(problemId);
    }
}

//找到所有相同problem对应的checkbox，对其统一进行反选
function checkSelected(problemId, flag) {
    $("table").find("input[type='checkbox']").each(function () {
        if ($(this).val() == problemId) {
            $(this).prop("checked", flag);
        }
    });
}

// 添加一个li到sidebarList
function addToSidebarList(problemId, problemTitle) {
    var ul = $("#sidebar-wrapper ul");
    var li = $("<li></li>").text((selectedProblemIds.length + ":") + problemTitle);
    var div = $("<div class='sidebar-li-operator'></div>");
    var a = $("<a onclick=removeOneSelectProblem(" + problemId + ")></a>");
    var span_trash = $("<span class='fui-trash'></span>").text("删除");
    a.append(span_trash);
    div.append(a);
    li.append(div);
    ul.append(li);
    sidebarLi[problemId] = li;
}

// 删除一个li从sidebarList
function removeFromSidebarList(problemId) {
    var li = sidebarLi[problemId];
    $(li).remove();
    delete sidebarLi[problemId];
}

function emptySideBarList() {
    var tempIds = selectedProblemIds.concat();
    for (var i = 0; i < tempIds.length; i++) {
        var obj = tempIds[i];
        removeOneSelectProblem(obj);
    }
}

function createFormAndSubmit() {
    var action = basePath + "/contest/submit_contest_problem?page=1&note=teacher&tp=&kw=";
    var selectedProblemForm = $("<form action='"+action+"' method='post' style='display: none'></form>");
    var contestIdHidden = $("#contestId").clone();
    var tokenHidden = $("#token").clone();
    for (var i = 0; i < selectedProblemIds.length; i++) {
        var problemId = selectedProblemIds[i];
        var selected = $("<input type='hidden' name='selected' value='"+problemId+"'>");
        selectedProblemForm.append(selected);
    }
    selectedProblemForm.append(contestIdHidden);
    selectedProblemForm.append(tokenHidden);
    $("body").append(selectedProblemForm);
    selectedProblemForm.submit();
}

var labels;
var smartProblems;

function initSmartProblems() {
    smartProblems = new Object({"tr_1":new Object()});
}

$(function () {
    $("select").select2();
    $('[data-toggle=tooltip]').tooltip();
});

function listenSelect(select) {
    var name = select.name;
    var value = $(select).val();
    var tr = $(select).parent().parent();
    var trId = tr.attr("id");
    var select1 = tr.find("select").eq(0);
    var select2 = tr.find("select").eq(1);
    if (name == "type") {
        select2.empty();
        select2.append("<option></option>");
        for (var i = 0; i < labels[value].length; i++) {
            var val = labels[value][i];
            select2.append("<option value="+val+">"+val+"</option>")
        }
    }
    if (name == "level") {
        var smartProblem = smartProblems[trId];
        smartProblem.startLevel = value - 25;
        smartProblem.endLevel = value;
    } else {
        var smartProblem = smartProblems[trId];
        smartProblem[name] = value;
    }
}

function listenNumberInput(number) {
    var name = number.name;
    var value = number.value;
    var tr = $(number).parent().parent();
    var trId = tr.attr("id");
    var smartProblem = smartProblems[trId];
    smartProblem[name] = value;
}

var templateTR;
var i = 2;

function addRow(button) {
    var td = $(button).parent().parent();
    var tbody = $("#tr_1").parent();
    var tr = templateTR.clone();
    var trId = "tr_" + i;
    tr.attr("id", trId);
    tbody.append(tr);
    tr.find("select").select2();
    tr.find("a").eq(0).text(i);
    smartProblems[trId] = new Object();
    i++;
}

function removeRow(button) {
    var tr = $(button).parent().parent().parent();
    var trId = tr.attr("id");
    tr.remove();
    delete smartProblems[trId];
}

function generateProblem() {
    var params = new Array();
    var canGenerate = true;
    for (var key in smartProblems) {
        var smartProblem = smartProblems[key];
        smartProblem.trId = key;
        var number = key.split("_")[1];
        if (smartProblem.type == null) {
            canGenerate = false;
            bootbox.alert("第"+number+"行的分类未选择");
        } else if (smartProblem.label == null) {
            canGenerate = false;
            bootbox.alert("第"+number+"行的题型未选择");
        } else if (smartProblem.startLevel == null || smartProblem.endLevel == null) {
            canGenerate = false;
            bootbox.alert("第"+number+"行的难度未选择");
        } else if (smartProblem.problemCount == null) {
            canGenerate = false;
            bootbox.alert("第"+number+"行的数量未填充");
        }
        params.push(smartProblem);
    }
    if (canGenerate) {
        $.ajax({
            url : basePath + "/smartProblem/generateProblem",
            type : "POST",
            dataType : "JSON",
            contentType : "application/json",
            data : JSON.stringify(smartProblems)
        }).done(function (data) {
            emptySideBarList();
            for (var key in data) {
                for (var i = 0; i < data[key].length; i++) {
                    var problem = data[key][i];
                    addOneSelectProblem(problem.id, problem.title);
                }
            }
        })
    }
}