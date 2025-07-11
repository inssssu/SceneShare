let users = [
  { id: "user123", name: "홍길동", email: "hong@test.com", gender: "남성", status: "정상", joined: "2023-04-15" },
  { id: "admin567", name: "관리자", email: "admin@test.com", gender: "여성", status: "비정상", joined: "2022-12-01" }
];

function toggleUserStatus(userId) {
  const user = users.find(u => u.id === userId);
  if (!user) return;

  user.status = (user.status === "정상") ? "비정상" : "정상";
  renderUserList(document.getElementById("searchInput").value);
}

function deleteUser(userId) {
  if (confirm("정말 삭제하시겠습니까?")) {
    users = users.filter(u => u.id !== userId);
    renderUserList(document.getElementById("searchInput").value);
  }
}

function viewUserDetail(userId) {
  const user = users.find(u => u.id === userId);
  if (!user) return;

  document.getElementById("userTable").style.display = "none";
  document.getElementById("userDetailSection").style.display = "block";
  document.querySelector('.search-bar-container').style.display = 'none';

  document.getElementById("detailId").textContent = user.id;
  document.getElementById("detailName").textContent = user.name;
  document.getElementById("detailEmail").textContent = user.email;
  document.getElementById("detailGender").textContent = user.gender;
  document.getElementById("detailStatus").textContent = user.status;
  document.getElementById("detailJoined").textContent = user.joined;
}

function backToList() {
  document.getElementById("userTable").style.display = "table";
  document.getElementById("userDetailSection").style.display = "none";
  document.querySelector('.search-bar-container').style.display = 'flex';
}

function renderUserList(filter = "") {
  const tableBody = document.getElementById("userTableBody");
  tableBody.innerHTML = "";

  users
    .filter(user => user.id.includes(filter) || user.name.includes(filter))
    .forEach((user, index) => {
      const row = document.createElement("tr");
      const statusClass = user.status === "정상" ? "active" : "inactive";

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

      tableBody.appendChild(row);
    });
}

document.getElementById("searchInput").addEventListener("input", function () {
  renderUserList(this.value);
});

document.addEventListener("DOMContentLoaded", () => {
  renderUserList();
});