var imageList; // 전역 범위에 imageList 변수를 정의합니다.

$(document).ready(function(){
    $(".nav-link").click(function(){
        var buttonId = $(this).attr("id"); // 클릭된 버튼의 ID를 가져옴
    	$.ajax({
    		type : 'POST',
	        url : '../AjaxTopTenServlet',
            data: { Platform : buttonId }, // 클릭된 버튼의 ID를 전달
            success: function(response) {
	            var urlArray = response.split(", ");
	            imageList = []; // 전역 변수인 imageList를 사용합니다.
	            for (var i = 0; i < urlArray.length; i++) {
	                imageList.push(urlArray[i]);
	            }
	            appendImages(imageList); // 이미지 리스트를 전달하여 appendImages 함수를 호출합니다.
	            
	        }
	    });
	    return false; // 기본 동작 중지

	    
		// 이미지를 추가하는 함수
		function appendImages(imageList) {
			var imageContainer = document.getElementById("imageContainer");
	        imageContainer.innerHTML = ""; // 기존 이미지를 모두 제거합니다.
	        
	        for (var i = 0; i < imageList.length; i++) {
		        var img = document.createElement("img");
		        img.src = imageList[i];
		        img.alt = "Image";
		        imageContainer.appendChild(img);
		    }


		}

	});
});