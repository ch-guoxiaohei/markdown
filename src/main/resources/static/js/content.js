function getArticle(articleId) {
  var url = articles + "/" + articleId;
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
      var data = success.data;
      $("#md-content-title").html(data.title);
      $("#md-content-overview").html(data.overview);
      $("#md-content-html").html(data.content);
    }
  });
}