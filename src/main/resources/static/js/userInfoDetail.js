
// URL의 쿼리스트링에서 특정 파라미터 값을 추출하는 함수
// 예) ?userId=user123 에서 userId 값 "user123" 추출
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search); // 현재 URL의 쿼리스트링 파싱
    return urlParams.get(param); // 해당 파라미터 값 반환 (없으면 null)
}

// 사용자 상세정보를 화면에 렌더링하는 함수
// userId를 받아서 users 배열에서 해당 사용자 찾고 정보를 각 HTML 요소에 넣음
function renderUserDetail(userId) {
    // userId와 일치하는 사용자 객체를 users 배열에서 찾음
    const user = users.find(u => u.id === userId);
    if (!user) {
        // 사용자가 없으면 알림창 표시 후 함수 종료
        alert("존재하지 않는 사용자입니다.");
        return;
    }

    // 사용자 정보를 각각의 HTML 요소에 텍스트로 채움
    document.getElementById("detailId").textContent = user.id;
    document.getElementById("detailName").textContent = user.name;
    document.getElementById("detailEmail").textContent = user.email;
    document.getElementById("detailGender").textContent = user.gender;
    document.getElementById("detailStatus").textContent = user.status;
    document.getElementById("detailJoined").textContent = user.joined;
}

// 뒤로가기 버튼 클릭 시 호출되는 함수
// 브라우저의 이전 페이지로 이동
function goBack() {
    history.back();
}

// 페이지가 로드될 때 실행되는 이벤트 핸들러
document.addEventListener("DOMContentLoaded", () => {
    // URL에서 userId 파라미터 값을 가져옴
    const userId = getQueryParam("userId");
    // userId가 없으면 경고창 표시 후 함수 종료
    if (!userId) {
        alert("사용자 ID가 전달되지 않았습니다.");
        return;
    }
    // userId가 있으면 해당 사용자의 상세정보 렌더링 함수 호출
    renderUserDetail(userId);
});
