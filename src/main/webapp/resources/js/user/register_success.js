/**
 * Created by BppleMan on 2017/11/15.
 */
var time = 3000;
var length = 0;
var interval;
function startAnimation(contextPath) {
    var code = "animation('" + contextPath +"')";
    interval = setInterval(code,10);
}

function animation(contextPath) {
    console.log(contextPath);
    time -= 10;
    length = (3000 - time) / 15;
    if(time==0){
        clearInterval(interval);
        location.href = contextPath + "/home";
    }
    $("#progressbar").width(length + "%");
}