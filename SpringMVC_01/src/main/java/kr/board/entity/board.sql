drop table board;


CREATE TABLE board
(
    num 		int 			not null auto_increment,
    username	varchar(255)	not null,
    title		varchar(255)	not null,
    content		varchar(2000)	not null,
    writer		varchar(255)	not null,
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



CREATE TABLE member
(
    id 			int 			auto_increment primary key,
    username	varchar(255)	not null unique,
    password	varchar(255)	not null,
    name		varchar(255)	not null,
    email		varchar(255)	not null unique
);

commit;

insert into member(username, password, name, email)
values ('smhrd01', '12345', '관리자', 'smhrd01@smhrd.co.kr');

insert into member(username, password, name, email)
values ('smhrd02', '12345', '문경수', 'smhrd02@smhrd.co.kr');

insert into member(username, password, name, email)
values ('smhrd03', '12345', '빅데이터03', 'smhrd03@smhrd.co.kr');

commit;

select * from member;