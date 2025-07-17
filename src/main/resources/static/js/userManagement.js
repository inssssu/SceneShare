// 초기 사용자 목록 데이터: 실제 환경에서는 서버 API로부터 받아오는 부분
let users = [
  { id: "user123", name: "홍길동", email: "hong@test.com", gender: "남성", status: "정상", joined: "2023-04-15" },
  { id: "admin567", name: "관리자", email: "admin@test.com", gender: "여성", status: "비정상", joined: "2022-12-01" }
];

// 사용자 상태를 토글하는 함수 (정상 ↔ 비정상 상태 변경)
function toggleUserStatus(userId) {
  // 전달받은 userId와 일치하는 사용자 객체를 배열에서 찾음
  const user = users.find(u => u.id === userId);
  if (!user) return; // 사용자가 없으면 함수 종료

  // 현재 상태가 "정상"이면 "비정상"으로, 아니면 "정상"으로 상태 변경
  user.status = (user.status === "정상") ? "비정상" : "정상";

  // 상태 변경 후 현재 검색어 기준으로 사용자 목록 다시 렌더링
  renderUserList(document.getElementById("searchInput").value);
}

// 사용자 삭제 함수
function deleteUser(userId) {
  // 삭제 전 확인 창 표시
  if (confirm("정말 삭제하시겠습니까?")) {
    // 삭제 대상 사용자를 제외한 새 배열 생성
    users = users.filter(u => u.id !== userId);

    // 삭제 후 현재 검색어 기준으로 목록 다시 렌더링
    renderUserList(document.getElementById("searchInput").value);
  }
}

// 상세보기 페이지로 이동하는 함수
function viewUserDetail(userId) {
  // 상세보기 페이지(userInfoDetail.html)로 userId를 쿼리스트링에 붙여서 이동
  // encodeURIComponent로 특수문자 안전하게 인코딩
  window.location.href = `/userInfoDetail.html?userId=${encodeURIComponent(userId)}`;
}

// 사용자 목록을 HTML 테이블에 렌더링하는 함수 (검색어 필터 포함)
function renderUserList(filter = "") {
  // 테이블 tbody 요소를 가져옴
  const tableBody = document.getElementById("userTableBody");
  tableBody.innerHTML = ""; // 기존 내용을 초기화

  // users 배열에서 검색어가 포함된 사용자만 필터링
  users
    .filter(user => user.id.includes(filter) || user.name.includes(filter))
    .forEach((user, index) => {
      // 새로운 테이블 행 생성
      const row = document.createElement("tr");

      // 사용자 상태에 따라 버튼 클래스 결정 (정상: active, 비정상: inactive)
      const statusClass = user.status === "정상" ? "active" : "inactive";

      // 행 내부 HTML 구성: 번호, 아이디, 상태, 관리 버튼 3개(상세, 정지, 삭제)
      row.innerHTML = `
        <td>${index + 1}</td>
        <td>${user.id}</td>
        <td>${user.status}</td>
        <td>
          <button class="detail-btn" onclick="viewUserDetail('${user.id}')">상세</button>
          <button class="status-btn ${statusClass}" onclick="toggleUserStatus('${user.id}')">정지</button>
          <button class="delete-btn" onclick="deleteUser('${user.id}')">삭제</button>
        </td>
      `;

      // 테이블 tbody에 새 행 추가
      tableBody.appendChild(row);
    });
}

// 검색 입력창에 이벤트 등록: 입력값이 변경될 때마다 필터링된 사용자 목록을 갱신
document.getElementById("searchInput").addEventListener("input", function () {
  renderUserList(this.value);
});

// DOM이 완전히 로드된 후 초기 사용자 목록 렌더링 실행
document.addEventListener("DOMContentLoaded", () => {
  renderUserList();
});