
# Rewards API

Spring Boot REST API that calculates reward points for customers based on purchase transactions.

## Reward Rules
- 2 points for every dollar spent over $100
- 1 point for every dollar spent between $50 and $100

## Endpoint
GET /api/rewards

## Tech Stack
- Java 17
- Spring Boot 3.2
- Maven

## Notes
- Months are calculated dynamically from transaction date
- Includes unit tests and negative scenarios
- No hardcoded months
