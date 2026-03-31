CREATE TABLE appointment (
    id BIGSERIAL PRIMARY KEY,
    service VARCHAR(255) NOT NULL,
    professional VARCHAR(255) NOT NULL,
    scheduled_date_time TIMESTAMP NOT NULL,
    client VARCHAR(255) NOT NULL,
    client_phone VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_client_datetime UNIQUE (client, scheduled_date_time)
);
