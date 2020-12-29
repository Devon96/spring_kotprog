$(document).ready(function () {
    var date = document.getElementById("birthday").getAttribute("value");
    date = date.split(" ")[0];
    document.getElementById("birthday").setAttribute("value", date);

    myFunction();
});


function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah')
                .attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function myFunction() {
    var checkBox = document.getElementById("check");
    var newPass = document.getElementById("newPass");
    var oldPass = document.getElementById("oldPass");
    var newPassTextField = document.getElementById("newPassword");
    var oldPassTextField = document.getElementById("oldPassword");
    if (checkBox.checked === false){
        newPass.style.display = "none";
        oldPass.style.display = "none";
        newPassTextField.value = "asdfgh";
        oldPassTextField.value = "asdfgh";

    } else {
        newPass.style.display = "block";
        oldPass.style.display = "block";
        newPassTextField.value = "";
        oldPassTextField.value = "";
    }
}