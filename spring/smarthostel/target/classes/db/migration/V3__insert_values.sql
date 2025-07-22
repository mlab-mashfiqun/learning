INSERT INTO users (username, password, email, role, name, phone, address, nid) VALUES
                                                                                   ('admin', '$2a$10$example_hashed_password', 'admin@smarthostel.com', 'MANAGEMENT', 'System Administrator', '+8801712345678', 'Dhaka, Bangladesh', '123456789'),
                                                                                   ('catering1', '$2a$10$example_hashed_password', 'catering@smarthostel.com', 'CATERING_STAFF', 'Catering Manager', '+8801712345679', 'Dhaka, Bangladesh', '123456790'),
                                                                                   ('resident1', '$2a$10$example_hashed_password', 'resident1@example.com', 'RESIDENT', 'Mashfiqun Nabi', '+8801712345680', 'Dhaka, Bangladesh', '123456791'),
                                                                                   ('resident2', '$2a$10$example_hashed_password', 'resident2@example.com', 'RESIDENT', 'Aritra Chakraborty', '+8801712345681', 'Dhaka, Bangladesh', '123456792');

INSERT INTO menu (date, meal_type, items, description, price, created_by) VALUES
                                                                              (CURRENT_DATE, 'BREAKFAST', 'Paratha, Egg Curry, Tea', 'Traditional Bangladeshi breakfast', 50.00, 'catering1'),
                                                                              (CURRENT_DATE, 'LUNCH', 'Rice, Fish Curry, Dal, Vegetables', 'Complete lunch meal', 80.00, 'catering1'),
                                                                              (CURRENT_DATE, 'DINNER', 'Rice, Chicken Curry, Salad', 'Dinner special', 90.00, 'catering1'),
                                                                              (CURRENT_DATE + 1, 'BREAKFAST', 'Bread, Omelet, Milk Tea', 'Continental breakfast', 45.00, 'catering1'),
                                                                              (CURRENT_DATE + 1, 'LUNCH', 'Biriyani, Raita, Shorbot', 'Special biriyani lunch', 120.00, 'catering1'),
                                                                              (CURRENT_DATE + 1, 'DINNER', 'Rice, Beef Curry, Dal', 'Regular dinner', 95.00, 'catering1');

INSERT INTO meal_packages (user_id, total_meals, amount_paid, expiry_date, discount_percent) VALUES
    ((SELECT id FROM users WHERE username = 'resident1'), 30, 2000.00, CURRENT_DATE + INTERVAL '30 days', 10.00);

INSERT INTO meal_bookings (user_id, menu_id, booking_date, meal_type, total_price, payment_method, package_id) VALUES
    ((SELECT id FROM users WHERE username = 'resident1'),
     (SELECT id FROM menu WHERE date = CURRENT_DATE AND meal_type = 'BREAKFAST'),
     CURRENT_DATE, 'BREAKFAST', 50.00, 'PACKAGE',
     (SELECT id FROM meal_packages WHERE user_id = (SELECT id FROM users WHERE username = 'resident1')));
