CREATE TABLE Users (
                       id BIGINT PRIMARY KEY,
                       username VARCHAR(35) NOT NULL,
                       email VARCHAR(35) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(25) NOT NULL -- Example roles: 'user', 'admin'
);

CREATE TABLE Posts (
                       id BIGINT PRIMARY KEY,
                       author_id BIGINT,
                       text VARCHAR(300),
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Comments (
                          id BIGINT PRIMARY KEY,
                          post_id BIGINT,
                          author_id BIGINT,
                          text VARCHAR(300),
                          createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Likes (
                       id BIGINT PRIMARY KEY,
                       liker_id BIGINT,
                       post_id BIGINT
);

ALTER TABLE Posts ADD FOREIGN KEY (author_id) REFERENCES Users (id);
ALTER TABLE Comments ADD FOREIGN KEY (post_id) REFERENCES Posts(id);
ALTER TABLE Comments ADD FOREIGN KEY (author_id) REFERENCES Users (id);
ALTER TABLE Likes ADD FOREIGN KEY (liker_id) REFERENCES Users(id);
ALTER TABLE Likes ADD FOREIGN KEY (post_id) REFERENCES Posts(id);
