CREATE TABLE IF NOT EXISTS client(
    client_id varchar(20),
    client_secret varchar(100),
    redirect_uri varchar(100),
    user varchar(100),
    scope varchar(100),
    authorization_code varchar(100)
);
