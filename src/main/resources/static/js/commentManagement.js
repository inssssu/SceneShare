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