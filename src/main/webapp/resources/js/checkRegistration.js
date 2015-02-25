/**
 * Created by User on 28.05.14.
 */
(function( $ ){

    $(function() {

        $('.reg').each(function(){
            var form = $(this),
                btn = form.find('.btn');

            form.find('.input-xlarge').addClass('empty_field');

            // Функция проверки полей формы
            function checkInput(){
                form.find('.form-control').each(function(){
                    if($(this).val() != ''){
                        $(this).removeClass('empty_field');
                        //divGroupForm.removeClass('has-error');
                        //divGroupForm.addClass('has-success');
                    } else {
                        $(this).addClass('empty_field');
                        //divGroupForm.addClass('has-error');
                        //divGroupForm.removeClass('has-success');
                    }
                });
            }

            setInterval(function(){
                checkInput();
                var sizeEmpty = form.find('.empty_field').size();
                if(sizeEmpty > 0){
                    if(btn.hasClass('disabled')){
                        return false
                    } else {
                        btn.addClass('disabled');
                    }
                    btn.removeClass('btn-primary');
                    btn.addClass('btn-danger');

                } else {

                    btn.removeClass('disabled');
                    btn.removeClass('btn-danger');
                    btn.addClass('btn-primary');

                }
            },500);

        });

    });

})( jQuery );
