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




--- 답변형 게시판 
drop table reply;


CREATE TABLE reply
(
    num 		int 			not null auto_increment,
    username	varchar(255)	not null,
    title		varchar(255)	not null,
    content		varchar(2000)	not null,
    writer		varchar(255)	not null,
    indate		datetime		default now(),
    count		int				default	0,
    bgroup		int, -- 원글(부모글)과 답글(자식글)을 하나로 묶는 필드
    bseq		int, 
    	-- 답글의 순서를 결정.(
    	--	bseq = 0 : 원글, 
    	--		- bseq = 1 : 원글의 첫번째 답글, 
    	--		- bseq = 2 : 원글의 2번째 답글)
    blevel		int,  
    	-- 답글의 들여쓰기를 결정.(
    	--	blevel = 0 : 원글, 
    	--		- blevel = 1 : 원글에서 한번 들여쓰기, 
    	--			- blevel = 2 : 원글의 두번 들여쓰기)
    bdelete		int,
    	-- 삭제여부를 판단하는 필드
    	-- 	bdelete = 0 : 정상글
    	-- 	bdelete = 1 : 삭제글
    
    primary key (num)
);

commit;

select * from reply;

select max(bgroup) from reply;  -- 2+1 = 3

select IFNULL(max(bgroup)+1, 0) as bgroup 
from reply;


		select * 
			from reply 
			order by bgroup desc, bseq asc
			limit 1, 5;

				select count(*) from reply;

