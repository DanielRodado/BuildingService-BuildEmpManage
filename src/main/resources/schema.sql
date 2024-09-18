CREATE TABLE IF NOT EXISTS buildings (
    id SERIAL PRIMARY KEY,
    building_name VARCHAR(5) UNIQUE NOT NULL,
    status VARCHAR(30) NOT NULL,
    capacity SMALLINT NOT NULL,
    available_capacity SMALLINT NOT NULL
);
