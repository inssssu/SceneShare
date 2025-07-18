const perPage = 30;
let currentPage = 1;

// 로컬스토리지에서 게시물 불러오기
// 영화 api 사용하는 localStorage? -> 더미데이터를 넣어서 사용한 내용이라 api 사용시 삭제
// function loadPosts() {
//     return JSON.parse(localStorage.getItem("posts") || "[]");
// }

// 로컬스토리지에 게시물 저장하기
// function savePosts(posts) {
//     localStorage.setItem("posts", JSON.stringify(posts));
// }

// 현재 페이지에 해당하는 게시물만 추출
function paginate(data, page) {
    const start = (page - 1) * perPage;
    return data.slice(start, start + perPage);
}

// 페이지네이션 렌더링
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

    // 검색 및 필터 처리
    const filter = document.getElementById("searchInput").value.toLowerCase();
    const type = document.getElementById("filterType").value;
    if (type !== "all") {
        posts = posts.filter(p => p.type === type);
    }

    // 검색어 필터링
    posts = posts.filter(p => (p.title + p.content + (p.writer || "")).toLowerCase().includes(filter));

    // 공지사항 우선 정렬 + 작성일 역순
    posts.sort((a, b) => {
        if (a.type !== b.type) return a.type === "notice" ? -1 : 1;
        return b.createdAt.localeCompare(a.createdAt);
    });

    const total = posts.length;
    renderPagination(total);
    const paginated = paginate(posts, currentPage);

    // 테이블 행 추가
    paginated.forEach((post, index) => {
        const row = document.createElement("tr");
        row.classList.add("clickable");
        row.onclick = () => viewPostDetail(post.id);

        const number = post.type === "notice"
            ? "공지"
            : total - ((currentPage - 1) * perPage + index);

        row.innerHTML = `
            <td>${number}</td>
            <td>${post.title}</td>
            <td>${post.content}</td>
            <td>${post.createdAt}</td>
            <td>${post.views}</td>
            <td><button class="delete-btn" onclick="event.stopPropagation(); deletePost('${post.id}')">삭제</button></td>`;
        tbody.appendChild(row);
    });
}

// 게시물 상세 보기
function viewPostDetail(id) {
    const posts = loadPosts();
    const index = posts.findIndex(p => p.id === id);
    if (index === -1) return alert("게시물을 찾을 수 없습니다.");

    posts[index].views++;
    savePosts(posts);
    const post = posts[index];

    const detail = document.getElementById("postDetailSection");
    detail.style.display = "block";
    document.getElementById("tableContainer").style.display = "none";

    let html = `<h3>${post.type === 'notice' ? '공지사항' : '영화정보'} 상세 보기</h3>`;

    if (post.type === 'notice') {
        html += `
            <p><strong>제목:</strong> ${post.title}</p>
            <p><strong>내용:</strong> ${post.content}</p>
            <p><strong>첨부파일:</strong> ${post.file ? `<a href="${post.file}" download>${post.file}</a>` : '-'}</p>
            <p><strong>작성일:</strong> ${post.createdAt}</p>
            <p><strong>수정일:</strong> ${post.updatedAt}</p>
            <p><strong>조회수:</strong> ${post.views}</p>`;
    } else {
        html += `
            <p><strong>제목:</strong> ${post.title}</p>
            <p><strong>개봉일:</strong> ${post.releaseDate || '-'}</p>
            <p><strong>평점:</strong> ${post.rating ? `${post.rating} / 5` : '-'}</p>
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
        <button class="back-btn" onclick="backToList()">목록</button>`;
    if (post.type === 'notice') {
        html += `<button class="edit-btn" onclick="editPost('${post.id}')">수정</button>`;
    }
    html += `<button class="delete-btn" onclick="deletePost('${post.id}')">삭제</button></div>`;

    detail.innerHTML = html;
    renderPostList(); // 조회수 반영을 위해 목록 재렌더링
}

// 게시물 삭제
function deletePost(id) {
    if (!confirm("정말 삭제하시겠습니까?")) return;
    const posts = loadPosts().filter(p => p.id !== id);
    savePosts(posts);
    backToList();
    renderPostList();
}

// 게시물 수정
function editPost(id) {
    window.location.href = `notice-form.html?id=${id}`;
}

// 목록으로 돌아가기
function backToList() {
    document.getElementById("postDetailSection").style.display = "none";
    document.getElementById("tableContainer").style.display = "block";
}

// 검색 및 필터 이벤트 바인딩
document.getElementById("searchInput").addEventListener("input", renderPostList);
document.getElementById("filterType").addEventListener("change", renderPostList);

// 초기 렌더링
window.onload = () => renderPostList();