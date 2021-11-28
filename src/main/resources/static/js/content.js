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
      $("#editormd-view textarea").val(data.originContent);
      var editormdView = editormd.markdownToHTML("editormd-view", {
        htmlDecode      : "style,script,iframe",  // you can filter tags decode
        emoji           : true,
        taskList        : true,
        tex             : true,  // 默认不解析
        flowChart       : true,  // 默认不解析
        sequenceDiagram : true,  // 默认不解析
      });
    }
  });
}