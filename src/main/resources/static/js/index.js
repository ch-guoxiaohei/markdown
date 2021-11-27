var pageQuery = $("#md-hidden-page");
var pageTotal = $("#md-hidden-total-page");
var pageSize = 1;


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
      $('#md-article-ul').empty();
      setPage(success.data.totalPages, success.data.pageable.pageNumber);
      success.data.content.forEach(function (item) {
        var overview = item.overview;
        var title = item.title;
        var id = item.id;
        var content = "<li><div><span class='md-index-content-title'>" + title
            + "</span>" +
            "<div class='md-index-content-overview' ><p>" + overview
            + "</p></div>" +
            "<a href='/content.html?articleId=" + id
            + "' class='md-read-more'> read more</a>" +
            "</div></li>";
        $('#md-article-ul').append(content);
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
  if (currentPage >= total -1 ) {
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