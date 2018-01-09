var basePath;

$(function () {
    //初始化des_editor
    var editor = ace.edit("inputData");
    $('#inputData').height(400);
    initEditor(editor);
    //初始化input_editor
    var editor = ace.edit("outputData");
    $('#outputData').height(400);
    initEditor(editor);

    $("#type").change(function () {
        var type = $(this).val();
        var select = $("#label");
        select.empty()
        select.append($("<option></option>"))
        for (var i = 0; i < labels[type].length; i++) {
            var label = labels[type][i];
            var option = $("<option></option>").text(label);
            option.val(label);
            select.append(option);
        }
        select.select2();
    })
})

function initEditor(editor) {
    //设置风格和语言（更多风格和语言，请到github上相应目录查看）
    var theme = "textmate";
    var language = "plain_text";
    editor.setTheme("ace/theme/" + theme);
    editor.session.setMode("ace/mode/" + language);

//字体大小
    editor.setFontSize(18);

//设置只读（true时只读，用于展示代码）
    editor.setReadOnly(false);

//自动换行,设置为off关闭
    editor.setOption("wrap", "free")

//启用提示菜单
    ace.require("ace/ext/language_tools");
    editor.setOptions({
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: true
    });
}

function setTextValue(problemId) {
    var url = basePath + "/bg/problem/getProblemData";
    $.post(url, {"problemId" : problemId}, function (data) {
        fillAce(data)
    }, "JSON")
    var url = basePath + "/bg/problem/getProblem";
    $.post(url, {"problemId" : problemId}, function (data) {
        $("input[name='problemId']").val(data.id);
        $("input[name='title']").val(data.title);
        $("input[name='author']").val(data.author);
    }, "JSON")
}

function fillAce(problemData) {
    var inputData = ace.edit("inputData");
    var outputData = ace.edit("outputData");
    inputData.setValue(problemData.inputData);
    outputData.setValue(problemData.outputData);
}


function submitProblemData() {
    var inputData = ace.edit("inputData");
    var outputData = ace.edit("outputData");
    var form = $("form");
    var textarea = $("<textarea name='inputData' style='display: none'>").val(inputData.getValue());
    form.append(textarea);
    textarea = $("<textarea name='outputData' style='display: none'>").val(outputData.getValue());
    form.append(textarea);
    return true;
}