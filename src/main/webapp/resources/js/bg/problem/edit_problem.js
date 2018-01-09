$(function () {
    //初始化des_editor
    var editor = ace.edit("des_editor");
    $('#des_editor').height(150);
    initEditor(editor);
    //初始化input_editor
    var editor = ace.edit("input_editor");
    $('#input_editor').height(150);
    initEditor(editor);
    //初始化des_editor
    var editor = ace.edit("output_editor");
    $('#output_editor').height(150);
    initEditor(editor);
    //初始化des_editor
    var editor = ace.edit("sample_input_editor");
    $('#sample_input_editor').height(150);
    initEditor(editor);
    //初始化des_editor
    var editor = ace.edit("sample_output_editor");
    $('#sample_output_editor').height(150);
    initEditor(editor);
    //初始化des_editor
    var editor = ace.edit("hints_editor");
    $('#hints_editor').height(150);
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

var basePath;
var labels;

function setTextValue(problemId) {
    var url = basePath + "/bg/problem/getProblem";
    $.post(url, {"problemId" : problemId}, function (data) {
        $("input[name='title']").val(data.title);
        $("input[name='author']").val(data.author);
        fillAce(data)
        for (var i = 0; i < data.problemLabels.length; i++) {
            var label = data.problemLabels[i];
            $(".tagsinput").tagsinput('add', label.label);
        }
    }, "JSON")
}

function fillAce(problem) {
    var des_editor = ace.edit("des_editor");
    var input_editor = ace.edit("input_editor");
    var output_editor = ace.edit("output_editor");
    var sample_input_editor = ace.edit("sample_input_editor");
    var sample_output_editor = ace.edit("sample_output_editor");
    var hints_editor = ace.edit("hints_editor");
    des_editor.setValue(problem.description);
    input_editor.setValue(problem.input);
    output_editor.setValue(problem.output);
    sample_input_editor.setValue(problem.sampleInput);
    sample_output_editor.setValue(problem.sampleOutput);
    hints_editor.setValue(problem.hints);
}


function submit_new_problem() {
    var des_editor = ace.edit("des_editor");
    var input_editor = ace.edit("input_editor");
    var output_editor = ace.edit("output_editor");
    var sample_input_editor = ace.edit("sample_input_editor");
    var sample_output_editor = ace.edit("sample_output_editor");
    var hints_editor = ace.edit("hints_editor");
    var form = $("form");
    var textarea = $("<textarea name='description' style='display: none'>").val(des_editor.getValue());
    form.append(textarea);
    textarea = $("<textarea name='input' style='display: none'>").val(input_editor.getValue());
    form.append(textarea);
    textarea = $("<textarea name='output' style='display: none'>").val(output_editor.getValue());
    form.append(textarea);
    textarea = $("<textarea name='sampleInput' style='display: none'>").val(sample_input_editor.getValue());
    form.append(textarea);
    textarea = $("<textarea name='sampleOutput' style='display: none'>").val(sample_output_editor.getValue());
    form.append(textarea);
    textarea = $("<textarea name='hints' style='display: none'>").val(hints_editor.getValue());
    form.append(textarea);
    return true;
}

function addLabel() {
    var label = $("#label").val()
    $(".tagsinput").tagsinput('add', label);
}