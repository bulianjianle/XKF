
var width = window.innerWidth + 'px';
var height = window.innerHeight + 'px';
$('body').css({
    'backgroundSize': width + ' ' + height
});
$(window).resize(function() {
    width = window.innerWidth + 'px';
    height = window.innerHeight + 'px';
    $('body').css({
    'backgroundSize': width + ' ' + height
    });
})