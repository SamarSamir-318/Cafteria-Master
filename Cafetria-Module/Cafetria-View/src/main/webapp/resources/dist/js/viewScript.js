$(function ()
{
    $('.food > .info > .content .stars')
            .bind('mousemove', function (e)
            {
                var pct = e.pageX - $(this).offset().left;
                pct = pct / $(this).width() * 100;
                $(this).find('> em').css('width', pct + '%');
            })
            .bind('mouseleave', function ()
            {
                $(this).find('> em').animate({width: '91%'}, 250);
            });
});


