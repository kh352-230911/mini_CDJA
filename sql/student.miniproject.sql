--alter session set"_oracle_script"= true;
--
--create user mrmr
--identified by mrmr
--default tablespace users;
--
--grant connect, resource to mrmr;
--alter user mrmr quota unlimited on users;
--grant create session, create table to mrmr; -- 권한



--select * from member_mr where id = 'sinsa' and password = 'sinsa';
--insert into member_mr values('sinsa', 'sinsa', '신사임당', '임당당');
--select * from owner_mr;
--
--insert into owner_mr values('asd','asd','으스드', 1);
--insert into menu_mr (id_no, menu_name, price) values(1,'밥', 1000);

--일반 회원 테이블
create table member_mr(
    id varchar2(20) not null,
    password varchar2(20) not null,
    name varchar2(100) not null,
    nickname varchar2(20) not null,
    constraints pk_member_mr_id primary key(id),
    constraints uq_member_mr_nickname unique(nickname)
);


--사장 테이블
create table owner_mr(
    id varchar2(20) not null,
    password varchar2(20) not null,
    name varchar2(100) not null,
    id_no number not NULL,
    constraints pk_owner_mr_id primary key(id),
    constraints fk_owner_mr_id_no foreign key(id_no) references rest_list_mr(id_no)
);




--식당 리스트
create table rest_list_mr(
    id_no number not NULL,
    name varchar2(100) not null,
    local_code varchar2(20) not null,-- ##구
    addr varchar2(1000) not null,--도로명주소
    category_code varchar2(20) not null,
    kindshop char(1) default 'N',
    constraints pk_rest_list_mr_id_no primary key(id_no),
    constraints fk_rest_list_mr_category_code foreign key(category_code)references category_list_mr(category_code),
    constraints fk_rest_list_mr_local_code foreign key(local_code)references local_mr(local_code),
    constraints ck_rest_list_mr_kindshop check(kindshop in ('Y', 'N')) 
);
--카테고리 리스트
create table category_list_mr(
    category_code varchar2(20) not null,
    category varchar2(20) not null,
    constraints pk_category_code primary key(category_code)
);


--지역테이블
create table local_mr(
    local_code varchar2(20) not null,
    local_name varchar2(20),
    constraints pk_local_mr_local_code primary key(local_code)

);

--즐겨찾기 리스트
create table fav_shop_mr(
    fav_no number DEFAULT pk_fav_no.NEXTVAL ,
    fav_id varchar2(20) not null,
    fav_shop_no number not NULL,
    constraints pk_fav_shop_mr_fav_no primary key(fav_no),
    constraints fk_kind_shop_mr_id foreign key(fav_id) references member_mr(id),
    constraints fk_kind_shop_mr_id_no foreign key(fav_shop_no) references rest_list_mr(id_no)
);
select * from fav_shop_mr;
create sequence pk_fav_no
start with 1
increment by 1
nominvalue
nomaxvalue
nocycle
cache 20;

--메뉴 관련 sequence 

create sequence menu_no
start with 1
increment by 1
nominvalue
nomaxvalue
nocycle
cache 20;

--메뉴 리스트
create table menu_mr(
--    pn number DEFAULT pk_menu.NEXTVAL,
    id_no number,
    menu_no number DEFAULT menu_no.NEXTVAL,
    menu_name varchar2(100),
    price number,
    constraints pk_menu_mr_menu_no primary key (menu_no),
    constraints fk_menu_mr_id_no foreign key(id_no)references rest_list_mr(id_no)
);

commit;


--노원구 L5 
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (14251,'샘터분식','L5', '서울 노원구 공릉1.3동 567-6번지','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (14258,'주식회사아세','L5', '서울 노원구 공릉2동 441-93번지','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (14265,'파리바게뜨 육사생도회관점','L5', '서울 노원구 공릉2동 산230-30번지','C6');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (14274,'세븐돈까스','L5', '서울 노원구 공릉동 413-3 ','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (14287,'비에이치씨(BHC)태릉입구점','L5', '서울 노원구 공릉로 109 (공릉동)','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (14301,'인차이나','L5', '서울 노원구 공릉로 167  1층 (공릉동)','C2');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (14309,'오늘은강정','L5', '서울 노원구 공릉로 197  1층 좌측 (공릉동)','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (14336,'씨유서울과학기술대1관점','L5', '서울 노원구 공릉로 232  1층 (공릉동  서울과학기술대학교)','C7');

--강서구 L2
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (6251,'조그만식당','L2', '서울 강서구 가로공원로 173  1층 (화곡동)','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (6257,'일품사천불짬뽕','L2', '서울 강서구 가로공원로 193  1층 (화곡동  세진타워빌딩)','C2');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (6260,'BBQ치킨','L2', '서울 강서구 가로공원로76가길 12  1층 (화곡동)','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (6270,'59쌀피자','L2', '서울 강서구 가로공원로76길 53  1층 (화곡동)','C5');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (6275,'씨유 화곡양서점','L2', '서울 강서구 가로공원로84길 82  1층 (화곡동)','C7');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (6286,'화곡어시장','L2', '서울 강서구 강서로 158  2층 (화곡동)','C3');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (6313,'파리바게뜨','L2', '서울 강서구 강서로 247 (화곡동)','C6');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (6320,'미니스톱 화곡사거리점','L2', '화곡사거리점	서울 강서구 강서로 27','C7');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (6327,'대문안 감자탕','L2', '서울 강서구 강서로 303  1층 (내발산동)','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (6332,'홍콩반점0410','L2', '서울 강서구 강서로 33  2층 (화곡동)','C8');

--강동구 L4 
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (49696,'장어집','L4', '서울특별시 강동구 가래여울길 10 2층 201호(강일동)','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (49698,'이마트24 강동이씨플라자점','L4', '서울특별시 강동구 강동대로 143-40 1층','C7');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (49700,'윤담염소마을','L4', '서울특별시 강동구 강동대로 187 1층 101호','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (49704,'길위의파스타','L4', '서울특별시 강동구 강동대로51길 36 1층','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (49707,'나주관','L4', '서울특별시 강동구 강동대로53길 31 1층','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (49711,'지에스(GS)25 성내파크','L4', '서울특별시 강동구 강동대로53길 49 103  104호','C7');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (49712,'HILOO','L4', '서울특별시 강동구 강동대로53길 49 1층 101호','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (49717,'도쿄어시장','L4', '서울특별시 강동구 강동대로53길 87  1층 (성내동)','C3');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (49723,'파리바게뜨 암사양지점','L4', '서울특별시 강동구 고덕로 102 (암사동)','C6');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (49736,'피자알볼로 명일암사점','L4', '서울특별시 강동구 고덕로 162 1층 (명일동)','C5');

--강남구 L1
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (1 ,'씨유 양재점','L1', '서울 강남구 강남대로 238  102호 (도곡동  스카이쏠라빌딩)','C7');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (2,'(주)금강수림','L1', '서울 강남구 강남대로 240  지하 224호 2층','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (15,'라그릴리아 비스트로바 SPC스퀘어점','L1', '서울 강남구 강남대로 352  4층 (역삼동)','C4');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (47,'CJ푸드빌(주)뚜레쥬르 신사점','L1', '서울 강남구 강남대로 600  1 2층 (논현동  동산빌딩)','C6');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (55,'홍미닭발','L1', '서울 강남구 강남대로 628  지상 1층 (신사동  비율라빌딩)','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (62,'모모스테이크','L1', '서울 강남구 강남대로100길 10  지하1층 (역삼동)','C4');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (87,'봄날','L1', '\서울 강남구 강남대로114길 11  지하1층 (논현동)','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (104,'씨유논현혜성','L1', '서울 강남구 강남대로118길 8 (논현동)','C7');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (110,'육회먹은 연어','L1', '서울 강남구 강남대로122길 10  101 (논현동)','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (121,'피자스쿨 강남논현점','L1', '서울 강남구 강남대로122길 41  102호 (논현동)','C5');



--은평구 L3
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (93496,'배달키친','L3', '서울특별시 은평구 가좌로7나길 23 1층2호','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (93491,'참치홀릭','L3', '서울특별시 은평구 가좌로7나길','C3');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (93435,'직접 삶아내는감자탕','L3', '서울특별시 은평구 가좌로 169 1층','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (93439,'평창군 두부마을','L3', '서울특별시 은평구 가좌로 204 (응암동 동양빌딩)','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (93443,'옛날통닭','L3', '서울특별시 은평구 가좌로 222 우측호(응암동)','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (93450,'오거리 손칼국수 냉면','L3', '서울특별시 은평구 가좌로 244 1층','C8');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (93462,'숯불닭갈비뽕닭','L3', '서울특별시 은평구 가좌로 313-1  1층(신사동)','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (93466,'유가네한우곰탕','L3', '서울특별시 은평구 가좌로 321 101호','C1');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (93472,'수고한 나에게','L3', '서울특별시 은평구 가좌로 344 상가동 2층 6호','C6');
insert into rest_list_mr (id_no, name, local_code,addr, category_code)
values (93477,'이마트24 응암초교점','L3', '서울특별시 은평구 가좌로6길 12 1층','C7');




select * from local_mr;
insert into local_mr values('L1','강남구');
insert into local_mr values('L2','강서구');
insert into local_mr values('L3','은평구');
insert into local_mr values('L4','강동구');
insert into local_mr values('L5','노원구');

--drop table rest_list_row_mr;
select distinct category from rest_list_row_mr;
insert into category_list_mr values ('C1', '한식');
insert into category_list_mr values ('C2', '중식');
insert into category_list_mr values ('C3', '일식');
insert into category_list_mr values ('C4', '양식');
insert into category_list_mr values ('C5', '패스트푸드');
insert into category_list_mr values ('C6', '제과점');
insert into category_list_mr values ('C7', '편의점');
insert into category_list_mr values ('C8', '일반대중음식');


insert into menu_mr (id_no, menu_name, price) values (14251, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (14251, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (14251, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (14251, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (14251, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (14251, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (14251, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (14251, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (14251, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (14251, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (14274, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (14274, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (14274, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (14274, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (14274, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (14274, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (14274, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (14274, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (14274, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (14274, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (6251, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (6251, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (6251, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (6251, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (6251, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (6251, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (6251, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (6251, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (6251, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (6251, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (6327, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (6327, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (6327, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (6327, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (6327, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (6327, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (6327, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (6327, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (6327, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (6327, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (49696, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (49696, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (49696, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (49696, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (49696, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (49696, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (49696, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (49696, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (49696, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (49696, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (49700, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (49700, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (49700, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (49700, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (49700, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (49700, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (49700, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (49700, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (49700, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (49700, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (49707, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (49707, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (49707, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (49707, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (49707, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (49707, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (49707, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (49707, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (49707, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (49707, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (2, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (2, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (2, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (2, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (2, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (2, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (2, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (2, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (2, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (2, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (55, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (55, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (55, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (55, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (55, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (55, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (55, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (55, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (55, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (55, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (93435, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (93435, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (93435, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (93435, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (93435, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (93435, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (93435, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (93435, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (93435, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (93435, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (93439, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (93439, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (93439, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (93439, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (93439, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (93439, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (93439, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (93439, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (93439, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (93439, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (93462, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (93462, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (93462, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (93462, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (93462, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (93462, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (93462, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (93462, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (93462, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (93462, '순두부찌개', 6000);

insert into menu_mr (id_no, menu_name, price) values (93466, '라면', 6000);
insert into menu_mr (id_no, menu_name, price) values (93466, '김치찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (93466, '모듬튀김', 6000);
insert into menu_mr (id_no, menu_name, price) values (93466, '비빔밥', 7000);
insert into menu_mr (id_no, menu_name, price) values (93466, '불고기', 8000);
insert into menu_mr (id_no, menu_name, price) values (93466, '된장찌개', 6000);
insert into menu_mr (id_no, menu_name, price) values (93466, '냉면', 7000);
insert into menu_mr (id_no, menu_name, price) values (93466, '갈비탕', 9000);
insert into menu_mr (id_no, menu_name, price) values (93466, '떡볶이', 5000);
insert into menu_mr (id_no, menu_name, price) values (93466, '순두부찌개', 6000);

--C2 중식
insert into menu_mr (id_no, menu_name, price) values (14301, '짜장면', 7000);
insert into menu_mr (id_no, menu_name, price) values (14301, '탕수육', 9000);
insert into menu_mr (id_no, menu_name, price) values (14301, '볶음밥', 7500);
insert into menu_mr (id_no, menu_name, price) values (14301, '만두국', 7000);
insert into menu_mr (id_no, menu_name, price) values (14301, '양장피', 8000);
insert into menu_mr (id_no, menu_name, price) values (14301, '꿔바로우', 8500);
insert into menu_mr (id_no, menu_name, price) values (14301, '팔보채', 8500);
insert into menu_mr (id_no, menu_name, price) values (14301, '새우튀김', 7500);
insert into menu_mr (id_no, menu_name, price) values (14301, '해물찹쌀죽', 9000);
insert into menu_mr (id_no, menu_name, price) values (14301, '단무지', 3000);

insert into menu_mr (id_no, menu_name, price) values (6257, '짜장면', 7000);
insert into menu_mr (id_no, menu_name, price) values (6257, '탕수육', 9000);
insert into menu_mr (id_no, menu_name, price) values (6257, '볶음밥', 7500);
insert into menu_mr (id_no, menu_name, price) values (6257, '만두국', 7000);
insert into menu_mr (id_no, menu_name, price) values (6257, '양장피', 8000);
insert into menu_mr (id_no, menu_name, price) values (6257, '꿔바로우', 8500);
insert into menu_mr (id_no, menu_name, price) values (6257, '팔보채', 8500);
insert into menu_mr (id_no, menu_name, price) values (6257, '새우튀김', 7500);
insert into menu_mr (id_no, menu_name, price) values (6257, '해물찹쌀죽', 9000);
insert into menu_mr (id_no, menu_name, price) values (6257, '단무지', 3000);

-- C3 일식
insert into menu_mr (id_no, menu_name, price) values (6286, '스시', 12000);
insert into menu_mr (id_no, menu_name, price) values (6286, '사케동', 10000);
insert into menu_mr (id_no, menu_name, price) values (6286, '라멘', 9000);
insert into menu_mr (id_no, menu_name, price) values (6286, '우동', 8000);
insert into menu_mr (id_no, menu_name, price) values (6286, '텐동', 9500);
insert into menu_mr (id_no, menu_name, price) values (6286, '규카츠', 11000);
insert into menu_mr (id_no, menu_name, price) values (6286, '산낙지', 13000);
insert into menu_mr (id_no, menu_name, price) values (6286, '미소라멘', 9500);
insert into menu_mr (id_no, menu_name, price) values (6286, '연어롤', 12500);
insert into menu_mr (id_no, menu_name, price) values (6286, '오니기리', 8500);

insert into menu_mr (id_no, menu_name, price) values (49717, '스시', 12000);
insert into menu_mr (id_no, menu_name, price) values (49717, '사케동', 10000);
insert into menu_mr (id_no, menu_name, price) values (49717, '라멘', 9000);
insert into menu_mr (id_no, menu_name, price) values (49717, '우동', 8000);
insert into menu_mr (id_no, menu_name, price) values (49717, '텐동', 9500);
insert into menu_mr (id_no, menu_name, price) values (49717, '규카츠', 11000);
insert into menu_mr (id_no, menu_name, price) values (49717, '산낙지', 13000);
insert into menu_mr (id_no, menu_name, price) values (49717, '미소라멘', 9500);
insert into menu_mr (id_no, menu_name, price) values (49717, '연어롤', 12500);
insert into menu_mr (id_no, menu_name, price) values (49717, '오니기리', 8500);

insert into menu_mr (id_no, menu_name, price) values (93491, '스시', 12000);
insert into menu_mr (id_no, menu_name, price) values (93491, '사케동', 10000);
insert into menu_mr (id_no, menu_name, price) values (93491, '라멘', 9000);
insert into menu_mr (id_no, menu_name, price) values (93491, '우동', 8000);
insert into menu_mr (id_no, menu_name, price) values (93491, '텐동', 9500);
insert into menu_mr (id_no, menu_name, price) values (93491, '규카츠', 11000);
insert into menu_mr (id_no, menu_name, price) values (93491, '산낙지', 13000);
insert into menu_mr (id_no, menu_name, price) values (93491, '미소라멘', 9500);
insert into menu_mr (id_no, menu_name, price) values (93491, '연어롤', 12500);
insert into menu_mr (id_no, menu_name, price) values (93491, '오니기리', 8500);

--C4 양식
insert into menu_mr (id_no, menu_name, price) values (15, '스테이크', 15000);
insert into menu_mr (id_no, menu_name, price) values (15, '스파게티', 12000);
insert into menu_mr (id_no, menu_name, price) values (15, '샐러드', 8000);
insert into menu_mr (id_no, menu_name, price) values (15, '피자', 13000);
insert into menu_mr (id_no, menu_name, price) values (15, '랍스터 비스크', 18000);
insert into menu_mr (id_no, menu_name, price) values (15, '크림 스프', 8500);
insert into menu_mr (id_no, menu_name, price) values (15, '칠리 콘 카르네', 14000);
insert into menu_mr (id_no, menu_name, price) values (15, '카프레제 샐러드', 9000);
insert into menu_mr (id_no, menu_name, price) values (15, '립 아이 스테이크', 17000);
insert into menu_mr (id_no, menu_name, price) values (15, '포테이토 그라탕', 9500);

insert into menu_mr (id_no, menu_name, price) values (62, '스테이크', 15000);
insert into menu_mr (id_no, menu_name, price) values (62, '스파게티', 12000);
insert into menu_mr (id_no, menu_name, price) values (62, '샐러드', 8000);
insert into menu_mr (id_no, menu_name, price) values (62, '피자', 13000);
insert into menu_mr (id_no, menu_name, price) values (62, '랍스터 비스크', 18000);
insert into menu_mr (id_no, menu_name, price) values (62, '크림 스프', 8500);
insert into menu_mr (id_no, menu_name, price) values (62, '칠리 콘 카르네', 14000);
insert into menu_mr (id_no, menu_name, price) values (62, '카프레제 샐러드', 9000);
insert into menu_mr (id_no, menu_name, price) values (62, '립 아이 스테이크', 17000);
insert into menu_mr (id_no, menu_name, price) values (62, '포테이토 그라탕', 9500);

-- C5 패스트푸드
insert into menu_mr (id_no, menu_name, price) values (6270, '햄버거', 5000);
insert into menu_mr (id_no, menu_name, price) values (6270, '프렌치 후라이', 3000);
insert into menu_mr (id_no, menu_name, price) values (6270, '핫도그', 4000);
insert into menu_mr (id_no, menu_name, price) values (6270, '치킨 너겟', 4500);
insert into menu_mr (id_no, menu_name, price) values (6270, '피자 슬라이스', 3500);
insert into menu_mr (id_no, menu_name, price) values (6270, '소프트 아이스크림', 2500);
insert into menu_mr (id_no, menu_name, price) values (6270, '치킨 버거', 5500);
insert into menu_mr (id_no, menu_name, price) values (6270, '치즈스틱', 4000);
insert into menu_mr (id_no, menu_name, price) values (6270, '타코', 4500);
insert into menu_mr (id_no, menu_name, price) values (6270, '햇반버거', 6000);

insert into menu_mr (id_no, menu_name, price) values (49736, '햄버거', 5000);
insert into menu_mr (id_no, menu_name, price) values (49736, '프렌치 후라이', 3000);
insert into menu_mr (id_no, menu_name, price) values (49736, '핫도그', 4000);
insert into menu_mr (id_no, menu_name, price) values (49736, '치킨 너겟', 4500);
insert into menu_mr (id_no, menu_name, price) values (49736, '피자 슬라이스', 3500);
insert into menu_mr (id_no, menu_name, price) values (49736, '소프트 아이스크림', 2500);
insert into menu_mr (id_no, menu_name, price) values (49736, '치킨 버거', 5500);
insert into menu_mr (id_no, menu_name, price) values (49736, '치즈스틱', 4000);
insert into menu_mr (id_no, menu_name, price) values (49736, '타코', 4500);
insert into menu_mr (id_no, menu_name, price) values (49736, '햇반버거', 6000);

insert into menu_mr (id_no, menu_name, price) values (121, '햄버거', 5000);
insert into menu_mr (id_no, menu_name, price) values (121, '프렌치 후라이', 3000);
insert into menu_mr (id_no, menu_name, price) values (121, '핫도그', 4000);
insert into menu_mr (id_no, menu_name, price) values (121, '치킨 너겟', 4500);
insert into menu_mr (id_no, menu_name, price) values (121, '피자 슬라이스', 3500);
insert into menu_mr (id_no, menu_name, price) values (121, '소프트 아이스크림', 2500);
insert into menu_mr (id_no, menu_name, price) values (121, '치킨 버거', 5500);
insert into menu_mr (id_no, menu_name, price) values (121, '치즈스틱', 4000);
insert into menu_mr (id_no, menu_name, price) values (121, '타코', 4500);
insert into menu_mr (id_no, menu_name, price) values (121, '햇반버거', 6000);

--C6 제과점
insert into menu_mr (id_no, menu_name, price) values (14265, '초콜릿 케이크', 5500);
insert into menu_mr (id_no, menu_name, price) values (14265, '딸기 타르트', 6000);
insert into menu_mr (id_no, menu_name, price) values (14265, '치즈케이크', 5000);
insert into menu_mr (id_no, menu_name, price) values (14265, '아몬드 크루아상', 4000);
insert into menu_mr (id_no, menu_name, price) values (14265, '카페 라떼', 3500);
insert into menu_mr (id_no, menu_name, price) values (14265, '카푸치노', 4000);
insert into menu_mr (id_no, menu_name, price) values (14265, '피스타치오 마카롱', 4500);
insert into menu_mr (id_no, menu_name, price) values (14265, '아이스 크림 콘', 3000);
insert into menu_mr (id_no, menu_name, price) values (14265, '브라우니', 4500);
insert into menu_mr (id_no, menu_name, price) values (14265, '베이글', 3500);

insert into menu_mr (id_no, menu_name, price) values (6313, '초콜릿 케이크', 5500);
insert into menu_mr (id_no, menu_name, price) values (6313, '딸기 타르트', 6000);
insert into menu_mr (id_no, menu_name, price) values (6313, '치즈케이크', 5000);
insert into menu_mr (id_no, menu_name, price) values (6313, '아몬드 크루아상', 4000);
insert into menu_mr (id_no, menu_name, price) values (6313, '카페 라떼', 3500);
insert into menu_mr (id_no, menu_name, price) values (6313, '카푸치노', 4000);
insert into menu_mr (id_no, menu_name, price) values (6313, '피스타치오 마카롱', 4500);
insert into menu_mr (id_no, menu_name, price) values (6313, '아이스 크림 콘', 3000);
insert into menu_mr (id_no, menu_name, price) values (6313, '브라우니', 4500);
insert into menu_mr (id_no, menu_name, price) values (6313, '베이글', 3500);

insert into menu_mr (id_no, menu_name, price) values (49723, '초콜릿 케이크', 5500);
insert into menu_mr (id_no, menu_name, price) values (49723, '딸기 타르트', 6000);
insert into menu_mr (id_no, menu_name, price) values (49723, '치즈케이크', 5000);
insert into menu_mr (id_no, menu_name, price) values (49723, '아몬드 크루아상', 4000);
insert into menu_mr (id_no, menu_name, price) values (49723, '카페 라떼', 3500);
insert into menu_mr (id_no, menu_name, price) values (49723, '카푸치노', 4000);
insert into menu_mr (id_no, menu_name, price) values (49723, '피스타치오 마카롱', 4500);
insert into menu_mr (id_no, menu_name, price) values (49723, '아이스 크림 콘', 3000);
insert into menu_mr (id_no, menu_name, price) values (49723, '브라우니', 4500);
insert into menu_mr (id_no, menu_name, price) values (49723, '베이글', 3500);

insert into menu_mr (id_no, menu_name, price) values (47, '초콜릿 케이크', 5500);
insert into menu_mr (id_no, menu_name, price) values (47, '딸기 타르트', 6000);
insert into menu_mr (id_no, menu_name, price) values (47, '치즈케이크', 5000);
insert into menu_mr (id_no, menu_name, price) values (47, '아몬드 크루아상', 4000);
insert into menu_mr (id_no, menu_name, price) values (47, '카페 라떼', 3500);
insert into menu_mr (id_no, menu_name, price) values (47, '카푸치노', 4000);
insert into menu_mr (id_no, menu_name, price) values (47, '피스타치오 마카롱', 4500);
insert into menu_mr (id_no, menu_name, price) values (47, '아이스 크림 콘', 3000);
insert into menu_mr (id_no, menu_name, price) values (47, '브라우니', 4500);
insert into menu_mr (id_no, menu_name, price) values (47, '베이글', 3500);

insert into menu_mr (id_no, menu_name, price) values (93472, '초콜릿 케이크', 5500);
insert into menu_mr (id_no, menu_name, price) values (93472, '딸기 타르트', 6000);
insert into menu_mr (id_no, menu_name, price) values (93472, '치즈케이크', 5000);
insert into menu_mr (id_no, menu_name, price) values (93472, '아몬드 크루아상', 4000);
insert into menu_mr (id_no, menu_name, price) values (93472, '카페 라떼', 3500);
insert into menu_mr (id_no, menu_name, price) values (93472, '카푸치노', 4000);
insert into menu_mr (id_no, menu_name, price) values (93472, '피스타치오 마카롱', 4500);
insert into menu_mr (id_no, menu_name, price) values (93472, '아이스 크림 콘', 3000);
insert into menu_mr (id_no, menu_name, price) values (93472, '브라우니', 4500);
insert into menu_mr (id_no, menu_name, price) values (93472, '베이글', 3500);

--C7 편의점
insert into menu_mr (id_no, menu_name, price) values (14336, '샌드위치', 3500);
insert into menu_mr (id_no, menu_name, price) values (14336, '도시락', 4500);
insert into menu_mr (id_no, menu_name, price) values (14336, '라면 컵라면', 2500);
insert into menu_mr (id_no, menu_name, price) values (14336, '햄버거', 3000);
insert into menu_mr (id_no, menu_name, price) values (14336, '와플', 2000);
insert into menu_mr (id_no, menu_name, price) values (14336, '핫도그', 2500);
insert into menu_mr (id_no, menu_name, price) values (14336, '피자 피케이', 3500);
insert into menu_mr (id_no, menu_name, price) values (14336, '아이스크림', 2000);
insert into menu_mr (id_no, menu_name, price) values (14336, '과일 컵', 3000);
insert into menu_mr (id_no, menu_name, price) values (14336, '커피 음료', 2000);

insert into menu_mr (id_no, menu_name, price) values (6275, '샌드위치', 3500);
insert into menu_mr (id_no, menu_name, price) values (6275, '도시락', 4500);
insert into menu_mr (id_no, menu_name, price) values (6275, '라면 컵라면', 2500);
insert into menu_mr (id_no, menu_name, price) values (6275, '햄버거', 3000);
insert into menu_mr (id_no, menu_name, price) values (6275, '와플', 2000);
insert into menu_mr (id_no, menu_name, price) values (6275, '핫도그', 2500);
insert into menu_mr (id_no, menu_name, price) values (6275, '피자 피케이', 3500);
insert into menu_mr (id_no, menu_name, price) values (6275, '아이스크림', 2000);
insert into menu_mr (id_no, menu_name, price) values (6275, '과일 컵', 3000);
insert into menu_mr (id_no, menu_name, price) values (6275, '커피 음료', 2000);

insert into menu_mr (id_no, menu_name, price) values (6320, '샌드위치', 3500);
insert into menu_mr (id_no, menu_name, price) values (6320, '도시락', 4500);
insert into menu_mr (id_no, menu_name, price) values (6320, '라면 컵라면', 2500);
insert into menu_mr (id_no, menu_name, price) values (6320, '햄버거', 3000);
insert into menu_mr (id_no, menu_name, price) values (6320, '와플', 2000);
insert into menu_mr (id_no, menu_name, price) values (6320, '핫도그', 2500);
insert into menu_mr (id_no, menu_name, price) values (6320, '피자 피케이', 3500);
insert into menu_mr (id_no, menu_name, price) values (6320, '아이스크림', 2000);
insert into menu_mr (id_no, menu_name, price) values (6320, '과일 컵', 3000);
insert into menu_mr (id_no, menu_name, price) values (6320, '커피 음료', 2000);

insert into menu_mr (id_no, menu_name, price) values (49698, '샌드위치', 3500);
insert into menu_mr (id_no, menu_name, price) values (49698, '도시락', 4500);
insert into menu_mr (id_no, menu_name, price) values (49698, '라면 컵라면', 2500);
insert into menu_mr (id_no, menu_name, price) values (49698, '햄버거', 3000);
insert into menu_mr (id_no, menu_name, price) values (49698, '와플', 2000);
insert into menu_mr (id_no, menu_name, price) values (49698, '핫도그', 2500);
insert into menu_mr (id_no, menu_name, price) values (49698, '피자 피케이', 3500);
insert into menu_mr (id_no, menu_name, price) values (49698, '아이스크림', 2000);
insert into menu_mr (id_no, menu_name, price) values (49698, '과일 컵', 3000);
insert into menu_mr (id_no, menu_name, price) values (49698, '커피 음료', 2000);

insert into menu_mr (id_no, menu_name, price) values (49711, '샌드위치', 3500);
insert into menu_mr (id_no, menu_name, price) values (49711, '도시락', 4500);
insert into menu_mr (id_no, menu_name, price) values (49711, '라면 컵라면', 2500);
insert into menu_mr (id_no, menu_name, price) values (49711, '햄버거', 3000);
insert into menu_mr (id_no, menu_name, price) values (49711, '와플', 2000);
insert into menu_mr (id_no, menu_name, price) values (49711, '핫도그', 2500);
insert into menu_mr (id_no, menu_name, price) values (49711, '피자 피케이', 3500);
insert into menu_mr (id_no, menu_name, price) values (49711, '아이스크림', 2000);
insert into menu_mr (id_no, menu_name, price) values (49711, '과일 컵', 3000);
insert into menu_mr (id_no, menu_name, price) values (49711, '커피 음료', 2000);

insert into menu_mr (id_no, menu_name, price) values (1, '샌드위치', 3500);
insert into menu_mr (id_no, menu_name, price) values (1, '도시락', 4500);
insert into menu_mr (id_no, menu_name, price) values (1, '라면 컵라면', 2500);
insert into menu_mr (id_no, menu_name, price) values (1, '햄버거', 3000);
insert into menu_mr (id_no, menu_name, price) values (1, '와플', 2000);
insert into menu_mr (id_no, menu_name, price) values (1, '핫도그', 2500);
insert into menu_mr (id_no, menu_name, price) values (1, '피자 피케이', 3500);
insert into menu_mr (id_no, menu_name, price) values (1, '아이스크림', 2000);
insert into menu_mr (id_no, menu_name, price) values (1, '과일 컵', 3000);
insert into menu_mr (id_no, menu_name, price) values (1, '커피 음료', 2000);

insert into menu_mr (id_no, menu_name, price) values (104, '샌드위치', 3500);
insert into menu_mr (id_no, menu_name, price) values (104, '도시락', 4500);
insert into menu_mr (id_no, menu_name, price) values (104, '라면 컵라면', 2500);
insert into menu_mr (id_no, menu_name, price) values (104, '햄버거', 3000);
insert into menu_mr (id_no, menu_name, price) values (104, '와플', 2000);
insert into menu_mr (id_no, menu_name, price) values (104, '핫도그', 2500);
insert into menu_mr (id_no, menu_name, price) values (104, '피자 피케이', 3500);
insert into menu_mr (id_no, menu_name, price) values (104, '아이스크림', 2000);
insert into menu_mr (id_no, menu_name, price) values (104, '과일 컵', 3000);
insert into menu_mr (id_no, menu_name, price) values (104, '커피 음료', 2000);

insert into menu_mr (id_no, menu_name, price) values (93477, '샌드위치', 3500);
insert into menu_mr (id_no, menu_name, price) values (93477, '도시락', 4500);
insert into menu_mr (id_no, menu_name, price) values (93477, '라면 컵라면', 2500);
insert into menu_mr (id_no, menu_name, price) values (93477, '햄버거', 3000);
insert into menu_mr (id_no, menu_name, price) values (93477, '와플', 2000);
insert into menu_mr (id_no, menu_name, price) values (93477, '핫도그', 2500);
insert into menu_mr (id_no, menu_name, price) values (93477, '피자 피케이', 3500);
insert into menu_mr (id_no, menu_name, price) values (93477, '아이스크림', 2000);
insert into menu_mr (id_no, menu_name, price) values (93477, '과일 컵', 3000);
insert into menu_mr (id_no, menu_name, price) values (93477, '커피 음료', 2000);

--C8 일반대중음식
insert into menu_mr (id_no, menu_name, price) values (14258, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (14258, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (14258, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (14258, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (14258, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (14258, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (14258, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (14258, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (14258, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (14258, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (14287, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (14287, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (14287, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (14287, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (14287, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (14287, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (14287, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (14287, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (14287, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (14287, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (14309, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (14309, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (14309, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (14309, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (14309, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (14309, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (14309, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (14309, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (14309, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (14309, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (6260, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (6260, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (6260, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (6260, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (6260, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (6260, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (6260, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (6260, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (6260, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (6260, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (6332, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (6332, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (6332, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (6332, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (6332, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (6332, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (6332, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (6332, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (6332, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (6332, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (49704, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (49704, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (49704, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (49704, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (49704, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (49704, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (49704, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (49704, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (49704, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (49704, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (49712, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (49712, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (49712, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (49712, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (49712, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (49712, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (49712, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (49712, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (49712, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (49712, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (87, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (87, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (87, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (87, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (87, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (87, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (87, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (87, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (87, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (87, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (110, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (110, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (110, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (110, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (110, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (110, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (110, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (110, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (110, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (110, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (93496, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (93496, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (93496, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (93496, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (93496, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (93496, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (93496, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (93496, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (93496, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (93496, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (93443, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (93443, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (93443, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (93443, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (93443, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (93443, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (93443, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (93443, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (93443, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (93443, '우동', 7500);

insert into menu_mr (id_no, menu_name, price) values (93450, '비빔밥', 8000);
insert into menu_mr (id_no, menu_name, price) values (93450, '돼지고기 김치볶음밥', 8500);
insert into menu_mr (id_no, menu_name, price) values (93450, '짜장면', 7500);
insert into menu_mr (id_no, menu_name, price) values (93450, '탕수육', 9500);
insert into menu_mr (id_no, menu_name, price) values (93450, '된장찌개', 8000);
insert into menu_mr (id_no, menu_name, price) values (93450, '불고기', 9000);
insert into menu_mr (id_no, menu_name, price) values (93450, '떡볶이', 7000);
insert into menu_mr (id_no, menu_name, price) values (93450, '김밥', 5500);
insert into menu_mr (id_no, menu_name, price) values (93450, '치킨 까스', 9000);
insert into menu_mr (id_no, menu_name, price) values (93450, '우동', 7500);