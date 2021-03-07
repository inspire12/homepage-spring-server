function showDateFrom(localDateTime) {
    let d = new Date(localDateTime);
    let year = d.getFullYear();
    let month = (d.getMonth() + 1).toString().padStart(2, 0);
    let day = d.getDate().toString().padStart(2, 0);
    return year + "." + month + "." + day;
}

function createArticleRows(article) {
    let depth = "";
    for (let i = 0; i < article['depth']; i++) {
        depth += "<i class=\"fa fa-share fa-flip-vertical re mr-1\"></i>"
    }
    let trStr = '<tr><td>' + article['boardType'] + '</td>\n' +
        '<td> <a href=/article?id=' + article['id'] + '>' + depth + article['title'] + '</a><a>[' + article['commentsCount'] + ']</a></td>\n' +
        '<td>' + article['author']['nickname'] + '</td>\n' +
        '<td>' + showDateFrom(article['updatedAt']) + '</td>\n' +
        '<td>' + article['hits'] + '</td>\n' +
        '<td>' + article['likes'] + '</td>\n' +
        '</tr>';
    return createTrFromStr(trStr);
}
