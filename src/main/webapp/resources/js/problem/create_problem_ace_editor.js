$(document).ready(function () {
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

function setEditorMode(mode) {
    //初始化对象
    editor = ace.edit("editor");
    editor.session.setMode("ace/mode/" + mode);
}