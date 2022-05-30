var fonte = 16;

$(document).ready(function(){
    $('#aumentaFonte').click(function(){
        if(fonte<20){
            fonte=fonte+1;
            $('body').css({'font-size': fonte+ 'px'});
            $('p').css({'font-size': fonte+ 'px'});
            $('h3').css({'font-size': fonte+ 'px'});
            $('label').css({'font-size': fonte+ 'px'});
            $('input').css({'font-size': fonte+ 'px'});
            $('a').css({'font-size': fonte+ 'px'});
            $('header').css({'font-size': fonte+ 'px'});
            $('option').css({'font-size': fonte+ 'px'});
            $('select').css({'font-size': fonte+ 'px'});
            // $('#acessibilidade').css({'font-size': fonte+'px'});
            // $('#desenvolvedoresFoot').css({'font-size': fonte+'px'});
        }
    });

    $('#diminuiFonte').click(function(){
        if(fonte>12){
            fonte=fonte-1;
            $('body').css({'font-size': fonte+'px'});
            $('p').css({'font-size': fonte+'px'});
            $('h3').css({'font-size': fonte+ 'px'});
            $('label').css({'font-size': fonte+ 'px'});
            $('input').css({'font-size': fonte+ 'px'});
            $('a').css({'font-size': fonte+ 'px'});
            $('header').css({'font-size': fonte+ 'px'});
            $('option').css({'font-size': fonte+ 'px'});
            $('select').css({'font-size': fonte+ 'px'});
            // $('#acessibilidade').css({'font-size': fonte+'px'});
            // $('#desenvolvedoresFoot', 'p').css({'font-size': fonte+'px'});
        }
    });

});
// $('body').css({'font-size': fonte+'pt'});
