// 📌 수정 모드 여부 확인: URL에 ?id= 값이 있으면 수정 모드
const urlParams = new URLSearchParams(window.location.search);
const editId = urlParams.get('id'); // URL에 id가 있을 경우 수정 모드
// 📌 주요 DOM 요소 참조
const formTitle = document.getElementById('formTitle');
const titleInput = document.getElementById('title');
const contentInput = document.getElementById('content');
const fileInput = document.getElementById('file');
const existingFileText = document.getElementById('existingFile');
// 📌 기존 게시물 로드
let posts = JSON.parse(localStorage.getItem("posts") || "[]");
let editingPost = null;

// ✏️ 수정 모드일 경우 기존 데이터 폼에 채우기
if (editId) {
    editingPost = posts.find(p => p.id === editId);
    if (editingPost) {
        formTitle.textContent = '공지사항 수정';
        titleInput.value = editingPost.title;
        contentInput.value = editingPost.content;
        // 기존 첨부파일 이름 표시
        if (editingPost.file) {
            existingFileText.textContent = `기존 첨부파일: ${editingPost.file}`;
        }
    }
}
// 📤 폼 제출 처리 (등록 또는 수정)
document.getElementById("noticeForm").addEventListener("submit", function (e) {
    e.preventDefault();    // 기본 폼 제출 막기
    // 입력값 추출
    const title = titleInput.value.trim();
    const content = contentInput.value.trim();
    const fileName = fileInput.files.length > 0 ? fileInput.files[0].name : (editingPost?.file || "");
    // 필수 입력값 체크
    if (!title || !content) {
        alert("제목과 내용을 모두 입력해주세요.");
        return;
    }

    const today = new Date().toISOString().split("T")[0];

    if (editId && editingPost) {
        // ✏️ 수정 모드 처리
        editingPost.title = title;
        editingPost.content = content;
        editingPost.file = fileName;
        editingPost.updatedAt = today;

        const index = posts.findIndex(p => p.id === editId);
        posts[index] = editingPost;
        alert("공지사항이 수정되었습니다.");
    } else {
        // 🆕 등록 모드 처리
        const newPost = {
            id: "notice_" + Date.now(),    // 고유 ID 생성
            type: "notice",    // 게시물 유형
            title,
            content,
            file: fileName,
            createdAt: today,
            updatedAt: today,
            views: 0,   // 초기 조회수
            writer: "관리자"
        };
        posts.unshift(newPost);    // 최신 게시물이 가장 위로 오게 함
        alert("공지사항이 등록되었습니다.");
    }
    // 📦 변경된 데이터 저장 및 게시물 관리 페이지로 이동
    localStorage.setItem("posts", JSON.stringify(posts));
    window.location.href = "post.html";
});