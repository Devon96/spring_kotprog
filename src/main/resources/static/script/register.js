

$(document).ready(function () {
    console.log("lefutasasasasas");
    $("#confirm_password").on("input", function () {
        validate();
    });
    $("#password").on("input", function () {
        validate();
    });

});


function validate() {
    if($("#confirm_password").val() !== $("#password").val()){
        $("#submit").prop('disabled', true);
    }else{
        $("#submit").prop('disabled', false);
    }
}
