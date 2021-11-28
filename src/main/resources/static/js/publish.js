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

function submit(addPost) {
  if (!validSubmit(getData())) {
    alert("please add title or overview for article!");
    return;
  }
  $.ajax({
    url: addPost,
    type: "POST",
    data: JSON.stringify(getData()),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (success) {
      if (success.code !== 200) {
        alert("Save Article Failed");
      }
      alert("Save Article Successful");
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