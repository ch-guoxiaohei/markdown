function getData() {
  var markdown = testEditor.getMarkdown();
  var html = testEditor.getHTML();
  var title = $("#md-title").val();
  var id = $("#md-id").val();
  var overview = $("#md-edit-id-overview").val();
  var categoryId = $("#md-id-select option:selected").val();
  return {
    id: id,
    title: title,
    categoryId: categoryId,
    content: html,
    overview: overview,
    originContent: markdown
  };
}

function submit(addPost, articleId) {
  if (!validSubmit(getData())) {
    alert("please add title or overview for article!");
    return;
  }
  $.ajax({
    url: addPost + "/" + articleId,
    type: "POST",
    data: JSON.stringify(getData()),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (success) {
      if (success.code !== 200) {
        alert("Update Article Failed");
      }
      alert("Update Article Successful");
      $("#md-title").val('');
      $("#md-edit-id-overview").val('');
    }
  });
}

function validSubmit(data) {
  if (data.title === "" || data.overview === "") {
    return false;
  }
  return true;
}

function loadArticleById(articleId) {
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
      $("#test-editormd textarea").val(data.originContent);
      $("#md-title").val(data.title);
      $("#md-id").val(data.id);
      $("#md-id-select option").each(function (index, item) {
        if (data.categoryId === $(item).val()) {
          $(item).attr("selected", "true");
        }
      });
      $("#md-edit-id-overview").val(data.overview);
    }
  });
}