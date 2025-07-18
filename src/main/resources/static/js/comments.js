// 더미 유저 데이터 60명 생성
let users = Array.from({ length: 60 }, (_, i) => {
    const day = (28 - (i % 28)).toString().padStart(2, '0');
    const month = (9 - (i % 9)).toString().padStart(2, '0');
    return {
<<<<<<< Updated upstream
        id: `user${String(i + 1).padStart(3, '0')}`,
        name: `사용자${i + 1}`,
        joinDate: `2024-${month}-${day}`,
        status: i % 4 === 0 ? '비정상' : '정상',
    };
});
// 1페이지당 30개 리스트 
let currentPage = 1;
const itemsPerPage = 30;

// 정렬 상태 관리 변수
let sortField = 'joinDate'; // 기본 정렬 기준
let sortDirection = 'desc'; // 기본 내림차순

// 테이블 렌더링
function renderUserTable() {
    const keyword = document.getElementById('searchUser').value.toLowerCase();
    const tbody = document.querySelector('#userTable tbody');
    tbody.innerHTML = '';

    // 필터링 + 정렬
=======
        id: `user${String(i + 1).padStart(3, '0')}`,  // 고유 사용자 ID 생성
        name: `사용자${i + 1}`,                         // 사용자 이름
        joinDate: `2024-${month}-${day}`,              // 가입일 (더미 날짜)
        status: i % 4 === 0 ? '비정상' : '정상',       // 상태: 4번째마다 비정상으로 설정
    };
});

let currentPage = 1;               // 현재 페이지 번호 (초기값 1)
const itemsPerPage = 30;           // 한 페이지 당 보여줄 아이템 수

// 정렬 상태 관리 변수
let sortField = 'joinDate';        // 기본 정렬 기준: 가입일
let sortDirection = 'desc';        // 기본 정렬 방향: 내림차순

// 테이블 렌더링 함수
function renderUserTable() {
    const keyword = document.getElementById('searchUser').value.toLowerCase();  // 검색어 소문자 변환
    const tbody = document.querySelector('#userTable tbody');
    tbody.innerHTML = '';  // 테이블 본문 초기화

    // 필터링: 사용자 ID 또는 이름에 검색어 포함된 것만 필터링
    // 정렬: 현재 정렬 기준과 방향에 따라 정렬
>>>>>>> Stashed changes
    const filteredUsers = users
        .filter(user =>
            user.id.toLowerCase().includes(keyword) ||
            user.name.toLowerCase().includes(keyword)
        )
        .sort((a, b) => compare(a, b));

<<<<<<< Updated upstream
    const start = (currentPage - 1) * itemsPerPage;
    const end = start + itemsPerPage;

    // 번호는 필터링된 전체 기준으로 역순 계산
    filteredUsers.slice(start, end).forEach((user, index) => {
        const reversedNumber = filteredUsers.length - (start + index); // 전체 기준 역순 번호
=======
    // 현재 페이지 범위만큼 잘라서 가져오기
    const start = (currentPage - 1) * itemsPerPage;
    const end = start + itemsPerPage;

    // 번호 계산: 필터된 전체 기준으로 역순 번호 부여
    filteredUsers.slice(start, end).forEach((user, index) => {
        const reversedNumber = filteredUsers.length - (start + index);
>>>>>>> Stashed changes
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${reversedNumber}</td>
            <td>${user.joinDate}</td>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.status}</td>
            <td class="manage">
                <button onclick="goToDetail('${user.id}')">상세</button>
                <button onclick="deleteUser('${user.id}')">삭제</button>
            </td>`;
        tbody.appendChild(tr);
    });

<<<<<<< Updated upstream
    renderPagination(filteredUsers.length);
}

// 정렬 함수 (각 열을 기준으로 정렬)
=======
    renderPagination(filteredUsers.length);  // 페이징 버튼 렌더링
}

// 정렬 비교 함수 (각 컬럼별 정렬 처리)
>>>>>>> Stashed changes
function compare(a, b) {
    let result = 0;

    if (sortField === 'joinDate') {
<<<<<<< Updated upstream
        result = new Date(a.joinDate) - new Date(b.joinDate);
    } else if (sortField === 'name') {
        result = a.name.localeCompare(b.name);
    } else if (sortField === 'status') {
        const statusOrder = { '정상': 1, '비정상': 2 }; // 상태를 숫자로 매핑
        result = statusOrder[a.status] - statusOrder[b.status];
    }

    return sortDirection === 'asc' ? result : -result;
}

// 정렬 클릭 시 호출되는 함수
function sortTable(column) {
    if (sortField === column) {
        sortDirection = sortDirection === 'asc' ? 'desc' : 'asc'; // 오름차순/내림차순 토글
    } else {
        sortField = column; // 새로 클릭한 컬럼을 기준으로 정렬
        sortDirection = 'desc'; // 기본 내림차순
    }
    renderUserTable(); // 정렬 후 테이블 재렌더링
}

// 상세 페이지 이동
function goToDetail(userId) {
    location.href = `comment-detail.html?id=${encodeURIComponent(userId)}`;
}

// 유저 삭제
function deleteUser(userId) {
    if (confirm(`${userId} 사용자를 삭제하시겠습니까?`)) {
        users = users.filter(user => user.id !== userId);
        renderUserTable();
    }
}

// 페이징 버튼 렌더링
function renderPagination(totalItems) {
    const paginationEl = document.getElementById('pagination');
    paginationEl.innerHTML = '';

    const pageCount = Math.ceil(totalItems / itemsPerPage);
=======
        // 날짜를 실제 Date 객체로 변환해 비교
        result = new Date(a.joinDate) - new Date(b.joinDate);
    } else if (sortField === 'name') {
        // 이름 문자열 비교 (알파벳순)
        result = a.name.localeCompare(b.name);
    } else if (sortField === 'status') {
        // 상태를 숫자에 매핑해 비교 ('정상' < '비정상')
        const statusOrder = { '정상': 1, '비정상': 2 };
        result = statusOrder[a.status] - statusOrder[b.status];
    }

    // 정렬 방향에 따라 결과 반전
    return sortDirection === 'asc' ? result : -result;
}

// 테이블 헤더 클릭 시 정렬 방향 변경 함수
function sortTable(column) {
    if (sortField === column) {
        // 같은 컬럼 클릭 시 정렬 방향 토글
        sortDirection = sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
        // 새로운 컬럼 클릭 시 정렬 기준 변경, 기본 내림차순 설정
        sortField = column;
        sortDirection = 'desc';
    }
    renderUserTable();  // 정렬 변경 후 테이블 재렌더링
}

// 상세 페이지 이동 함수
function goToDetail(userId) {
    // 백엔드 연동 시 URL에 userId 파라미터 포함해서 상세페이지로 이동
    location.href = `comment-detail.html?id=${encodeURIComponent(userId)}`;
    // 실제 API 호출은 상세 페이지에서 구현
}

// 유저 삭제 함수
function deleteUser(userId) {
    if (confirm(`${userId} 사용자를 삭제하시겠습니까?`)) {
        // 백엔드 API 호출 시:
        // fetch(`/api/users/${userId}`, { method: 'DELETE' })
        //   .then(response => { if (response.ok) { /* 성공 처리 */ }});

        // 현재는 로컬 데이터에서 삭제 처리
        users = users.filter(user => user.id !== userId);
        renderUserTable();  // 삭제 후 테이블 재렌더링
    }
}

// 페이징 버튼 렌더링 함수
function renderPagination(totalItems) {
    const paginationEl = document.getElementById('pagination');
    paginationEl.innerHTML = ''; // 초기화

    const pageCount = Math.ceil(totalItems / itemsPerPage); // 전체 페이지 수 계산
>>>>>>> Stashed changes
    for (let i = 1; i <= pageCount; i++) {
        const btn = document.createElement('button');
        btn.textContent = i;
        if (i === currentPage) btn.classList.add('active');
        btn.addEventListener('click', () => {
            currentPage = i;
<<<<<<< Updated upstream
            renderUserTable();
=======
            renderUserTable(); // 페이지 변경 후 테이블 재렌더링
>>>>>>> Stashed changes
        });
        paginationEl.appendChild(btn);
    }
}

// 검색 입력 이벤트 바인딩
document.getElementById('searchUser').addEventListener('input', () => {
<<<<<<< Updated upstream
    currentPage = 1;
    renderUserTable();
});

// 페이지 로드 시 테이블 렌더링
=======
    currentPage = 1;      // 검색시 페이지 초기화
    renderUserTable();    // 검색어 변경 시 테이블 재렌더링
});

// 초기 페이지 로드 시 테이블 렌더링
>>>>>>> Stashed changes
renderUserTable();