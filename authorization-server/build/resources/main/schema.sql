drop  table if exists client;
create table client(
    client_id varchar(100),
    client_secret varchar(100),
    redirect_uri varchar(100),
    authorization_code varchar(100),
    access_token varchar(100),
    user varchar(100),
    scope varchar(100)
);

drop  table if exists member;
create table member(
    user_email varchar(100),
    user_name varchar(100),
    user_password varchar(100)
);

drop  table if exists board;
create table board(
    board_id varchar(100),
    board_title varchar(100),
    board_content varchar(100)
);

drop  table if exists comment;
create table comment(
    comment_id varchar(100),
    comment_content varchar(100)
)

