/**
 * Created by BppleMan on 2017/10/31.
 */
function buttonGroup(btn) {
    $('.btn-group').children().each(function () {
        if (this == btn) {
            $(this).addClass("active"); // this.className = 'active';
            setEditorMode(this.name);
        } else {
            $(this).removeClass("active");
        }
    });
}

function submit_code(isLogin, path, token, problemId, contestId) {
    if (!isLogin) {
        alert("请先登录!");
    } else {
        var editor = ace.edit("editor");
        var form = $("<form method='post'></form>");
        form.attr("action", path);
        var code = $("<textarea name='code.codeValue' style='display: none'></textarea>").text(editor.getValue());
        form.append(code);

        var language = $("<input type='hidden' name='code.language'>").val($(".btn.btn-default.active").val());
        form.append(language);

        var token = $("<input type='hidden' name='token'>").val(token);
        form.append(token);

        var problemId = $("<input type='hidden' name='idParam.problemId'>").val(problemId);
        form.append(problemId);

        if (contestId != undefined && contestId != null) {
            var contestId = $("<input type='hidden' name='idParam.contestId'>").val(contestId);
            form.append(contestId);
        }
        $(document.body).append(form);
        form.submit();
    }
}