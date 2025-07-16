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



    // 별점 주기
    const rateWrap = document.querySelectorAll('.rating'),
      label = document.querySelectorAll('.rating .rating__label'),
      input2 = document.querySelectorAll('.rating .rating__input'),
      labelLength = label.length,
      opacityHover = '0.6';

    let stars = document.querySelectorAll('.rating .star-icon');

    checkedRate();
  
    rateWrap.forEach(wrap => {
      wrap.addEventListener('mouseenter', () => {
        stars = wrap.querySelectorAll('.star-icon');

        stars.forEach((starIcon, idx) => {
          starIcon.addEventListener('mouseenter', () => {
            initStars();
            filledRate(idx, labelLength);

            for (let i = 0; i < stars.length; i++) {
              if (stars[i].classList.contains('filled')) {
                stars[i].style.opacity = opacityHover;
              }
            }
          });

          starIcon.addEventListener('mouseleave', () => {
            starIcon.style.opacity = '1';
            checkedRate();
          });

          wrap.addEventListener('mouseleave', () => {
            starIcon.style.opacity = '1';
          });
        });
      });
    });

    function filledRate(index, length) {
      if (index <= length) {
        for (let i = 0; i <= index; i++) {
          stars[i].classList.add('filled');
        }
      }
    }

    function checkedRate() {
      let checkedRadio = document.querySelectorAll('.rating input[type="radio"]:checked');


      initStars();
      checkedRadio.forEach(radio => {
        let previousSiblings = prevAll(radio);

        for (let i = 0; i < previousSiblings.length; i++) {
          previousSiblings[i].querySelector('.star-icon').classList.add('filled');
        }

        radio.nextElementSibling.classList.add('filled');


        function prevAll() {
          let radioSiblings = [],
            prevSibling = radio.parentElement.previousElementSibling;

          while (prevSibling) {
            radioSiblings.push(prevSibling);
            prevSibling = prevSibling.previousElementSibling;
          }
          return radioSiblings;
        }
      });

    }

    function initStars() {
      for (let i = 0; i < stars.length; i++) {
        stars[i].classList.remove('filled');
      }
    }



  });

});