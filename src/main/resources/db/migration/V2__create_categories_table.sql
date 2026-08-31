CREATE TABLE category(
    category_id UUID DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT now(),

    CONSTRAINT CATEGORIES_PK PRIMARY KEY (category_id),
    CONSTRAINT CATEGORIES_FK FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE CASCADE,
    CONSTRAINT uq_category_user_name UNIQUE (name, user_id)
);