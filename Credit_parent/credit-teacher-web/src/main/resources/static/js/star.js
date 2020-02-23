/* 生成星星 */
for (var i=0; i<500; i++) {
    $('#stars').append($('#stars .star:first-child').clone(true).css({
        'top': randomNum(750, 0) + 'px',
        'left': randomNum(900, -350) + 'px',
        'animationDelay': i % 100 == 0 ? '0s' : i * 0.03 + 's'
    }));
}

function randomNum (max, min) {
    var num = Math.floor(Math.random() * (max - min + 1) + min)
    return num;
}