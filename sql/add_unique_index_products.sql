-- SQL migration: Add UNIQUE index to `products` table
-- IMPORTANT: Backup your database before running any ALTER statements.
-- Choose ONE of the following options and run it against your project's database.

-- Option A: Make product `name` unique globally (no two products can share the same name)
-- Use this if product names must be globally unique across the store.
-- ALTER TABLE products
-- ADD UNIQUE INDEX idx_products_name (name);

-- Option B: Make product `name` unique per category (same name allowed in different categories)
-- Use this if product names can repeat across categories but must be unique within a category.
-- ALTER TABLE products
-- ADD UNIQUE INDEX idx_products_category_name (category_id, name);

-- Notes:
-- 1) Uncomment only the option you want to apply and run it once.
-- 2) If the table or columns use different names in your schema, update them accordingly.
-- 3) If your table already has duplicate rows that violate the chosen constraint, the ALTER will fail.
--    To find duplicates (per category example):
--    SELECT category_id, name, COUNT(*) c FROM products GROUP BY category_id, name HAVING c > 1;
--    You must resolve duplicates (delete/rename/merge) before adding the UNIQUE index.

-- Example run (MySQL/MariaDB):
-- 1) Backup: mysqldump -u <user> -p <database> products > products_backup.sql
-- 2) Apply migration: mysql -u <user> -p <database> < sql/add_unique_index_products.sql

-- Alternative (run a single statement directly):
-- mysql -u <user> -p -D <database> -e "ALTER TABLE products ADD UNIQUE INDEX idx_products_category_name (category_id, name);"
