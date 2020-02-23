/* 生成流星雨 */
for (var i=0; i<35; i++) {
    $('#meteors').append($('#meteors .meteor:first-child').clone(true).css({
        'top': randomNum(500, -100) + 'px',
        'left': randomNum(1400, 300) + 'px',
        'animationDelay': i % 7 == 0 ? '0s' : i * 0.7 + 's'
    }));
}

function randomNum (max, min) {
    var num = Math.floor(Math.random() * (max - min + 1) + min)
    return num;
}