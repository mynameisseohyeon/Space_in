drop  table if exists client;
create table client(
    client_id varchar(20),
    client_secret varchar(100),
    redirect_uri varchar(100),
    authorization_code varchar(100),
    access_token varchar(100)
);
