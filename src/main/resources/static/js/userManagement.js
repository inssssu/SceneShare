function toggleUserStatus(e) {
  const user = users.find(u => u.username === e);
  if (!user) return;

  user.status = (user.status === "정상") ? "비정상" : "정상";
  renderUserList(document.getElementById("searchInput").value);
}

function deleteUser(e) {
  if (confirm("정말 삭제하시겠습니까?")) {
    users = users.filter(u => u.username !== e);
    renderUserList(document.getElementById("searchInput").value);
  }
}

function renderUserList(filter = "") {
  const tableBody = document.getElementById("userTableBody");
  tableBody.innerHTML = "";

  users
    .filter(user => user.username.includes(filter) || user.name.includes(filter))
    .forEach((user, index) => {
      const row = document.createElement("tr");
      const statusClass = user.status === "정상" ? "active" : "inactive";

      row.innerHTML = `
        <td>${index + 1}</td>
        <td>${user.username}</td>
        <td>${user.name}</td>
        <td>${user.status}</td>
        <td>   
          <button class="detail-btn" onclick="viewUserDetail('${user.username}')">상세</button>
          <button class="status-btn ${statusClass}" onclick="toggleUserStatus('${user.username}')">정지</button>
          <button class="delete-btn" onclick="deleteUser('${user.username}')">삭제</button>
        </td>
      `;

      tableBody.appendChild(row);
    });
}

// document.getElementById("searchInput").addEventListener("input", function () {
//   renderUserList(this.value);
// });

document.addEventListener("DOMContentLoaded", () => {
  renderUserList();
});