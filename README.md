#  Rewards Program API

The system supports transaction storage, reward calculation for the last 90 days, monthly breakdowns, and robust exception handling.

###  **Save Transaction API**

**Method:** `POST`  
**URL:** `http://localhost:8080/api/rewards/saveRewards`

####  Request Body

```json
{
  "customerId": 1,
  "amount": 120.0,
  "date": "2025-12-17"
}
```

####  Response

```json
{
  "id": 1,
  "customerId": 1,
  "amount": 120.0,
  "date": "2025-12-17"
}
```

---

###  **Get Rewards for Customer**

**Method:** `GET`  
**URL:** `http://localhost:8080/api/rewards/2`

####  Response

```json
{
  "customerId": 1,
  "monthlyRewards": [
    {
      "month": "OCTOBER",
      "points": 0
    },
    {
      "month": "SEPTEMBER",
      "points": 49
    },
    {
      "month": "NOVEMBER",
      "points": 248
    }
  ],
  "totalPoints": 297
}
```
