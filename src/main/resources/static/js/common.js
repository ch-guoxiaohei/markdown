var addPost = "/api/v1/article";
var category = "/api/v1/category";
var articles = "/api/v1/article";

function getQueryParam(key) {
  if (!key) {
    return false;
  }

  var value = '';
  var paramStr = window.location.search ? window.location.search.substr(1) : '';

  if (paramStr) {
    paramStr.split('&').forEach(function (param) {
      var arr = param.split('=');
      if (arr[0] === key) {
        value = arr[1];
      }
    });
  }

  return value;
}


function loadCategory() {
  $.ajax({
    url: category,
    type: "GET",
    async: false,
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (success) {
      if (success.code !== 200) {
        alert("load category failed!!!");
        return;
      }
      var selectCategory = $("#md-id-select");
      selectCategory.empty();
      selectCategory.append("<option value='default'>default</option>");
      success.data.forEach(function (item) {
        $("#md-id-select").append(
            "<option value='" + item.id + "'>" + item.name + "</option>")
      });
    }
  });
}