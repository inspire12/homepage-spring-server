
// **********************************************
// ** Classy Nav - 1.1.0
// ** Responsive Megamenu Plugins
// ** Copyright (c) 2019 Designing World
// **********************************************

(function ($) {
    $.fn.classyNav = function (options) {
        // Variables
        let navContainer = $('.classy-nav-container');
        let classy_nav = $('.classynav ul');
        let classy_navli = $('.classynav > ul > li');
        let navbarToggler = $('.classy-navbar-toggler');
        let closeIcon = $('.classycloseIcon');
        let navToggler = $('.navbarToggler');
        let classyMenu = $('.classy-menu');
        let classyMenuProfile = $('.menu-profile');
        let var_window = $(window);
        let searchIcon = $('.serach-icon');
        // default options
        let defaultOpt = $.extend({
            breakpoint: 991,
            openCloseSpeed: 500,
            megaopenCloseSpeed: 800
        }, options);

        return this.each(function () {

            // navbar toggler
            navbarToggler.on('click', function () {
                navToggler.toggleClass('active');
                classyMenu.toggleClass('menu-on');
                searchIcon.toggleClass('d-none')
            });

            // close icon
            closeIcon.on('click', function () {
                navToggler.removeClass('active');
                classyMenu.removeClass('menu-on');
                searchIcon.removeClass('d-none')
            });

            // add dropdown & megamenu class in parent li class
            classy_navli.has('.dropdown').addClass('cn-dropdown-item');
            classy_navli.has('.megamenu').addClass('megamenu-item');

            // adds toggle button to li items that have children
            classy_nav.find('li a').each(function () {
                if ($(this).next().length > 0) {
                    $(this).parent('li').addClass('has-down').append('<span class="dd-trigger"></span>');
                }
            });

            // expands the dropdown menu on each click
            classy_nav.find('li .dd-trigger').on('click', function (e) {
                e.preventDefault();
                $(this).parent('li').children('ul').stop(true, true).slideToggle(defaultOpt.openCloseSpeed);
                $(this).parent('li').toggleClass('active');
            });

            // add padding in dropdown & megamenu item
            $('.megamenu-item').removeClass('has-down');

            // expands the megamenu on each click
            classy_nav.find('li .dd-trigger').on('click', function (e) {
                e.preventDefault();
                $(this).parent('li').children('.megamenu').slideToggle(defaultOpt.megaopenCloseSpeed);
            });


            // check browser width in real-time
            function breakpointCheck() {
                let headerLogin = $('#headerLogin');
                let headerLoginAfter = $('#headerLoginAfter');
                let windowWidth = window.innerWidth;
                if (windowWidth <= defaultOpt.breakpoint) {
                    navContainer.removeClass('breakpoint-off').addClass('breakpoint-on');
                    classyMenuProfile.removeClass('d-none');
                    headerLogin.removeClass('display-inline-block').addClass('d-none');
                    headerLoginAfter.addClass('d-none');
                    $('#searchIcon').hide()

                } else {
                    navContainer.removeClass('breakpoint-on').addClass('breakpoint-off');
                    classyMenuProfile.addClass('d-none');
                    headerLogin.removeClass('d-none').addClass('display-inline-block');
                    headerLoginAfter.removeClass('d-none');
                    $('#searchIcon').show()
                }
            }

            breakpointCheck();

            var_window.on('resize', function () {
                breakpointCheck();
            });

            // sidebar menu enable
            if (defaultOpt.sideMenu === true) {
                navContainer.addClass('sidebar-menu-on').removeClass('breakpoint-off');
            }
        });
    };

}(jQuery));


if ($.fn.classyNav) {
    $('#alimeNav').classyNav();
}

