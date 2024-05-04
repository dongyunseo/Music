var imageList; // 전역 범위에 imageList 변수를 정의합니다.

$(document).ready(function(){
    $(".nav-link").click(function(){
        var buttonId = $(this).attr("id"); // 클릭된 버튼의 ID를 가져옴
    	$.ajax({
    		type : 'POST',
	        url : '../AjaxTop100Servlet',
            data: { Platform : buttonId }, // 클릭된 버튼의 ID를 전달
            success: function(response) {
	            var urlArray = response.split(", ");
	            var newData_List = [];
                for (var i = 0; i < urlArray.length; i += 9) { // 받은 데이터를 세 개씩 끊어서 반복
                	var releaseDate = new Date(urlArray[i + 6]); // release_date를 Date 객체로 변환

                    newData_List.push({ 
                        "Day": urlArray[i], 
                        "Rank": parseInt(urlArray[i + 1]), 
                        "Title": urlArray[i + 2].replace(/,/g, "").split("\n"),
                        "Singer": urlArray[i + 3], 
                        "album_img_urls": urlArray[i + 4], 
                        "release_date": urlArray[i + 5], 
                        "genre_text": urlArray[i + 6],
                        "View_Count": urlArray[i + 7],
                        "click_Url": urlArray[i + 8]
                    }); // 객체 생성하여 newData에 추가

                }
                appendTop100(newData_List, buttonId); // 이미지 리스트를 전달하여 appendImages 함수를 호출합니다.
            }
	    });
	    return false; // 기본 동작 중지
	});

	// 조회수 천 단위 , 붙이는 스크립트
    function addCommasToNumber(number) {
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    // 데이터를 테이블에 추가하는 함수
    function appendTop100(newData_List, buttonId) {
        var tableBody = document.querySelector('.Top100List'); // 테이블의 tbody 요소를 참조합니다.
        // 기존의 행을 모두 제거합니다.
        tableBody.innerHTML = "";

        // 테이블의 헤더 값들을 설정합니다.
        var headers = {};
        if (buttonId === 'Melon') {
            headers = {
                rank: 'Rank',
                album: 'Album',
                title: 'Title',
                artist: 'Artist',
                release_date: '발매일'
            };
        } else if (buttonId === 'Genie' || buttonId === 'Circle') {
            headers = {
                rank: 'Rank',
                album: 'Album',
                title: 'Title',
                artist: 'Artist'
            };
        } else if (buttonId === 'Youtube') {
            headers = {
                rank: 'Rank',
                album: 'Album',
                title: 'Title',
                artist: 'Artist',
                release_date: '조회수'
            };
        }

        // 새로운 헤더 값들을 테이블에 추가합니다.
        var newRow = document.createElement('tr');
        for (var key in headers) {
            newRow.innerHTML += `<th class="${key}">${headers[key]}</th>`;
        }
        tableBody.appendChild(newRow);

        // 데이터 배열을 순회하며 각 데이터를 테이블에 추가합니다.
        newData_List.forEach(function(music, index) {
            var newRow = document.createElement('tr');

            // 각 데이터를 새로운 행에 추가합니다.
            newRow.innerHTML = `
                <td class="rank-number">${index + 1}</td>
                ${buttonId === 'Melon' || buttonId === 'Youtube' ? `<td><a href="${music.click_Url}" target="_blank"><img src="${music.album_img_urls}" class="album-image"></a></td>` : `<td><img src="${music.album_img_urls}" class="album-image"></td>`}
                <td class="Title_GENRE">
                    <p>${music.Title}</p>
                    ${buttonId === 'Melon' || buttonId === 'Genie' ? `<p>장르 : ${music.genre_text}</p>` : ''}
                </td>
                 <td>${music.Singer}</td>
            	${buttonId !== 'Genie' && buttonId !== 'Circle' ? `<td>${buttonId === 'Youtube' ? addCommasToNumber(music.View_Count) : music.release_date}</td>` : ''}`;
            
            // 테이블에 새로운 행을 추가합니다.
            tableBody.appendChild(newRow);
        });
    }

});
