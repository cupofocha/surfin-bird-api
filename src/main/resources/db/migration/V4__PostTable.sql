CREATE TABLE IF NOT EXISTS image_post (
    id INT PRIMARY KEY NOT NULL,
    poster_id UUID NOT NULL,
    text VARCHAR(100) NOT NULL,
    bird_image_id INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS bird_post (
    id INT PRIMARY KEY  NOT NULL,
    poster_id UUID NOT NULL,
    bird VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS comment (
    id INT PRIMARY KEY  NOT NULL,
    commenter_id UUID NOT NULL,
    text VARCHAR(100) NOT NULL,
    post_type VARCHAR(100) NOT NULL,
    post_id INT NOT NULL
    );