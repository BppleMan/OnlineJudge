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