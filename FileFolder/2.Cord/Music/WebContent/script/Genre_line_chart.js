$(document).ready(function(){
	var lineChart2;
	if (typeof lineChart2 !== 'undefined') {
        lineChart2.destroy();
        $("#line-chart2").html(""); // line-chart2 요소 내용을 비웁니다.
    }
    $(".nav-link").click(function(){
        var buttonId = $(this).attr("id"); // 클릭된 버튼의 ID를 가져옴
        // 이전 차트가 있다면 삭제합니다.
        if (typeof lineChart2 !== 'undefined') {
            lineChart2.destroy();
        } 
        $.ajax({
            type: "POST",
            url: "../GenreAjaxGraphServlet", // 두 번째 DAO의 엔드포인트 URL
            data: { Platform: buttonId }, // 클릭된 버튼의 ID를 전달
            success: function (response) {
                var data = response.split(', '); // 받은 데이터를 쉼표와 공백을 기준으로 나눔
                var newData = []; // 배열로 선언

                for (var i = 0; i < data.length; i += 3) { // 받은 데이터를 세 개씩 끊어서 반복
                    newData.push({ "Day": data[i], "Rank": parseInt(data[i + 1]), "Title": data[i + 2] }); // 객체 생성하여 newData에 추가
                }

                // 차트 데이터 생성
                var chartData = [];
                
                // newData 배열에서 중복을 제거한 Day 값만 추출하여 새로운 배열 생성
                var uniqueDays = [...new Set(newData.map(entry => entry.Day))];
                
                // 중복 제거된 Day 값을 오름차순으로 정렬  ( uniqueDays → x축의 값 추출 Day의 값을 groupby하여 정렬 ) 
                uniqueDays.sort((a, b) => a.localeCompare(b, undefined, { numeric: true }));
                
                
                // 최신 날짜 추출
                var maxRank = Math.max(...uniqueDays);
                // 문자열로 변환
                var dateString = maxRank.toString();
                // 연도, 월, 일 추출
                var year = dateString.substr(0, 4);
                var month = dateString.substr(4, 2);
                var day = dateString.substr(6, 2);
                // 날짜 형식으로 변환
                var formattedDate = year + "년 " + month + "월 ";

                
                // x축의 값 
                lineChart2 = new Chart(document.getElementById("line-chart2"), {
                    type: 'line',
                    data: {
                    	// Day 값을 groupby해서 놓기 
                    	labels: uniqueDays,  // 21~24
                        datasets: []
                    },
                    options: {
                        title: {
                            display: true,
                            text : buttonId + ' : 장르별'
                            	// 최신 데이터가 들어갈 예정 → Max(Day) 27~36줄
                        },
                        scales: {
                            yAxes: [{
                                ticks: {
                                    reverse: false, // y축 반대로
                                    min: 0,
                                    max: 240,
                                    stepSize: 60
                                }
                            }]
                        }
                    }
                });
                // 차트 업데이트
                lineChart2.update();


                // newData를 차트 데이터에 추가
                newData.forEach(entry => {
				    var existingDatasetIndex = lineChart2.data.datasets.findIndex(dataset => dataset.label === entry.Title);
				    if (existingDatasetIndex === -1) {
				        // 차트에 해당 제목의 데이터셋이 없는 경우
				        var data = Array(lineChart2.data.labels.length).fill(null); // 초기
																					// 데이터셋
																					// 생성
				        var dataIndex = lineChart2.data.labels.findIndex(label => label === entry.Day);
				        data[dataIndex] = entry.Rank; // 해당 날짜의 데이터 추가
				        lineChart2.data.datasets.push({
				            label: entry.Title,
				            data: data,
				            borderColor: getRandomColor(), // 랜덤 색상 생성 함수 사용
				            fill: false,
				            pointRadius: 5, // 데이터 포인트의 크기 조절
				            pointStyle: 'circle' // 데이터 포인트를 원 모양으로 설정
				        });
				    } else {
				        // 차트에 해당 제목의 데이터셋이 있는 경우
				        var dataIndex = lineChart2.data.labels.findIndex(label => label === entry.Day);
				        lineChart2.data.datasets[existingDatasetIndex].data[dataIndex] = entry.Rank; // 해당
																									// 날짜의
																									// 데이터
																									// 업데이트
				    }
				});
				
				// 차트 업데이트
				lineChart2.update();
				
				
				// 차트에 데이터 추가
				Object.keys(chartData[0].Ranks).forEach(title => {
				    var data = [];
				    chartData.forEach(dayData => {
				        data.push(dayData.Ranks[title] || null);
				    });
				    lineChart2.data.datasets.push({
				        label: title,
				        data: data,
				        borderColor: getRandomColor(), // 랜덤 색상 생성 함수 사용
				        fill: false,
				        pointRadius: 5, // 데이터 포인트의 크기 조절
				        pointStyle: 'circle' // 데이터 포인트를 원 모양으로 설정
				    });
				});
				
				// 차트 업데이트
				lineChart2.update();
				
				// 랜덤한 색상을 반환하는 함수
				function getRandomColor() {
				    var letters = '0123456789ABCDEF';
				    var color = '#';
				    for (var i = 0; i < 6; i++) {
				        color += letters[Math.floor(Math.random() * 16)];
				    }
				    return color;
				}
				
				function submitForm(formId) {
				    var form = document.getElementById(formId);
				    form.submit();
				}
            }
            
    	});
    });
});