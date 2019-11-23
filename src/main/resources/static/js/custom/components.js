const el = (domstring) => {
    const html = new DOMParser().parseFromString(domstring, 'text/html');
    return html.body.firstChild;
};


function createGallaryImg(img, tag, delay) {
    let singleGallayString = '<div class="col-12 col-sm-6 col-lg-3 single_gallery_item '+ tag +' mb-30 wow fadeInUp" data-wow-delay="' + delay + 'ms"><div class="single-portfolio-content"><img src="' + img + '" alt=""><div class="hover-content"><a href="' + img + '" class="portfolio-img">+</a></div></div></div>'
    return singleGallayItem = el(singleGallayString);
    // document.getElementById("alime-portfolio").append(singleGallayItem);
}

