var addPost = "/api/v1/article";
var category = "/api/v1/category";
var articles = "/api/v1/article";

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
      var selectCategory =   $("#md-id-select");
      selectCategory.empty();
      selectCategory.append("<option value='default'>default</option>");
      success.data.forEach(function (item) {
        $("#md-id-select").append(
            "<option value='" + item.id + "'>" + item.name + "</option>")
      });
    }
  });
}

function loadArticle() {
  var categoryId = $('#md-id-select option:selected').val();
  var page = $('#md-hidden-page').val();
  var pageSize = 6;
  var search = $("#md-search").val();
  var url = articles + "?categoryId=" + categoryId + "&page=" + page
      + "&pageSize=" + pageSize + "&search=" + encodeURIComponent(search);
  $.ajax({
    url: url,
    type: "GET",
    async: false,
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (success) {
      if (success.code !== 200) {
        alert("load article failed!!!");
        return;
      }
      $('#md-article-ul').empty();
      success.data.forEach(function (item) {
        var overview = item.overview;
        var title = item.title;
        var id = item.id;
        var content = "<li><div><span class='md-index-content-title'>" + title + "</span>" +
            "<div class='md-index-content-overview' ><p>" + overview + "</p></div>" +
            "<a href='" +  articles + "/" + id  +  "' class='md-read-more'> read more</a>" +
            "</div></li>";
        $('#md-article-ul').append(content);
      });
    }
  });
}

function bindClick() {
  $("#md-search-span").bind("click",function(event){
    loadArticle();
  });
}

