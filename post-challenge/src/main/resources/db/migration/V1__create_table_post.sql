CREATE TABLE tb_post (
    id VARCHAR(255) PRIMARY KEY NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    post_description VARCHAR(255),
    body TEXT NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE
);