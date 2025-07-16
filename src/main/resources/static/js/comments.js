// 더미 유저 데이터 60명 생성
let users = Array.from({ length: 60 }, (_, i) => {
    const day = (28 - (i % 28)).toString().padStart(2, '0');
    const month = (9 - (i % 9)).toString().padStart(2, '0');
    return {
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
    const filteredUsers = users
        .filter(user =>
            user.id.toLowerCase().includes(keyword) ||
            user.name.toLowerCase().includes(keyword)
        )
        .sort((a, b) => compare(a, b));

    const start = (currentPage - 1) * itemsPerPage;
    const end = start + itemsPerPage;

    // 번호는 필터링된 전체 기준으로 역순 계산
    filteredUsers.slice(start, end).forEach((user, index) => {
        const reversedNumber = filteredUsers.length - (start + index); // 전체 기준 역순 번호
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

    renderPagination(filteredUsers.length);
}

// 정렬 함수 (각 열을 기준으로 정렬)
function compare(a, b) {
    let result = 0;

    if (sortField === 'joinDate') {
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
    for (let i = 1; i <= pageCount; i++) {
        const btn = document.createElement('button');
        btn.textContent = i;
        if (i === currentPage) btn.classList.add('active');
        btn.addEventListener('click', () => {
            currentPage = i;
            renderUserTable();
        });
        paginationEl.appendChild(btn);
    }
}

// 검색 입력 이벤트 바인딩
document.getElementById('searchUser').addEventListener('input', () => {
    currentPage = 1;
    renderUserTable();
});

// 페이지 로드 시 테이블 렌더링
renderUserTable();