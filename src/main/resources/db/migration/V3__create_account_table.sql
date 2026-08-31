CREATE TABLE account(
    account_id UUID DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT now(),

    CONSTRAINT ACCOUNT_PK PRIMARY KEY (account_id),
    CONSTRAINT ACCOUNT_FK FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE CASCADE,
    CONSTRAINT uq_account_user_name UNIQUE (user_id, name)
);