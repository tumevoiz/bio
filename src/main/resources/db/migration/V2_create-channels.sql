CREATE TABLE channels (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(50) NOT NULL UNIQUE,

    PRIMARY KEY (id)
);