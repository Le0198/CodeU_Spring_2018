$(document).ready(function(){
    $( "a.scrollLink" ).click(function( event ) {
        event.preventDefault();
        $("html, body").animate({ scrollTop: $($(this).attr("href")).offset().top }, 500);
    });

    $("#gif").click(function() {
        $(".gif-section").toggle();
        $("#textarea").toggle();
    });

    $(".captioned-gif").click(function() {
        $(".captioned-gif").removeClass("selected");
        $(this).addClass("selected");

        var url = $(this).find("img").attr("src");

        $("#textarea").val(url);
        $("#type").val("image");
    });
});

/*
    Hamburger transition tutorial
    By Nick
    http://callmenick.com/2015/01/25/animating-css-only-hamburger-menu-icons/
*/
$(document).ready(function () {
	"use strict";
    (function () {

		var toggles = document.querySelectorAll(".c-hamburger");
		var i = toggles.length - 1;

        for (i; i >= 0; i--) {
            var toggle = toggles[i];
            toggleHandler(toggle);
        }

        function toggleHandler(toggle) {
            toggle.addEventListener("click", function (e) {
                e.preventDefault();
                (this.classList.contains("is-active") === true) ? this.classList.remove("is-active") : this.classList.add("is-active");

            });
        }
    })();
});


var inProgress = false;

$("#nav").click(function () {

	"use strict";

	var width = $(window).width() - 68;

    if ($(".c-hamburger").hasClass("is-active")) {
		inProgress = true;
		$('#dim-screen').fadeTo(500, 1);
        $("#nav-expand").animate({
            width: "300px"
        }, {
            duration: 1500,
            easing: 'easeInOutQuint'
        });
        $(".nav-list").animate({
            left: "0%"
        }, {
            duration: 300,
            easing: 'easeInOutQuint'
        });
        $(".c-hamburger").css({'transform' : 'rotate(180deg)'});
        $("body").css("overflow", "hidden");
    } else {
		$('#dim-screen').fadeTo(500, 0, function (){
			$(this).hide();
		});
        $("#nav-expand").animate({
            width: "0%"
        }, {
            duration: 1500,
            easing: 'easeInOutQuint'
        });
        $(".nav-list").animate({
            left: "-100%"
        }, {
            duration: 400,
            easing: 'easeInOutQuint'
        });
        $(".c-hamburger").css({'transform' : 'rotate(0deg)'});
        $("body").css("overflow", "auto");
    }
});

$(".nav-item").click(function () {
    $(".c-hamburger").removeClass("is-active");
    $('#dim-screen').fadeTo(500, 0, function (){
			$(this).hide();
		});
        $("#nav-expand").animate({
            width: "0%"
        }, {
            duration: 1500,
            easing: 'easeInOutQuint'
        });
        $(".nav-list").animate({
            left: "-100%"
        }, {
            duration: 400,
            easing: 'easeInOutQuint'
        });
        $(".c-hamburger").css({'transform' : 'rotate(0deg)'});
        $("body").css("overflow", "auto");
});
