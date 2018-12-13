(function($) {
  "use strict"; // Start of use strict

    $("#ground_school_resit").on("input", function() {
        $("#ground_school_resit_label").html("True/False")
    });

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1;
    var yyyy = today.getFullYear() - 18;


    $(function(){
            $('.date-picker').datepicker({
            format: "dd/mm/yyyy",
            startDate: "01-01-2010",
            maxViewMode: 3
        });
    });

    $(function(){
        $('#dob').datepicker({
            format: "dd/mm/yyyy",
            startDate: "01-01-1920",
            endDate: dd + "-" + mm + "-" + yyyy,
            maxViewMode: 3
        });
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

function modalOpen() {
    if ($("#postCodeError").val() != null) {
        $('#courseRegistrationModal').modal('show');
    } else if ($("#englishLevelError").val() != null) {
        $('#courseRegistrationModal').modal('show');
    }
}