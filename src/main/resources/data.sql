INSERT INTO users (id, username, email, password, role) VALUES (1, 'admin', 'admin@example.com', 'password', 'admin');
INSERT INTO users (id, username, email, password, role) VALUES (2, 'user', 'user@example.com', 'password', 'user');

-- Insert new posts
INSERT INTO posts (id, author_id, text, createdAt) VALUES (3, 1, 'Second post by admin', NOW());
INSERT INTO posts (id, author_id, text, createdAt) VALUES (4, 2, 'Second post by user', NOW());
INSERT INTO posts (id, author_id, text, createdAt) VALUES (5, 1, 'Third post by admin', NOW());
INSERT INTO posts (id, author_id, text, createdAt) VALUES (6, 2, 'Third post by user', NOW());

-- Insert new comments
INSERT INTO comments (id, post_id, author_id, text, createdAt) VALUES (3, 3, 2, 'Second comment by user on admin post', NOW());
INSERT INTO comments (id, post_id, author_id, text, createdAt) VALUES (4, 4, 1, 'Second comment by admin on user post', NOW());
INSERT INTO comments (id, post_id, author_id, text, createdAt) VALUES (5, 5, 2, 'Third comment by user on admin post', NOW());
INSERT INTO comments (id, post_id, author_id, text, createdAt) VALUES (6, 6, 1, 'Third comment by admin on user post', NOW());
INSERT INTO comments (id, post_id, author_id, text, createdAt) VALUES (7, 3, 1, 'Fourth comment by admin on admin post', NOW());
INSERT INTO comments (id, post_id, author_id, text, createdAt) VALUES (8, 4, 2, 'Fourth comment by user on user post', NOW());

-- Insert new likes
INSERT INTO likes (id, liker_id, post_id) VALUES (3, 1, 3);
INSERT INTO likes (id, liker_id, post_id) VALUES (4, 2, 4);
INSERT INTO likes (id, liker_id, post_id) VALUES (5, 1, 5);
INSERT INTO likes (id, liker_id, post_id) VALUES (7, 1, 4);
INSERT INTO likes (id, liker_id, post_id) VALUES (6, 2, 6);
INSERT INTO likes (id, liker_id, post_id) VALUES (8, 2, 3);

