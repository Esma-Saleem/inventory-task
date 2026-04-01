# inventory-task
A repository for recruitement team assigned task.
# Dealer Inventory Module (Multi-Tenant)

## Overview
This project implements a multi-tenant Dealer Inventory module using Spring Boot.

## Features
- Tenant-based data isolation using `X-Tenant-Id` header
- Dealer creation and retrieval
- Clean layered architecture (Controller, Repository, Entity)

## API Endpoints

### Create Dealer
POST /dealers

Headers:
X-Tenant-Id: tenant1

Body:
{
  "name": "Dealer One",
  "email": "dealer@test.com",
  "subscriptionType": "PREMIUM"
}

---

### Get Dealers
GET /dealers

Headers:
X-Tenant-Id: tenant1

---

## Multi-Tenancy Approach
- Tenant ID is passed via HTTP header
- Data is filtered using `tenantId` column
- Ensures strict tenant isolation

## How to Run

```bash
./mvnw spring-boot:run
