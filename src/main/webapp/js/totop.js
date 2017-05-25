/**
 * Created by 75747 on 2017/5/25.
 */
$(window).scroll(function () {
    $(window).scrollTop() > 500 ? $("#rocket").addClass("show") : $("#rocket").removeClass("show");
});
$("#rocket").click(function () {
    $("#rocket").addClass("launch");
    $("html, body").animate({
        scrollTop: 0
    }, 500, function () {
        $("#rocket").removeClass("show launch");
    });
    return false;
});