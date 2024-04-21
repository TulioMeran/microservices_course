CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` int AUTO_INCREMENT PRIMARY KEY,
    `name` Varchar(100) NOT NULL,
    `email` Varchar(100) NOT NULL,
    `mobile_number` Varchar(20) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` Varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` Varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `accounts`(
    `customer_id` int NOT NULL,
    `account_number` int AUTO_INCREMENT PRIMARY KEY,
    `account_type` Varchar(100) NOT NULL,
    `branch_address` Varchar(200) NOT NULL,
    `communication_sw` BOOLEAN,
    `created_at` date NOT NULL,
    `created_by` Varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` Varchar(20) DEFAULT NULL
);