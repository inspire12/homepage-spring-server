console.log("hello home");

class img {
    constructor(name, tag, delay){
        this.name = name;
        this.tag = tag;
        this.delay = delay;
    }
}

function createBreadcrumbSection(article) {
    let section = '<section class="breadcrumb-area blog bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/18.jpg);">\n' +
        '        <div class="container h-100">\n' +
        '            <div class="row h-100 align-items-center">\n' +
        '                <div class="col-12">\n' +
        '                    <div class="breadcrumb-content text-center">\n' +
        '                        <a href="#" class="btn post-catagory">' + article['category'] + '</a>\n' +
        '                        <h2 class="page-title">' + article['subject'] + '</h2>\n' +
        '                        <div class="post-meta">\n' +
        '                            <a href="#" class="post-author"> By&nbsp;' + article['author']['nickname'] + '</a>\n' +
        '                            <a href="#" class="post-date">' + article['created_at'] + '</a>\n' +
        '                            <a href="#" class="post-comments">No Comments</a>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </section>'
    let sectionElement = createElementFromStr(section);
    return sectionElement;
}

