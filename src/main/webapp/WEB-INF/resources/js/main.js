function changeLanguage(language){
     document.cookie="my-locale-cookie="+language+"; path=/;";
}


//var button1 = document.getElementById('button1');
var div1 = document.getElementById('step1');

//var button2 = document.getElementById('button2');
var div2 = document.getElementById('step2');

// button1.onclick = function doFunction() {
//     div1.style.display = 'none';
//     div2.style.display = 'block';
// };

$(document).ready(function(){
    $("#next1").click(function(){
        $("#step1").hide();
        $("#step2").show();
        $("#step3").hide();
    });
    $("#next2").click(function(){
        $("#step1").hide();
        $("#step2").hide();
        $("#step3").show();
    });
    $("#return2").click(function(){
        $("#step1").show();
        $("#step2").hide();
        $("#step3").hide();
    });
    $("#return3").click(function(){
        $("#step1").hide();
        $("#step2").show();
        $("#step3").hide();
    });
});





