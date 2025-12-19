CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `accounts` (
  `customer_id` int NOT NULL,
   `account_number` int AUTO_INCREMENT  PRIMARY KEY,
  `account_type` varchar(100) NOT NULL,
  `branch_address` varchar(200) NOT NULL,
  `created_at` date NOT NULL,
   `created_by` varchar(20) NOT NULL,
   `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

INSERT INTO customer
(name, email, mobile_number, created_at, created_by, updated_at, updated_by)
VALUES
('Amit Sharma', 'amit.sharma@gmail.com', '9876543210', CURRENT_DATE, 'SYSTEM', NULL, NULL),

('Priya Verma', 'priya.verma@gmail.com', '9123456780', CURRENT_DATE, 'SYSTEM', NULL, NULL),

('Rahul Singh', 'rahul.singh@gmail.com', '9988776655', CURRENT_DATE, 'SYSTEM', NULL, NULL);


INSERT INTO accounts
(customer_id, account_type, branch_address, created_at, created_by, updated_at, updated_by)
VALUES
(1, 'SAVINGS', 'Bangalore Main Branch', CURRENT_DATE, 'SYSTEM', NULL, NULL),

(2, 'CURRENT', 'Mumbai Andheri Branch', CURRENT_DATE, 'SYSTEM', NULL, NULL),

(3, 'SAVINGS', 'Delhi Connaught Place Branch', CURRENT_DATE, 'SYSTEM', NULL, NULL);
