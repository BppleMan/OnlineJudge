$(document).ready(function () {
    //初始化对象
    editor = ace.edit("editor");
    $('#editor').height(600);
//设置风格和语言（更多风格和语言，请到github上相应目录查看）
    theme = "monokai";
    language = "c_cpp";
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
})

function setEditorMode(mode) {
    //初始化对象
    editor = ace.edit("editor");
    editor.session.setMode("ace/mode/" + mode);
}