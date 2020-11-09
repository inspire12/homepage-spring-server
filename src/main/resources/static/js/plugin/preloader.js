(function ($) {
    'use strict';

    var alime_window = $(window);

    // ****************************
    // :: 1.0 Preloader Active Code
    // ****************************

    alime_window.on('load', function () {
        $('#preloader').fadeOut('1000', function () {
            $(this).remove();
        });
    });
});
