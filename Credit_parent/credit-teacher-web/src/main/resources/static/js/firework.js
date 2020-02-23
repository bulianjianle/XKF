/* 生成烟花 */
for (var i=0; i<250; i++) {
    var color_one = randomNum(0, 255);
    var color_two = randomNum(0, 255);
    var color_three = randomNum(0, 255);
    $('#fireworks').append($('#fireworks .firework:first-child').clone(true).css({
        'top': randomNum(-10, 400) + 'px',
        'left': randomNum(0, 800) + 'px',
        'transform': 'rotate(' + randomNum(0, 360) + 'deg)',
        'background-color': 'rgb(' + color_one + ',' + color_two + ',' + color_three + ')',
        'box-shadow': '0 0 3px 1px rgb(' + color_one + ',' + color_two + ',' + color_three + ')',
        'animationDelay': i % 50 == 0 ? '0s' : i * 0.02 + 's'
    }));
}

function randomNum (max, min) {
    var num = Math.floor(Math.random() * (max - min + 1) + min)
    return num;
}