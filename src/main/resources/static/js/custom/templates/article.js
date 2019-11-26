function submitComment(message) {
    console.dir(message);
    console.dir(message.value);

    let url = "http://localhost:8080/comments";
    let body = {
        "article_id": article['id'],
        "user_id": 1,
        "content": message.value
    };
    putRequest(url, body, (data) => {
        console.dir(data);
        // comment dom 삭제 후 다시 갱신 (전체를 다 받아서 리턴하도록 해야겠네)
        let comments = data['comments'];
        let commentArea = document.getElementById("comment-area-id");
        while (commentArea.childElementCount > 0) {
            commentArea.removeChild(commentArea.firstChild);
            // 무한루프 조심
        }
        appendComments(comments, commentArea);
        window.location.reload();
    })
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

function appendComments(comments, commentArea) {
    for (let i = 0; i < comments.length; i++) {
        let comment = comments[i];
        let commentElement = createCommentElement(comment);
        commentArea.append(commentElement);
    }
}


function createCommentElement(comment) {

    let commentStr = '<li class="single_comment_area">\n' +
        '                            <div class="comment-content d-flex">\n' +
        '                                <div class="comment-author">\n' +
        '                                    <img src="img/bg-img/32.jpg" alt="author">\n' +
        '                                </div>\n' +
        '                                <div class="comment-meta">\n' +
        '                                    <a href="#" class="post-date">27 Aug 2019</a>\n' +
        '                                    <h5>' + comment.author.name + '</h5>\n' +
        '                                    <p>' + comment.content + '</p>\n' +
        '                                    <a href="#" class="like">Like</a>\n' +
        '                                    <a href="#" class="reply">Reply</a>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </li>';
    return createElementFromStr(commentStr);

}