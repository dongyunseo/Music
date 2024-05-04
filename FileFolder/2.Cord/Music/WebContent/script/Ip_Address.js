window.onload = function() {
    // IP 주소를 가져오는 AJAX 요청
    $.getJSON("https://api.ip.pe.kr/json/", function(data){
        // IP 주소를 가져왔을 때의 처리
        var ipAddress = data.ip;
        // 가져온 IP 주소를 폼의 input 요소에 설정
        $("#ip_address").val(ipAddress);
        // 폼 서브밋을 막기
        $("#ip_form").submit(function(event) {
            event.preventDefault(); // 기본 서브밋 이벤트를 막음
            // 폼을 서브밋
            $.post($(this).attr("action"), $(this).serialize(), function(response) {
                // 서브밋 후의 처리 (만약 필요하다면)
            });
        });
        // 폼 수동으로 서브밋
        $("#ip_form").submit();
    });
    document.getElementById("Melon").click();
};