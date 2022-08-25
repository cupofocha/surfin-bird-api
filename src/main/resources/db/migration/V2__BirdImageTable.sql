CREATE TABLE IF NOT EXISTS bird_image (
    id INT NOT NULL,
    bird VARCHAR(100) NOT NULL,
    path VARCHAR(100) NOT NULL,
    approvement BOOLEAN,
    uploader_id UUID NOT NULL
);