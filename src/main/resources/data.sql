insert into admin_account (admin_idx, admin_name, admin_password)
values (1, 'admin', 1234);

insert into user_account (user_idx, username, name, status)
values
    (1, 'user1', '황일수', '정상'),
    (2, 'user2', '황이수', '정상'),
    (3, 'user3', '황삼수', '정상'),
    (4, 'user4', '황사수', '정상'),
    (5, 'user5', '황오수', '정상'),
    (6, 'user6', '황육수', '비정상'),
    (7, 'user7', '황칠수', '정상'),
    (8, 'user8', '황팔수', '정상'),
    (9, 'user9', '황구수', '정상'),
    (10, 'user10', '황십수', '정상'),
    (11, 'user11', '황십일수', '정상'),
    (12, 'user12', '황십이수', '비정상'),
    (13, 'user13', '황십삼수', '정상'),
    (14, 'user14', '황십사수', '정상'),
    (15, 'user15', '황십오수', '정상'),
    (16, 'user16', '황십육수', '정상'),
    (17, 'user17', '황십칠수', '정상');

insert into movie_info (movie_id, title, description, director, genre, type, create_date, hit_cnt)
values
        (1, '슈퍼맨', '세상의 희망인가, 위협인가?', '제임스 건', '액션/모험/판타지', 'movie',NOW(), 23),
        (2, '드래곤 길들이기', '드래곤을 없애는 것이 삶의 목적인 보통의 바이킹들과 달리', '딘 데블로이스', '모험', 'movie', NOW(), 12),
        (3, 'F1 더 무비', '최고가 되지 못한 전설 VS 최고가 되고 싶은 루키', '조셉 코신스키', '스포츠/드라마', 'movie', NOW(), 10),
        (4, '공지사항 첫번째글', '공지사항 첫번째 내용', '관리자심씨', '', 'notice', NOW(), 303),
        (5, '공지사항 두번째글', '공지사항 두번째 내용', '관리자황씨', '', 'notice', NOW(), 204);

