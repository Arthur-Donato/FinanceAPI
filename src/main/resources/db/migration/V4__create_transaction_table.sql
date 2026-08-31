CREATE TABLE transaction(
    transaction_id UUID DEFAULT gen_random_uuid(),
    type VARCHAR(10) NOT NULL,
    description VARCHAR(255),
    date DATE NOT NULL,
    value NUMERIC(19,2) NOT NULL,
    account_id UUID NOT NULL,
    category_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT now(),

    CONSTRAINT TRANSACTION_PK PRIMARY KEY (transaction_id),
    CONSTRAINT ACCOUNT_FK FOREIGN KEY (account_id) REFERENCES account(account_id) ON DELETE CASCADE,
    CONSTRAINT CATEGORY_FK FOREIGN KEY(category_id) REFERENCES category(category_id) ON DELETE CASCADE,
    CONSTRAINT check_type CHECK(type IN ('INCOME', 'EXPENSE'))
);

CREATE INDEX idx_transaction_account_id_date ON transaction(account_id, date);
CREATE INDEX idx_transaction_category_id_date ON transaction(category_id, date);