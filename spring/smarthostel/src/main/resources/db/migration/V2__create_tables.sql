CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       role user_role NOT NULL DEFAULT 'RESIDENT',
                       name VARCHAR(100) NOT NULL,
                       phone VARCHAR(20),
                       address TEXT,
                       nid VARCHAR(20),
                       is_active BOOLEAN DEFAULT TRUE,
                       email_verified BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE menu (
                      id BIGSERIAL PRIMARY KEY,
                      date DATE NOT NULL,
                      meal_type meal_type NOT NULL,
                      items TEXT NOT NULL,
                      description TEXT,
                      price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
                      is_available BOOLEAN DEFAULT TRUE,
                      created_by TEXT NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      UNIQUE(date, meal_type)
);

CREATE TABLE meal_packages (
                               id BIGSERIAL PRIMARY KEY,
                               user_id UUID NOT NULL,
                               total_meals INTEGER NOT NULL CHECK (total_meals IN (30, 60)),
                               used_meals INTEGER DEFAULT 0 CHECK (used_meals >= 0),
                               remaining_meals INTEGER GENERATED ALWAYS AS (total_meals - used_meals) STORED,
                               purchase_date DATE DEFAULT CURRENT_DATE,
                               expiry_date DATE NOT NULL,
                               amount_paid DECIMAL(10,2) NOT NULL,
                               discount_percent DECIMAL(5,2) DEFAULT 0.00,
                               status package_status DEFAULT 'ACTIVE',
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE meal_bookings (
                               id BIGSERIAL PRIMARY KEY,
                               user_id UUID NOT NULL,
                               menu_id BIGINT NOT NULL,
                               booking_date DATE NOT NULL,
                               meal_type meal_type NOT NULL,
                               quantity INTEGER DEFAULT 1 CHECK (quantity > 0),
                               total_price DECIMAL(10,2) NOT NULL,
                               payment_method VARCHAR(20) DEFAULT 'PACKAGE',
                               package_id BIGINT NULL,
                               status booking_status DEFAULT 'PENDING',
                               special_instructions TEXT,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                               FOREIGN KEY (menu_id) REFERENCES menu(id) ON DELETE RESTRICT,
                               FOREIGN KEY (package_id) REFERENCES meal_packages(id) ON DELETE SET NULL,
                               UNIQUE(user_id, menu_id, booking_date)
);

CREATE TABLE payments (
                          id BIGSERIAL PRIMARY KEY,
                          user_id UUID NOT NULL,
                          booking_id BIGINT NULL,
                          package_id BIGINT NULL,
                          amount DECIMAL(10,2) NOT NULL,
                          payment_method VARCHAR(50) DEFAULT 'CASH',
                          payment_date DATE DEFAULT CURRENT_DATE,
                          transaction_reference VARCHAR(100),
                          notes TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                          FOREIGN KEY (booking_id) REFERENCES meal_bookings(id) ON DELETE SET NULL,
                          FOREIGN KEY (package_id) REFERENCES meal_packages(id) ON DELETE SET NULL
);