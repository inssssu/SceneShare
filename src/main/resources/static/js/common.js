$(() => {

    $(document).ready(function () {

        // movie-list bookmark(보고싶어요) 클릭 시 색상 변경
        $('.bookmark').click(function () {
            if ($(this).hasClass('active')) {
                $(this).removeClass('active')
            }
            else {
                $(this).addClass('active')
            }
        });


        // input[type=search] 커스텀 삭제버튼
        const input = document.getElementById('search-movie');
        const clearBtn = document.getElementById('clearBtn');

        input.addEventListener('input', () => {
            clearBtn.style.display = input.value ? 'block' : 'none';
        });

        clearBtn.addEventListener('click', () => {
            input.value = '';
            input.focus();
            clearBtn.style.display = 'none';
        });


        // textarea 글자수 
        const textarea = document.getElementById('myTextarea');
        const charCount = document.getElementById('charCount');

        textarea.addEventListener('input', () => {
            charCount.textContent = textarea.value.length;
        });


        //
        


    });

});