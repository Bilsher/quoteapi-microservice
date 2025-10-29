CREATE TABLE IF NOT EXISTS user_votes (
    vote_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    quote_id INT NOT NULL,
    vote_type VARCHAR(10) NOT NULL,
    UNIQUE KEY unique_user_quote (user_id, quote_id)
);