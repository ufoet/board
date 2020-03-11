create table board(
	bno number(8), -- 글번호
	name nvarchar2(10) not null, -- 작성자
	password varchar2(15) not null, -- 비밀번호
	title nvarchar2(50) not null, -- 글제목
	content nvarchar2(1000) not null, -- 내용
	attach nvarchar2(100), -- 파일첨부
	re_ref number(8) not null, -- 답변글 작성시 참조되는 글번호
	re_lev number(8) not null, -- 답변글의 레벨
	re_seq number(8) not null, -- 답변 글의 순서
	readcount number(8) default 0, -- 조회수
	regdate date default sysdate -- 작성날짜
);

drop table board;

alter table board add constraint pk_board primary key(bno);

create SEQUENCE board_seq;

drop SEQUENCE board_seq;

select * from board order by bno desc;

update BOARD set title = 'zxc' , content = 'ok' where bno = 2;

insert into BOARD(bno,name,password,title,content,re_ref,re_lev,re_seq) 
(select board_seq.nextVal,name,password,title,content,board_seq.currVal,re_lev,re_seq from board);

select * from BOARD where bno = (select max(bno) from BOARD);

insert into board(bno,name,password,title,content,
attach,re_ref,re_lev,re_seq) 
values(board_seq.nextVal,'hong','1234','댓글2','댓글2',null,1062,1,1);

select * from BOARD where re_ref=1062 order by re_seq;

update board set re_seq = re_seq+1 where re_ref = 1062 and re_seq > 0;

select * from BOARD order by re_ref;
commit

select * from BOARD order by bno desc;
