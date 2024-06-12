CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    transaction_date TIMESTAMP,
    created_by VARCHAR(255),
    create_on TIMESTAMP
);
