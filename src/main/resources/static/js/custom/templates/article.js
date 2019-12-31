function main(article, user) {

    let sectionElement = createBreadcrumbSection(article);
    document.getElementById("breadcrumb-area-id").append(sectionElement);

    let content = "<div>" + article['content'] + "</div>";
    let contentElement = createElementFromStr(content);
    document.getElementById("blog-details-text-id").append(contentElement);
    let tags = article['tags'];
    let tagContainer = document.getElementById("popular-tags");
    for (let i = 0; tags !== undefined && (tags !== null) && (i < tags.length); i++) {
        let tagElement = createElementFromStr('<li><a class="btn badges-btn" href="#">' + tags[i] + '</a></li>');
        tagContainer.append(tagElement)
    }
    // document.getElementById("previous-article").appendChild(createOtherArticle(article['prev_article'], "prev"));
    // document.getElementById("next-article").appendChild(createOtherArticle(article['next_article'], "next"));
    appendFiles(article);

    // let recommendElement = createRecommendElement(article);
    // document.getElementById("related-news-id").append(recommendElement);

    let comments = article['comments'];
    let commentSection = document.getElementById("comment-area-id");
    let commentTitle = commentSection.firstElementChild;
    let commentsArea = commentSection.children[1];
    let commentSize = comments.length;
    commentTitle.textContent = commentSize + " Comments";
    appendComments(comments, commentsArea);
}

function editArticle () {
    swal({
        title: "수정하시겠습니까?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willEdit) => {
            if (willEdit) {
                let id = getParameter("id");
                window.location.href = "/writing?id="+ id;
            } 
        });
}

function swalIsDelete(url) {
    swal({
        title: "삭제하시겠습니까?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                deleteRequest(url, function (data) {
                    if(data === true)
                        window.location.href = "/board";
                    else
                        swal("삭제에 실패했습니다.", "", "warning")

                });
            } else {

            }
        });

}

function deleteArticle() {
    let url = "/articles?id=" + article['id'];
    swalIsDelete(url)
}

function submitComment(message) {
    console.dir(message);
    console.dir(message.value);

    let url = "/comments";
    let body = {
        "article_id": article['id'],
        "username": user,
        "content": message.value
    };
    putRequest(url, body, (data) => {
        console.dir(data);
        // comment dom 삭제 후 다시 갱신 (전체를 다 받아서 리턴하도록 해야겠네)
        let comments = data['comments'];
        let commentSection = document.getElementById("comment-area-id");

        refreshComments(comments, commentSection);
        appendComments(comments, commentSection.children[1]);
    })
}

function refreshComments(comments, commentSection) {
    let commentsArea = commentSection.children[1];
    while (commentsArea.childElementCount > 0) {
        commentsArea.removeChild(commentsArea.firstChild);
        // 무한루프 조심
    }
    let commentTitle = commentSection.firstElementChild;
    commentTitle.textContent = comments.length + " Comments";
    document.getElementById("postComments").textContent = comments.length + " Comments";
}

function appendFiles(article) {
    let files = article['files'];
    for (let i = 0; i < files.length; i++) {
        let file = createElementFromStr("<li><a href='"+ files[i].file_url + "'> <i class=\"fa fa-save mr-2\"></i>" + files[i].filename + "</a> </li>")
        document.getElementById("file-list").firstElementChild.append(file)
    }
}

function appendComments(comments, commentArea) {
    for (let i = 0; i < comments.length; i++) {
        let comment = comments[i];

        let commentElement = createCommentElement(comment);
        commentArea.append(commentElement);
    }
}


function createCommentElement(comment) {

    let commentStr = '<li class="single_comment_area">\n' +
        '                            <div class="comment-content d-flex">\n';
    for (let i = 0; i < comment['depth']; i++) {
        commentStr += '                                <div class="comment-author"></div>\n';
    }
    let profile = comment.author.profile;
    if (profile === null || profile === "null") {
        profile = "img/bg-img/52.jpg"
    }
    commentStr +=
        '                                <div class="comment-author">\n' +
        '                                    <img src="' + profile + '" alt="author">\n' +
        '                                </div>\n' +
        '                                <div class="comment-meta">\n' +
        '                                    <a href="#" class="post-date">27 Aug 2019</a>\n' +
        '                                    <h5>' + comment.author.username + '</h5>\n' +
        '                                    <p>' + comment.content + '</p>\n' +
        '                                    <a href="#" class="like">Like</a>\n' +
        '                                    <a href="#" class="reply">Reply</a>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </li>';
    return createElementFromStr(commentStr);

}

function convertDate(date) {
    return date
}

function createRecommendElement(article) {
    let subject = article.subject;
    let category = article.category;
    let updatedAt = convertDate(article['updated_at']);

    let commentSize = article['comments'].length;
    if (commentSize === 0) {
        commentSize = "No"
    }
    let url = "/article?id=" + article['id'];
    let recommendStr = '<div class="col-12 col-sm-6 col-lg-3">\n' +
        '                <div class="single-post-area">\n' +
        '                    <!-- Post Thumbnail -->\n' +
        '                    <a href="#" class="post-thumbnail"><img src="img/bg-img/52.jpg" alt=""></a>\n' +
        '                    <!-- Post Catagory -->\n' +
        '                    <a href="#" class="btn post-catagory">' + category + '</a>\n' +
        '                    <!-- Post Conetent -->\n' +
        '                    <div class="post-content">\n' +
        '                        <div class="post-meta">\n' +
        '                            <a href="#">' + updatedAt + '</a>\n' +
        '                            <a href="#">' + commentSize + ' Comment</a>\n' +
        '                        </div>\n' +
        '                        <a href="' + url + '" class="post-title">' + subject + '</a>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>';

    let recommendElement = createElementFromStr(recommendStr);

    return recommendElement;
}


function createCommentForm() {
    let commentFormStr = ' <form action="#" method="post">\n' +
        '                        <div class="row">\n' +
        '                            <div class="col-12">\n' +
        '                                <textarea name="message" class="message form-control mb-30" placeholder="Messages"></textarea>\n' +
        '                            </div>\n' +
        '                            <div class="col-12">\n' +
        '                                <button type="submit" class="btn alime-btn btn-2 mt-15">Send Messages</button>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                    </form>';
    let commentForm = createElementFromStr(commentFormStr);
    let messageTextarea = commentForm.getElementsByClassName("message-name")[0];
    return {"form-section": commentForm, "message": messageTextarea};
}
