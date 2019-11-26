let _domParser = new DOMParser();
const createElementFromStr = (domstring) => {
    const html = _domParser.parseFromString(domstring, 'text/html');
    return html.body.firstChild;
};

const createTrFromStr = (domstring) => {
    const html = _domParser.parseFromString("<table><tbody>" + domstring + "</tbody></table>", 'text/html');
    return html.body.firstChild.firstChild.firstChild;
};


function createGallaryImgModel(name, tag, delay) {
    let model = {};
    model.name = name;
    model.tag = tag;
    model.delay = delay;
    return model;
}

function createGallaryImg(img, tag, delay) {
    let singleGallayString = '<div class="col-12 col-sm-6 col-lg-3 single_gallery_item '
        + tag + ' mb-30 wow fadeInUp" data-wow-delay="'
        + delay + 'ms"><div class="single-portfolio-content"><img src="'
        + img + '" alt=""><div class="hover-content"><a href="'
        + img + '" class="portfolio-img">+</a></div></div></div>'
    return createElementFromStr(singleGallayString);
    // document.getElementById("alime-portfolio").append(singleGallayItem);
}

function createFollowAreaImg(imgName) {
    let followAreaImg = '<div class="single-instagram-item">\n' + '<img src="img/bg-img/'
        + imgName + '.jpg" alt="">\n' + '<div class="instagram-hover-content text-center d-flex align-items-center justify-content-center">\n' + '<a href="#">\n' + '<i class="ti-instagram" aria-hidden="true"></i>\n'
        + '<span>Best of the world,Tasha</span>\n' + '</a>\n' + '</div>\n' + '</div>'
    return createElementFromStr(followAreaImg);
}


function createGallaryImgModel (name, tag, delay) {
    let model = {};
    model.name = name;
    model.tag = tag;
    model.delay = delay;
    return model;
}

function appendFollowAreaImgs(){
    let imgs = ['boyoung_test', "3","18","4","5","6","7"];
    for (let i=0; i  < imgs.length; i++){
        document.getElementById("instagram-feed-area-id").append(createFollowAreaImg(imgs[i]));
    }
}

function appendGallaryImgs() {
    let imgs = [createGallaryImgModel("3", "human", 500), createGallaryImgModel("4", "country", 200)
        , createGallaryImgModel("boyoung_test", "human", 700)];
    for (let i = 0; i < imgs.length; i++) {
        let singleGallayItem = createGallaryImg("/img/bg-img/" + imgs[i].name + ".jpg", imgs[i].tag,imgs[i].delay);
        document.getElementById("alime-portfolio").append(singleGallayItem);
    }
}
