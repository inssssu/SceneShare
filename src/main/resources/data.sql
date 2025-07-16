insert into admin_account (admin_idx, admin_name, admin_password)
values (1, 'admin', 1234);

insert into user_account_entity (user_idx, username, password, name, email)
values
    (1, 'user1', '1234', '유저1', 'email1@bitc.com'),
    (2, 'user2', '1234', '유저2', 'email2@bitc.com'),
    (3, 'user3', '1234', '유저3', 'email3@bitc.com'),
    (4, 'user4', '1234', '유저4', 'email4@bitc.com'),
    (5, 'user5', '1234', '유저5', 'email5@bitc.com'),
    (6, 'user6', '1234', '유저6', 'email6@bitc.com'),
    (7, 'user7', '1234', '유저7', 'email7@bitc.com'),
    (8, 'user8', '1234', '유저8', 'email8@bitc.com'),
    (9, 'user9', '1234', '유저9', 'email9@bitc.com'),
    (10, 'user10', '1234', '유저10', 'email10@bitc.com'),
    (11, 'user11', '1234', '유저11', 'email11@bitc.com'),
    (12, 'user12', '1234', '유저12', 'email12@bitc.com'),
    (13, 'user13', '1234', '유저13', 'email13@bitc.com'),
    (14, 'user14', '1234', '유저14', 'email14@bitc.com'),
    (15, 'user15', '1234', '유저15', 'email15@bitc.com'),
    (16, 'user16', '1234', '유저16', 'email16@bitc.com');

insert into movie_info (movie_id, title, description, director, genre, type)
values
        (1, '슈퍼맨', '세상의 희망인가, 위협인가?', '제임스 건', '액션/모험/판타지', 'movie'),
        (2, '드래곤 길들이기', '드래곤을 없애는 것이 삶의 목적인 보통의 바이킹들과 달리', '딘 데블로이스', '모험', 'movie'),
        (3, 'F1 더 무비', '최고가 되지 못한 전설 VS 최고가 되고 싶은 루키', '조셉 코신스키', '스포츠/드라마', 'movie');

insert into notice_board (notice_id, title, contents, type)
values
    (1, '공지사항 첫번째글', '공지사항 첫번째 내용', 'notice'),
    (2, '공지사항 두번째글', '공지사항 두번째 내용', 'notice');