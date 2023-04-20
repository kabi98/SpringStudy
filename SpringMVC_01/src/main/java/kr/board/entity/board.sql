drop table board;


CREATE TABLE board
(
    num 		int 			not null auto_increment,
    title		varchar(100)	not null,
    content		varchar(2000)	not null,
    writer		varchar(100)	not null,
    indate		datetime		default now(),
    count		int				default	0,
    primary key (num)
);


insert into board(title, content, writer)
values ('게시판 연습', '게시판 연습', '관리자');


insert into board(title, content, writer)
values ('게시판 연습2', '게시판 연습2', '문경수');


insert into board(title, content, writer)
values ('게시판 연습3', '게시판 연습3', '빅데이터');

select * from board;

commit;
