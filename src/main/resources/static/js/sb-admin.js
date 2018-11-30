(function($) {
  "use strict"; // Start of use strict

    var format = "dd/mm/yyyy";
    var match = new RegExp(format
        .replace(/(\w+)\W(\w+)\W(\w+)/, "^\\s*($1)\\W*($2)?\\W*($3)?([0-9]*).*")
        .replace(/[mdy]/g, "\\d"));
    var replace = "$1/$2/$3$4"
        .replace(/\//g, format.match(/\W/));

    function doFormat(target)
    {
        target.value = target.value
            .replace(/(^|\W)(?=\d\W)/g, "$10")   // padding
            .replace(match, replace)             // fields
            .replace(/(\W)+/g, "$1");            // remove repeats
    }

    $("input[name='dob']:first").keyup(function(e) {
        if(!e.ctrlKey && !e.metaKey && (e.keyCode == 32 || e.keyCode > 46))
            doFormat(e.target)
    });

    $("#dob").on("input", function() {
      $("#dob_label").html("Date of Birth (dd/mm/yyyy)")
    });

    $("#ground_school.resit").on("input", function() {
      alert("HELLO");
        $("#ground_school.resit_label").html("True/False")
    });


    // Toggle the side navigation
  $("#sidebarToggle").on('click',function(e) {
    e.preventDefault();
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
        delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll',function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(event) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    event.preventDefault();
  });

})(jQuery); // End of use strict