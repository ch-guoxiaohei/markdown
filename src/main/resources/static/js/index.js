var pageQuery = $("#md-hidden-page");
var pageTotal = $("#md-hidden-total-page");
var pageBox = $(".md-index-content-folder-page");
var articleMain = $('#md-article-ul');
var pageSize = 6;

function loadArticle() {
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
        pageBox.show();
        var overview = item.overview;
        var title = item.title;
        var id = item.id;
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
            "<a href='/content.html?articleId=" + id
            + "' class='md-read-more'> read more</a>" +
            "</div></li>";
        articleMain.append(content);
      });
    }
  });
}

function bindClick() {
  $("#md-search-span").bind("click", function (event) {
    loadArticle();
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
  loadArticle();
}

function prePage() {
  var currentPage = pageQuery.val();
  if (currentPage <= 0) {
    alert("Had reach first page");
    return;
  }
  pageQuery.val(parseInt(currentPage) - 1);
  loadArticle();
}