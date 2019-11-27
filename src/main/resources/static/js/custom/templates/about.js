function createTeamMemberArea(user) {
    let domStr =
        '                <div class="col-md-6 col-xl-3">\n' +
        '                    <div class="team-content-area text-center mb-30 wow fadeInUp" data-wow-delay="500ms">\n' +
        '                        <div class="member-thumb">\n' +
        '                            <img src="' + user['profile'] + '" alt="">\n' +
        '                        </div>\n' +
        '                        <h5>' + user['id'] + '</h5>\n' +
        '                        <span>' + user['name'] + '</span>\n' +
        '                        <div class="member-social-info">\n' +
        '                            <a href="#"><i class="ti-facebook"></i></a>\n' +
        '                            <a href="#"><i class="ti-twitter-alt"></i></a>\n' +
        '                            <a href="#"><i class="ti-linkedin"></i></a>\n' +
        '                            <a href="#"><i class="ti-pinterest"></i></a>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>'
    return createElementFromStr(domStr);
}