let _domParser = new DOMParser();
const createElementFromStr = (domstring) => {
    const html = _domParser.parseFromString(domstring, 'text/html');
    return html.body.firstChild;
};

const createTrFromStr = (domstring) => {
    const html = _domParser.parseFromString("<table><tbody>" + domstring + "</tbody></table>", 'text/html');
    return html.body.firstChild.firstChild.firstChild;
};

const creategalleryImgModel = (name, tag, delay) => {
    let model = {};
    model.name = name;
    model.tag = tag;
    model.delay = delay;
    return model;
};

function creategalleryImg(img, tag, delay) {
    let singleGallayString = '<div class="col-12 col-sm-6 col-lg-3 single_gallery_item '
        + tag + ' mb-30 wow fadeInUp" data-wow-delay="'
        + delay + 'ms"><div class="single-portfolio-content"><img src="'
        + img + '" alt=""><div class="hover-content"><a href="'
        + img + '" class="portfolio-img">+</a></div></div></div>'
    return createElementFromStr(singleGallayString);
    // document.getElementById("alime-portfolio").append(singleGallayItem);
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
        '                            <a href="#" class="post-comments" id="postComments">'+ article['comments'].length+ ' Comments</a>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </section>';
    let sectionElement = createElementFromStr(section);
    return sectionElement;
}

function createFollowAreaImg(imgName) {
    let followAreaImg = '<div class="single-instagram-item">\n' + '<img src="img/bg-img/'
        + imgName + '" alt="">\n' + '<div class="instagram-hover-content text-center d-flex align-items-center justify-content-center">\n' + '<a href="#">\n' + '<i class="ti-instagram" aria-hidden="true"></i>\n'
        + '<span>Best of the world,Tasha</span>\n' + '</a>\n' + '</div>\n' + '</div>'
    return createElementFromStr(followAreaImg);
}

function appendFollowAreaImgs(imgs){
    imgs = ["2.jpg","3.png","4.jpg","5.jpg","6.jpg","7.jpg"];
    for (let i=0; i  < imgs.length; i++){
        document.getElementById("instagram-feed-area-id").append(createFollowAreaImg(imgs[i]));
    }
}

function appendGalleryImgs(imgs) {
    imgs = [creategalleryImgModel("devday2018.png", "algorithm", 500), creategalleryImgModel("ana_2.png", "study", 200)
        , creategalleryImgModel("ucpc.png", "algorithm", 700), creategalleryImgModel("sw_festival.png", "contest", 700)
        , creategalleryImgModel("study.jpg", "study", 400)
        , creategalleryImgModel("ant_2.jpg", "study", 200), creategalleryImgModel("poster.png", "algorithm", 500)];

    for (let i = 0; i < imgs.length; i++) {
        try{
            let singleGallayItem = creategalleryImg("/img/bg-img/" + imgs[i].name , imgs[i].tag,imgs[i].delay);
            document.getElementById("alime-portfolio").append(singleGallayItem);
        }catch (e) {
            console.dir(e);
            console.dir("gallery img error: index: " + i)

        }

    }
}

function getParameter(param) {
    let url = new URL(window.location.href);
    return url.searchParams.get(param)
}

function createTeamMemberArea(user, delay) {
    let domStr =
        '                <div class="col-md-6 col-xl-3">\n' +
        '                    <div class="team-content-area text-center mb-30 wow fadeInUp" data-wow-delay="' + delay +'ms">\n' +
        '                        <div class="member-thumb">\n' +
        '                            <img src="' + user['profile'] + '" alt="">\n' +
        '                        </div>\n' +
        '                        <h5>' + user['nickname'] + '</h5>\n' +
        '                        <span>' + user['name'] + '</span>\n' +
        // '                        <div class="member-social-info">\n' +
        // '                            <a href="#"><i class="ti-facebook"></i></a>\n' +
        // '                            <a href="#"><i class="ti-twitter-alt"></i></a>\n' +
        // '                            <a href="#"><i class="ti-linkedin"></i></a>\n' +
        // '                            <a href="#"><i class="ti-pinterest"></i></a>\n' +
        // '                        </div>\n' +
        '                    </div>\n' +
        '                </div>';
    return createElementFromStr(domStr);
}