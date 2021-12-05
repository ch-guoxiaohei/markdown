var pageQuery = $("#md-hidden-page");
var pageTotal = $("#md-hidden-total-page");
var pageBox = $(".md-index-content-folder-page");
var articleMain = $('#md-article-ul');
var pageSize = 6;

function bindClick() {
  $("#md-search-span").bind("click", function (event) {
    loadArticleByAdmin();
  });
}

function setPage(totalPage, page) {
  pageTotal.val(totalPage);
  pageQuery.val(page);
}

function nextPage() {
  // page start from 0 . but total is current num.
  var total = pageTotal.val();
  var currentPage = pageQuery.val();
  if (currentPage >= total - 1) {
    alert("Had reach last page");
    return;
  }
  pageQuery.val(parseInt(currentPage) + 1);
  loadArticleByAdmin();
}

function prePage() {
  var currentPage = pageQuery.val();
  if (currentPage <= 0) {
    alert("Had reach first page");
    return;
  }
  pageQuery.val(parseInt(currentPage) - 1);
  loadArticleByAdmin();
}

function loadArticleByAdmin() {
  var categoryId = $('#md-id-select option:selected').val();
  var page = pageQuery.val();

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
      articleMain.empty();
      if (success.data.content.length === 0) {
        articleMain.append("<p class='md-no-content'>No Data At This Time</p>");
        pageBox.hide();
        return;
      }
      setPage(success.data.totalPages, success.data.pageable.pageNumber);
      success.data.content.forEach(function (item) {
        var overview = item.overview;
        var title = item.title;
        var id = item.id;
        var bindId = "delete_" + id;
        var categoryName = "";
        if (categoryMap[item.categoryId] !== undefined) {
          categoryName = "【" + categoryMap[item.categoryId] + "】";
        }
        var content = "<li><div><span class='md-index-content-title'>" +
            "<a target='_blank' href='/content.html?articleId=" + id + "'>" + categoryName
            + title + "</a>"
            + "</span>" +
            "<div class='md-index-content-overview' ><p>" + overview + "【"
            + item.createTime + "】"
            + "</p></div>" +
            "<a href='/edit.html?articleId=" + id
            + "' class='md-edit'> edit</a>" +
            "<a class='md-delete' href='javascript:void(0)' id='" + bindId
            + "'>delete</a>" +
            "</div></li>";
        articleMain.append(content);
        $("#" + bindId).bind('click', function () {
          deleteArticle(id);
        });
      });
    }
  });
}

function deleteArticle(id) {
  var status = confirm("do you delete this article？");
  if (status) {
    console.log("delete " + id);
    var url = articleDel + "/" + id;
    $.ajax({
      url: url,
      type: "GET",
      async: false,
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function (success) {
        if (success.code !== 200) {
          alert("Delete article failed!!!");
          return;
        }
        var data = success.data;
        if (data === "") {
          alert("This Article was not exist!");
          return;
        }
        loadArticleByAdmin();
      }
    });
  }
}