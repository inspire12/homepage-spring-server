const el = (domstring) => {
    const html = new DOMParser().parseFromString(domstring, 'text/html');
    return html.body.firstChild;
};

function createGallaryImgModel (name, tag, delay) {
    let model = {};
    model.name = name;
    model.tag = tag;
    model.delay = delay;
    return model;
}

function createGallaryImg(img, tag, delay) {
    let singleGallayString = '<div class="col-12 col-sm-6 col-lg-3 single_gallery_item '
        + tag +' mb-30 wow fadeInUp" data-wow-delay="'
        + delay + 'ms"><div class="single-portfolio-content"><img src="'
        + img + '" alt=""><div class="hover-content"><a href="'
        + img + '" class="portfolio-img">+</a></div></div></div>'
    return el(singleGallayString);
    // document.getElementById("alime-portfolio").append(singleGallayItem);
}

function createFollowAreaImg(imgName) {
    let followAreaImg =  '<div class="single-instagram-item">\n' + '<img src="img/bg-img/'
        + imgName +'.jpg" alt="">\n' + '<div class="instagram-hover-content text-center d-flex align-items-center justify-content-center">\n' + '<a href="#">\n' + '<i class="ti-instagram" aria-hidden="true"></i>\n'
        +'<span>Best of the world,Tasha</span>\n' + '</a>\n' + '</div>\n' + '</div>'
    return el(followAreaImg);
}
