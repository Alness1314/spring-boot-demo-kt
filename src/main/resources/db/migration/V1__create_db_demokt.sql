CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "username" VARCHAR(64) NOT NULL UNIQUE,
    "password" VARCHAR(256) NOT NULL,
    "full_name" VARCHAR(256) NOT NULL,
    "send_expiration_alert" BOOLEAN NOT NULL DEFAULT FALSE,
    "profile" VARCHAR(256) NOT NULL,
    "created_at" TIMESTAMP,
    "updated_at" TIMESTAMP,
    "erased" BOOLEAN NOT NULL DEFAULT FALSE
);

-- Crear tabla
CREATE TABLE modules (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "title" VARCHAR(128) NOT NULL UNIQUE,
    "subtitle" VARCHAR(1024),
    "path" VARCHAR(512) NOT NULL,
    "icon" VARCHAR(256),
    "level" VARCHAR(128) NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "erased" BOOLEAN NOT NULL DEFAULT FALSE
);