drop  table if exists client;
create table client(
    client_id varchar(20) PRIMARY KEY,
    client_secret varchar(100),
    redirect_uri varchar(100),
    authorization_code varchar(100),
    access_token varchar(100)
);

CREATE TABLE IF NOT EXISTS board (
    board_id INT AUTO_INCREMENT PRIMARY KEY,
    board_title VARCHAR(100),
    board_content VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS comment (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    comment_content VARCHAR(100),
    board_id INT,
    FOREIGN KEY (board_id) REFERENCES board(board_id)
);




