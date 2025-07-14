const perPage = 15;    // 한 페이지당 게시물 수
let currentPage = 1;    // 현재 페이지
// localStorage에서 게시물 로드
function loadPosts() {
    return JSON.parse(localStorage.getItem("posts") || "[]");
}
// localStorage에 게시물 저장
function savePosts(posts) {
    localStorage.setItem("posts", JSON.stringify(posts));
}
// 페이지에 따라 데이터 자르기
function paginate(data, page) {
    const start = (page - 1) * perPage;
    return data.slice(start, start + perPage);
}
// 페이지 번호 렌더링
function renderPagination(totalItems) {
    const totalPages = Math.ceil(totalItems / perPage);
    const container = document.getElementById("pagination");
    container.innerHTML = '';
    for (let i = 1; i <= totalPages; i++) {
        const btn = document.createElement("button");
        btn.textContent = i;
        btn.onclick = () => {
            currentPage = i;
            renderPostList();
        };
        container.appendChild(btn);
    }
}
// 게시물 목록 렌더링
function renderPostList() {
    const tbody = document.querySelector("#postTable tbody");
    tbody.innerHTML = "";
    let posts = loadPosts();

    const filter = document.getElementById("searchInput").value.toLowerCase();
    const type = document.getElementById("filterType").value;
    // 타입 필터링 (공지/영화)
    if (type !== "all") {
        posts = posts.filter(p => p.type === type);
    }
    // 제목 + 내용 + 작성자 검색 필터
    posts = posts.filter(p => (p.title + p.content + (p.writer || "")).toLowerCase().includes(filter));
    // 정렬: 공지 먼저, 최신순
    posts.sort((a, b) => {
        if (a.type !== b.type) return a.type === "notice" ? -1 : 1;
        return b.createdAt.localeCompare(a.createdAt);
    });
    // 페이지네이션 렌더링
    renderPagination(posts.length);
    const paginated = paginate(posts, currentPage);
    // 게시물 행 생성
    paginated.forEach(post => {
        const row = document.createElement("tr");
        row.classList.add("clickable");
        row.onclick = () => viewPostDetail(post.id);    // 클릭 시 상세 보기

        const number = post.type === "notice" ? "공지" : post.id;
        row.innerHTML = `
                <td>${number}</td>
                <td>${post.title}</td>
                <td>${post.content}</td>
                <td>${post.writer || '-'}</td>
                <td>${post.updatedAt}</td>
                <td>${post.views}</td>
                <td><button class="delete-btn" onclick="event.stopPropagation(); deletePost('${post.id}')">삭제</button></td>`;
        tbody.appendChild(row);
    });
}
// 게시물 상세 보기 + 조회수 증가
function viewPostDetail(id) {
    const posts = loadPosts();
    const index = posts.findIndex(p => p.id === id);
    if (index === -1) return alert("게시물을 찾을 수 없습니다.");

    posts[index].views++;   // 조회수 증가
    savePosts(posts);   // 저장

    const post = posts[index];
    const detail = document.getElementById("postDetailSection");
    detail.style.display = "block";
    document.getElementById("tableContainer").style.display = "none";

    let html = `<h3>${post.type === 'notice' ? '공지사항' : '영화정보'} 상세 보기</h3>`;
    // 공지사항일 경우
    if (post.type === 'notice') {
        html += `
                <p><strong>제목:</strong> ${post.title}</p>
                <p><strong>내용:</strong> ${post.content}</p>
                <p><strong>첨부파일:</strong> ${post.file ? `<a href="${post.file}" download>${post.file}</a>` : '-'}</p>
                <p><strong>작성일:</strong> ${post.createdAt}</p>
                <p><strong>수정일:</strong> ${post.updatedAt}</p>
                <p><strong>조회수:</strong> ${post.views}</p>`;
    } else {
        // 영화정보일 경우
        html += `
                <p><strong>제목:</strong> ${post.title}</p>
                <p><strong>개봉일:</strong> ${post.releaseDate || '-'}</p>
                <p><strong>평점:</strong> ${post.rating || '-'}</p>
                <p><strong>장르:</strong> ${post.genre || '-'}</p>
                <p><strong>감독:</strong> ${post.director || '-'}</p>
                <p><strong>출연진:</strong> ${post.cast || '-'}</p>
                <p><strong>내용:</strong> ${post.content}</p>
                <p><strong>작성자:</strong> ${post.writer || '-'}</p>
                <p><strong>작성일:</strong> ${post.createdAt}</p>
                <p><strong>수정일:</strong> ${post.updatedAt}</p>
                <p><strong>조회수:</strong> ${post.views}</p>`;
    }

    html += `<div id="detailControls">  
                <button class="back-btn" onclick="backToList()">목록</button>`;    // 버튼들을 감싸는 컨테이너(div) 입니다.
    if (post.type === 'notice') {
        html += `<button class="edit-btn" onclick="editPost('${post.id}')">수정</button>`;  // 조건문입니다: 공지사항(notice) 타입인 경우에만 실행됨. 
    }   // 게시물(movie) 에는 “수정” 버튼이 안 보이게 하려는 의도
    html += `<button class="delete-btn" onclick="deletePost('${post.id}')">삭제</button></div>`;    // 모든 게시물(공지, 영화 상관없이) 에 대해 “삭제” 버튼을 추가합니다.
    detail.innerHTML = html;

    renderPostList(); // 목록에서 조회수도 즉시 반영
}
// 게시물 삭제
function deletePost(id) {
    if (!confirm("정말 삭제하시겠습니까?")) return;
    const posts = loadPosts().filter(p => p.id !== id);
    savePosts(posts);
    backToList();   // 상세보기 닫기
    renderPostList();   // 목록 갱신
}
// 공지사항 수정 → 등록 페이지로 id 전달
function editPost(id) {
    window.location.href = `notice-form.html?id=${id}`;
}
// 목록 화면으로 복귀
function backToList() {
    document.getElementById("postDetailSection").style.display = "none";
    document.getElementById("tableContainer").style.display = "block";
}
// 필터 및 검색 이벤트 리스너 등록
document.getElementById("searchInput").addEventListener("input", renderPostList);
document.getElementById("filterType").addEventListener("change", renderPostList);
// 초기 로딩 시 게시물 렌더링
window.onload = () => {
    renderPostList();
};