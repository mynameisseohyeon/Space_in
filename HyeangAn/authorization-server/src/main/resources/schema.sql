mysql> CREATE TABLE IF NOT EXISTS `client_details`
(
    `client_id`               VARCHAR(256)  NOT NULL,
    `resource_ids`            VARCHAR(256)  NULL,
    `client_secret`           VARCHAR(256)  NULL,
    `scope`                   VARCHAR(256)  NULL,
    `authorized_grant_types`  VARCHAR(256)  NULL,
    `redirect_uri`             VARCHAR(256)  NULL,
    `access_token_validity`   INT           NULL,
    `refresh_token_validity`  INT           NULL,
    PRIMARY KEY (`client_id`)
);
mysql> INSERT INTO client_details (client_id, client_secret, redirect_uri)
VALUES ("spacein", "password", "http://localhost:8080");
