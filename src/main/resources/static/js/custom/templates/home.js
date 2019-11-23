console.log("hello home");

class img {
    constructor(name, tag, delay){
        this.name = name;
        this.tag = tag;
        this.delay = delay;
    }
}

function createFollowAreaImg() {
    let num = '10';
    let text = 'hygoni';
    let followAreaImg = '<!-- Single Instagram Item -->\n' +
        '        <div class="single-instagram-item">\n' +
        '            <img src="img/bg-img/' + num + '.jpg" alt="">\n' +
        '            <div class="instagram-hover-content text-center d-flex align-items-center justify-content-center">\n' +
        '                <a href="#">\n' +
        '                    <i class="ti-instagram" aria-hidden="true"></i>\n' +
        '                    <span>' + text +'</span>\n' +
        '                </a>\n' +
        '            </div>\n' +
        '        </div>';
    return el(followAreaImg);
}

function appendFollowAreaImgs() {
    for(let i = 0; i < 3; i++){
        document.getElementById("instagram-feed-area-id").append(createFollowAreaImg());
    }
}

function appendGallaryImgs() {
    let imgs = ["3", "4", "5", "6", "7", "8", "9", "10", "boyoung_test"];

    for (let i = 0; i < imgs.length; i++) {
        let singleGallayItem = createGallaryImg("/img/bg-img/" + imgs[i] + ".jpg", "",500);
        document.getElementById("alime-portfolio").append(singleGallayItem);
    }
}

