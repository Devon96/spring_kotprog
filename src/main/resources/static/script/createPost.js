var categori;
function myFunction() {
    var checkBox = document.getElementById("newCategory");
    var newCategoryInput = document.getElementById("newCategoryInput");
    var categories = document.getElementById("categories");
    var categori = document.getElementById("category");
    var categoryTextField = document.getElementById("categoryTextField");
    if (checkBox.checked == true){
        newCategoryInput.style.display = "block";
        categories.style.display = "none";
        categoryTextField.value = "";

    } else {
        newCategoryInput.style.display = "none";
        categories.style.display = "block";
        categori.value = "";
    }
}
$(document).ready(function () {
    categori = document.getElementById("category");
    categori.value = "";
});
