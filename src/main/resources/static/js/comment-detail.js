// === mock 데이터: 예시 코멘트 목록 ===
const comments = [
    { id: 1, userId: "user123", movie: "인터스텔라", type: "comment", content: "정말 감동적인 영화였습니다.", date: "2024-07-01", parentId: null },
    { id: 2, userId: "user123", movie: "인터스텔라", type: "reply", content: "저도 그렇게 생각해요!", date: "2024-07-02", parentId: 1 },
    { id: 3, userId: "user123", movie: "다크 나이트", type: "re-reply", content: "댓글 감사합니다!", date: "2024-07-03", parentId: 2 },
    { id: 4, userId: "user123", movie: "다크 나이트", type: "comment", content: "히스 레저의 연기가 대단했어요.", date: "2024-07-01", parentId: null },
    { id: 5, userId: "user123", movie: "인터스텔라", type: "comment", content: "이 영화는 정말 최고의 작품이에요!", date: "2024-07-04", parentId: null },
    { id: 6, userId: "user123", movie: "인터스텔라", type: "reply", content: "정말 감동적이죠!", date: "2024-07-05", parentId: 1 },
    { id: 7, userId: "user123", movie: "다크 나이트", type: "re-reply", content: "히스 레저 최고!", date: "2024-07-06", parentId: 4 },
    { id: 8, userId: "user123", movie: "다크 나이트", type: "comment", content: "다크 나이트는 정말 대작이었어요.", date: "2024-07-07", parentId: null },
    { id: 9, userId: "user123", movie: "인터스텔라", type: "comment", content: "이 영화의 스토리가 정말 훌륭했어요.", date: "2024-07-08", parentId: null },
    { id: 10, userId: "user123", movie: "인터스텔라", type: "comment", content: "이 영화의 스토리가 정말 훌륭했어요.", date: "2024-07-08", parentId: null },
    { id: 11, userId: "user123", movie: "인터스텔라", type: "comment", content: "이 영화의 스토리가 정말 훌륭했어요.", date: "2024-07-08", parentId: null }
];
// 현재 삭제 대기중인 코멘트 ID 저장용
let selectedCommentId = null;
// id별 코멘트 조회용 Map (빠른 부모 찾기 위해)
const commentMap = new Map(comments.map(c => [c.id, c]));

// 코멘트 목록 렌더링 함수
function renderComments() {
    const section = document.getElementById("commentSections");
    section.innerHTML = ""; // 초기화

    // 현재 페이지에 해당하는 코멘트만 추출
    const start = (currentPage - 1) * commentsPerPage;
    const end = start + commentsPerPage;
    const currentComments = comments.slice(start, end); // 현재 페이지에 해당하는 댓글

    // 영화별 그룹핑 (movie 이름이 key)
    const grouped = {};
    currentComments.forEach(item => {
        if (!grouped[item.movie]) grouped[item.movie] = [];
        grouped[item.movie].push(item);
    });

    // 영화별 섹션 생성
    for (const movie in grouped) {
        const movieSection = document.createElement("div");
        movieSection.className = "movie-section";

        // 영화 제목
        const title = document.createElement("h2");
        title.className = "movie-title";
        title.textContent = movie;
        movieSection.appendChild(title);

        // 해당 영화에 대한 각 코멘트 렌더링
        grouped[movie].forEach(comment => {
            const card = document.createElement("div");
            card.className = "comment-card";

            // 왼쪽: 뱃지, (댓글/대댓글인 경우 부모 작성자 ID), 내용
            const leftDiv = document.createElement("div");
            leftDiv.className = "comment-info";

            // 댓글/대댓글이면 부모 작성자 ID 표시
            if (comment.type !== "comment" && comment.parentId) {
                const parent = commentMap.get(comment.parentId);
                const parentUserId = parent ? parent.userId : "알 수 없음";
                const parentSpan = document.createElement("span");
                parentSpan.className = "comment-id";
                parentSpan.textContent = `@${parentUserId} 님의 글`;    // 부모 작성자 ID
                leftDiv.appendChild(parentSpan);
            }

            // 유형 뱃지 생성 (코멘트/댓글/대댓글 구분)
            const badge = document.createElement("span");
            badge.className = `badge ${comment.type}`;
            badge.textContent = comment.type === "comment" ? "코멘트" : comment.type === "reply" ? "댓글" : "대댓글";
            leftDiv.appendChild(badge);

            // 코멘트 내용
            const contentSpan = document.createElement("span");
            contentSpan.className = "comment-content";
            contentSpan.textContent = comment.content;
            leftDiv.appendChild(contentSpan);

            card.appendChild(leftDiv);

            // 오른쪽: 작성일 + 삭제 버튼 우측 정렬
            const rightDiv = document.createElement("div");
            rightDiv.className = "comment-right";

            const dateSpan = document.createElement("span");
            dateSpan.textContent = comment.date;
            rightDiv.appendChild(dateSpan);

            const deleteBtn = document.createElement("button");
            deleteBtn.className = "delete-btn";
            deleteBtn.textContent = "삭제";
            deleteBtn.onclick = () => confirmDelete(comment.id);
            rightDiv.appendChild(deleteBtn);

            card.appendChild(rightDiv);

            movieSection.appendChild(card);
        });
        section.appendChild(movieSection);
    }
}

// 페이지네이션 변수 설정
const commentsPerPage = 10; // 한 페이지에 보일 댓글 수
let currentPage = 1; // 현재 페이지
let totalPages = Math.ceil(comments.length / commentsPerPage); // 총 페이지 수

// 페이지네이션 버튼 렌더링 함수
function renderPagination() {
    const paginationDiv = document.getElementById("pagination");
    paginationDiv.innerHTML = ""; // 초기화

    // totalPages가 1 이상일 때만 페이지네이션 버튼을 표시
    if (totalPages > 1) {
        // 이전 버튼
        const prevButton = document.createElement("button");
        prevButton.textContent = "이전";
        prevButton.disabled = currentPage === 1;
        prevButton.onclick = () => changePage(currentPage - 1);
        paginationDiv.appendChild(prevButton);

        // 페이지 번호
        const pageButton = document.createElement("span");
        pageButton.textContent = `${currentPage} / ${totalPages}`;
        paginationDiv.appendChild(pageButton);

        // 다음 버튼
        const nextButton = document.createElement("button");
        nextButton.textContent = "다음";
        nextButton.disabled = currentPage === totalPages;
        nextButton.onclick = () => changePage(currentPage + 1);
        paginationDiv.appendChild(nextButton);
    }
}

// 페이지 변경 함수
function changePage(page) {
    currentPage = page;
    renderComments();
    renderPagination();
}

// 삭제 확인 모달 열기 함수
function confirmDelete(id) {
    selectedCommentId = id;
    document.getElementById("modalOverlay").style.display = "flex";
}

// 삭제 확인 모달 닫기 함수
function closeModal() {
    selectedCommentId = null;
    document.getElementById("modalOverlay").style.display = "none";
}

// 삭제 확정 시 실행 함수
function deleteConfirmed() {
    if (selectedCommentId != null) {
        // 실제 백엔드 API 요청은 여기에 구현할 예정입니다.
        // TODO: API 요청 보내서 삭제 처리

        // 현재는 로컬 배열에서 삭제 처리 (mock)
        const index = comments.findIndex(c => c.id === selectedCommentId);
        if (index !== -1) {
            comments.splice(index, 1);
            // 삭제 후 id-댓글 맵 갱신
            commentMap.clear();
            comments.forEach(c => commentMap.set(c.id, c));
        }
        renderComments(); // 삭제 후 UI 재렌더링
    }
    closeModal();
}
// 초기 렌더링 호출
renderComments();
renderPagination();