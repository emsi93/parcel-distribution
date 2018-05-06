function changeLanguage(language){
     document.cookie="my-locale-cookie="+language+"; path=/;";
}

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






