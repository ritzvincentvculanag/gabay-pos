# Gabay POS

An Android-based Point of Sale (POS) system built in Java for managing sales operations at Gabay Store.

## Overview

Gabay POS provides a digital workflow for managing products, inventory, users, and store transactions. It is designed to simplify day-to-day checkout operations while keeping product and transaction data organized in a local SQLite database.

## Features

- Product management with create, update, delete, and search functionality
- Category management for organizing products
- Inventory tracking with quantity updates after completed transactions
- Cart and checkout workflow with quantity controls
- Invoice and transaction recording
- User account and role management
- Local persistence using SQLite
- RecyclerView-based lists for products, users, and invoices
- Material Design components for dialogs, forms, and actions

## Tech Stack

- **Language:** Java
- **Platform:** Android
- **Build System:** Gradle
- **Database:** SQLite
- **UI:** XML layouts, AndroidX, Material Components
- **Architecture:** Repository-based data access with model and adapter classes

## Project Structure

```text
app/src/main/java/me/jhayzonalbay/rmmcgabay/
├── db/             # Database and schema definitions
├── models/         # Domain models and RecyclerView adapters
├── repositories/   # CRUD and database operations
├── utils/          # Shared interfaces and helper utilities
└── views/          # Activities and fragments for application screens
```

## Getting Started

### Prerequisites

- Android Studio
- Android SDK
- A device or emulator running Android
- Gradle support provided by Android Studio

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/ritzvincentvculanag/gabay-pos.git
   ```

2. Open the project in Android Studio.
3. Allow Android Studio to sync the Gradle configuration.
4. Connect an Android device or start an emulator.
5. Build and run the `app` configuration.

## Usage

1. Sign in using a configured user account.
2. Add and manage products, categories, and inventory quantities.
3. Select products and add them to the checkout cart.
4. Adjust quantities or remove items as needed.
5. Complete checkout to record the invoice and update inventory.
6. Review recorded transactions and invoice information.

## Data Model

The local database includes entities for:

- Categories
- Products
- User types
- Users
- Invoices
- Purchased items

Relationships between these entities support product categorization, role-based user records, invoice ownership, and transaction line items.

## Contributing

1. Fork the repository.
2. Create a feature branch:

   ```bash
   git checkout -b feature/your-feature
   ```

3. Commit your changes with a clear message.
4. Push the branch and open a pull request.

## License

No license has been specified for this project.
