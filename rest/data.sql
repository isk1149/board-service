{
  "writer": "lee",
  "subject": "good news",
  "content": "정부가 양자·인공지능(AI)과 첨단 바이오를 '3대 게임체인저'로 지정하고, 성과 창출을 본격화한다. AI를 활용해 국민 삶의 질을 향상하는 사업에는 7천억원 넘는 돈을 투자한다.  양자인터넷 전송 거리를 연말까지 지금의 1천배(100㎞)로 늘리고, 과학기술의학전문대학원 신설도 추진한다."
}
{
  "writer": "kim",
  "subject": "bad news",
  "content": "Spring Data JPA에서 제공하는 JpaRepository 인터페이스를 상속하기만 해도 되며, 인터페이스에 따로 @Repository등의 어노테이션을 추가할 필요가 없다."
}

insert into board(id, board_name, description, creator, created_date_time) values('7b9a7c55a9334d049e8a880cf216e28e', 'Q&A', 'Q&A게시판', 'admin', current_timestamp);
insert into board(id, board_name, description, creator, created_date_time) values('7b451b7e6cbe41e282f2d499c4fe2fcf', '지식', '지식게시판', 'admin', current_timestamp);
insert into board(id, board_name, description, creator, created_date_time) values('9292264cc3aa410b96d14c7ea2b5f335', '커뮤니티', '커뮤니티게시판', 'admin', current_timestamp);
insert into board(id, board_name, description, creator, created_date_time) values('3f08e148399248b9b6e3518e19b1e15b', '이벤트', '이벤트게시판', 'admin', current_timestamp);
insert into board(id, board_name, description, creator, created_date_time) values('4bfff7a041154a2c9dcb8d68b55417d9', '공지사항', '공지사항게시판', 'admin', current_timestamp);

insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('839ecf660f9a41038969fdc04f3d51bd', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글1', 'Q&A게시글1의 내용입니다.'
    , 'tester', 421, 45, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('6401419e42ed4012bd7d5cefcfb415fb', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글2', 'Q&A게시글2의 내용입니다.'
       , 'tester', 345, 23, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('861c11a63962460ea54df20ebdf4b57b', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글3', 'Q&A게시글3의 내용입니다.'
       , 'tester', 12, 1, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('4bfe190ee2074f339654c5f05bd692d1', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글4', 'Q&A게시글4의 내용입니다.'
       , 'tester', 1, 1, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('da6c9b8b7cc540feaf4a56e0cc8b52a9', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글5', 'Q&A게시글5의 내용입니다.'
       , 'tester', 0, 0, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('7766d7d979054b2aaadbbaebee87a706', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글6', 'Q&A게시글6의 내용입니다.'
       , 'tester', 0, 0, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('100977274dac4d8cb9c3402d98b78457', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글7', 'Q&A게시글7의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('100977274dac4d8cb9c3412d98b78457', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글8', 'Q&A게시글8의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('100974274dac4d8cb9c3412d98b78457', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글9', 'Q&A게시글9의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('100974274dac4abcd9c3412d98b78457', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글10', 'Q&A게시글10의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('1009742sddac4abcd9c3412d98b78457', '7b9a7c55a9334d049e8a880cf216e28e', 'Q&A게시글11', 'Q&A게시글11의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);

insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('a8e8a232645d4aad9f90168c1e24ecab', '7b451b7e6cbe41e282f2d499c4fe2fcf', '지식게시글1', '지식게시글1의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('05be25ba73b74aac943baf140b26356d', '7b451b7e6cbe41e282f2d499c4fe2fcf', '지식게시글2', '지식게시글2의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('91c40d063ec54d24bd3d4bbb63f87f24', '7b451b7e6cbe41e282f2d499c4fe2fcf', '지식게시글3', '지식게시글3의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('db4658609fe941e895d83daba045beae', '7b451b7e6cbe41e282f2d499c4fe2fcf', '지식게시글4', '지식게시글4의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('f26db94f04be49b99899b44a54c48179', '7b451b7e6cbe41e282f2d499c4fe2fcf', '지식게시글5', '지식게시글5의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('37ca9a1ad5744d9a8a3244f0b481f62c', '7b451b7e6cbe41e282f2d499c4fe2fcf', '지식게시글6', '지식게시글6의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('e2b4050e13c244f68befa3a84f6496db', '7b451b7e6cbe41e282f2d499c4fe2fcf', '지식게시글7', '지식게시글7의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('e2b4050e13c244f68befa3a84f649123`', '7b451b7e6cbe41e282f2d499c4fe2fcf', '지식게시글8', '지식게시글8의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);

insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('c2f21c7cc5054f6db749a2ee06a6b920', '9292264cc3aa410b96d14c7ea2b5f335', '커뮤니티게시글1', '커뮤니티게시글1의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('610d358206164dcf842b03441d99e3b2', '9292264cc3aa410b96d14c7ea2b5f335', '커뮤니티게시글2', '커뮤니티게시글2의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('6475ceb0d63f48858459b39fd7a65724', '9292264cc3aa410b96d14c7ea2b5f335', '커뮤니티게시글3', '커뮤니티게시글3의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('4ed40a3aa76444c48994bc77999afa7c', '9292264cc3aa410b96d14c7ea2b5f335', '커뮤니티게시글4', '커뮤니티게시글4의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('f738d2fc6757474bb99954993e7ae5d3', '9292264cc3aa410b96d14c7ea2b5f335', '커뮤니티게시글5', '커뮤니티게시글5의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('a212373e5c5d4655ac9bbd16abb89e7a', '9292264cc3aa410b96d14c7ea2b5f335', '커뮤니티게시글6', '커뮤니티게시글6의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);
insert into post(id, board_id, subject, content, writer, view_count, recommendation_count, creator, created_date_time)
values ('3e4f5a02d44c45bbb31c1b4f052dcb5e', '9292264cc3aa410b96d14c7ea2b5f335', '커뮤니티게시글7', '커뮤니티게시글7의 내용입니다.'
       , 'tester', 8, 3, 'tester', current_timestamp);



3e4f5a02d44c45bbb31c1b4f052dcb51
3e4f5a02d44c45bbb31c1b4f052dcb52
3e4f5a02d44c45bbb31c1b4f052dcb53
3e4f5a02d44c45bbb31c1b4f052dcb54
3e4f5a02d44c45bbb31c1b4f052dcb55


SELECT * FROM BOARD ;
SELECT * FROM POST ;
SELECT * FROM COMMENT ;
SELECT * FROM INFORMATION_SCHEMA.INDEXES WHERE TABLE_SCHEMA = 'PUBLIC';