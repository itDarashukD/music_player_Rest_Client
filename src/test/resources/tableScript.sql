CREATE TABLE album
(
    id    INT          NOT NULL IDENTITY ,
    name  VARCHAR(250) NOT NULL,
    year  BIGINT       NOT NULL,
    notes VARCHAR(250) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE song
(
    id       INT          NOT NULL IDENTITY ,
    album_id INT          NOT NULL,
    name     VARCHAR(250) NOT NULL,
    notes    VARCHAR(250) NOT NULL,
    year     INT          NOT NULL,
    PRIMARY KEY (id)
);
