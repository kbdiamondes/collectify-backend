# Collectiy Java Springboot Back-end
![Capstone 1 Recap](https://github.com/kbdiamondes/collectify-backend/assets/68324799/64fdcd15-6100-4251-8126-d6a7df201073)
_This backend is officially used for the Mobile Application "Collectify", a mobile application aimed to ease payment recording and transaction of MSME's_

## Original & Current Developer

> Work, Take Mistakes, Learn from Mistakes, Code it , and Repeat

## Features

### First Increment

- [NEW] Contract Listing
- Contract New Entries [Data Passed from Other Group]

### Second Increment
- Record Entities from other group to Collectify

### Third Increment
- [2.1] Due Payments (Customer)
- Pay & Collect Dues

### Fourth Increment
- [3.1] Collect Payments (Individual) 
- [4.3] Collector Collections (Reseller) 

# API Documentation

This documentation provides information on how to use the API endpoints provided by the controllers in this project. The API allows you to perform various operations related to contracts, payment dues, payment collection, and collector assignment.

## Reseller Controller

### Create Contract with Client
- **URL**: `POST /contracts/{resellerId}/clients/{clientUsername}/contracts}
- **Description**: Create a new contract.
- **Request Body**: JSON representing the contract.
- **Response**: JSON representing the created contract.

## Pay Dues Controller

### Pay Dues
- **URL**: `POST /paydues/client/{clientId}/contracts/{contractId}/pay`
- **Description**: Make a payment for dues associated with a contract.
- **Path Parameters**:
  - `clientId` - The ID of the client.
  - `contractId` - The ID of the contract.
- **Request Parameters**:
  - `amount` - The amount of payment.
  - `base64Image` - Base64-encoded image data.
  - `fileName` - The name of the file.
  - `contentType` - The content type of the file.
- **Response**: Success message or error message.

## Collect Payments Controller

### Collect Payment
- **URL**: `POST /collect-payments/{resellerId}/contracts/{contractId}/collect-payment`
- **Description**: Collect payment for a contract as a reseller.
- **Path Parameters**:
  - `resellerId` - The ID of the reseller.
  - `contractId` - The ID of the contract.
- **Request Parameters**:
  - `paymentType` - The Payment Type of Transaction. 
  - `base64Image` - Base64-encoded image data.
  - `fileName` - The name of the file.
  - `contentType` - The content type of the file.
- **Response**: Success message or error message.

## Collector Collection Controller

### Assign Collector
- **URL**: `POST /assigncollectors/{resellerId}/contracts/{contractId}/assign-collector`
- **Description**: Assign a collector to a contract as a reseller.
- **Path Parameters**:
  - `resellerId` - The ID of the reseller.
  - `contractId` - The ID of the contract.
- **Request Parameter**:
  - `collectorId` - The ID of the collector.
- **Response**: Success message or error message.


# Getters for Screen List


## Due Payments Controller [CLIENT SENTITY]

### Get Client with Unpaid Contracts
- **URL**: `GET /due-payments/client/{clientId}/unpaid-contracts`
- **Description**: Retrieve a client's information along with their unpaid contracts.
- **Parameters**:
  - `clientId` (Path Variable): The unique identifier for the client.
- **Response**:
  - `HTTP Status Code 200 OK` with JSON representing the client and unpaid contracts if the client exists.
  - `HTTP Status Code 404 Not Found` if the client does not exist.

### Get Client with Paid Contracts
- **URL**: `GET /due-payments/client/{clientId}/paid-contracts`
- **Description**: Retrieve a client's information along with their paid contracts.
- **Parameters**:
  - `clientId` (Path Variable): The unique identifier for the client.
- **Response**:
  - `HTTP Status Code 200 OK` with JSON representing the client and paid contracts if the client exists.
  - `HTTP Status Code 404 Not Found` if the client does not exist.
 
## Collection List Controller [COLLECTOR ENTITY]

### Get Assigned Paid Contracts for Collector
- **URL**: `GET /collection/{collectorId}/assigned-paid-contracts`
- **Description**: Retrieve a list of assigned paid contracts for a collector.
- **Parameters**:
  - `collectorId` (Path Variable): The unique identifier for the collector.
- **Response**:
  - `HTTP Status Code 200 OK` with JSON representing a list of paid contracts if the collector exists.
  - `HTTP Status Code 404 Not Found` if the collector does not exist.

## Transaction History Controller
- **URL**: `/transaction-history`
- **Description**: Controller for managing transaction history.
  
#### Get All Transactions By Client
- **URL**: `GET /transaction-history/client/{clientId}`
- **Description**: Retrieve all transactions by client.
- **Parameters**:
  - `clientId` (Path Variable): The unique identifier for the client.
- **Response**: A list of transaction history records.

## Payment Records Controller
- **URL**: `/payment-records`
- **Description**: Controller for managing payment records.

#### Get Payment Records For Client
- **URL**: `GET /payment-records/client/{clientId}`
- **Description**: Retrieve payment records for a specific client.
- **Parameters**:
  - `clientId` (Path Variable): The unique identifier for the client.
- **Response**:
  - `HTTP Status Code 200 OK` with JSON representing payment records if the client and records exist.
  - `HTTP Status Code 404 Not Found` if the client or records do not exist.

## Collector Payment Records Controller
- **URL**: `/collector-payment-records`
- **Description**: Controller for managing payment records for collectors.

#### Get Payment Records For Collector
- **URL**: `GET /collector-payment-records/collector/{collectorId}`
- **Description**: Retrieve payment records for a specific collector.
- **Parameters**:
  - `collectorId` (Path Variable): The unique identifier for the collector.
- **Response**:
  - `HTTP Status Code 200 OK` with JSON representing payment records if the collector and records exist.
  - `HTTP Status Code 404 Not Found` if the collector or records do not exist.

## November 3 Update
### Recent Contracts [For Collector Dashboard]
- **URL**: `GET /collection-history/collector/{collectorId}`
- **Description**: Returns a record of collector collection action with a slightly modified JSON data response.
- **Parameters**:
  - `collectorId` (Path Variable): The unique identifier for the collector.
- **Response**:
  - `HTTP Status Code 200 OK` with a list of `CollectionHistory` if the collector exists.
  - `HTTP Status Code 404 Not Found` if the collector does not exist.

### Collect [All] Payments with no Collector[Reseller]
- **URL**: `POST /collectPayments/{resellerId}/contracts/{contractId}/collect-payment`
- **Description**: Collect payment for a specific contract belonging to a reseller.
- **Parameters**:
  - `resellerId` (Path Variable): The unique identifier for the reseller.
  - `contractId` (Path Variable): The unique identifier for the contract.
  - `paymentType` (Request Param): Type of payment.
  - `base64Image` (Request Param): Base64 encoded image data.
  - `fileName` (Request Param): Name of the file.
  - `contentType` (Request Param): Type of content.
- **Response**:
  - `HTTP Status Code 200 OK` if the payment is collected successfully.
  - `HTTP Status Code 400 Bad Request` if the base64 image data is empty.
  - `HTTP Status Code 403 Forbidden` if access is denied.
  - `HTTP Status Code 500 Internal Server Error` for other failures during payment collection.

### GET Active Contracts [Reseller]
- **URL**: `GET /unpaid/{resellerId}`
- **Description**: Get clients with unpaid contracts according to the Reseller ID.
- **Parameters**:
  - `resellerId` (Path Variable): The unique identifier for the reseller.
- **Response**:
  - `HTTP Status Code 200 OK` with a list of `Contract` objects representing unpaid contracts.
  
### CRUD Schedule Payment Reminder [Client]
- **URL**: `/schedule-payment-reminder`
- **Description**: Controller for scheduling, getting, and deleting payment reminders for clients.
- **Endpoints**:
  - `GET /client/{clientId}/reminders`: Get scheduled reminders for a client.
  - `POST /set-reminder`: Set a payment reminder for a client.
  - `DELETE /delete-reminder`: Delete a payment reminder for a client.
- **Parameters**:
  - `clientId` (Path Variable): The unique identifier for the client.
  - `contractId` (Request Param): The unique identifier for the contract.
  - `reminderTitle` (Request Param): Title of the reminder.
  - `reminderDateTime` (Request Param): Date and time for the reminder.
- **Response**:
  - Various HTTP Status Codes based on success or failure of reminder operations.

### Assigned Tasks [Collector]
- **URL**: `GET /collection/{collectorId}/assigned-uncollected-contracts`
- **Description**: GET uncollected & assigned contracts with a specific collector ID.
- **Parameters**:
  - `collectorId` (Path Variable): The unique identifier for the collector.
- **Response**:
  - `HTTP Status Code 200 OK` with a list of `Contract` objects representing uncollected contracts.
  
### Payment Records [Collector & Client]
- **URL**: `GET /payment-records/client/{clientId}`
- **Description**: Records the paid dues for a client.
- **Parameters**:
  - `clientId` (Path Variable): The unique identifier for the client.
- **Response**:
  - `HTTP Status Code 200 OK` with a list of `TransactionHistory` for the client.
  - `HTTP Status Code 404 Not Found` if the client does not exist.

### Payment Records [Collector]
- **URL**: `GET /collector-payment-records/collector/{collectorId}`
- **Description**: Returns a record of all collected contracts with the specific details for a collector.
- **Parameters**:
  - `collectorId` (Path Variable): The unique identifier for the collector.
- **Response**:
  - `HTTP Status Code 200 OK` with a list of `Contract` objects representing collected payments.
  - `HTTP Status Code 404 Not Found` if no records are found.

### Total Active & Unpaid Contract Count [Reseller Dashboard]
- **URL**: `GET /resellers/{resellerId}/active-unpaid-contracts/count`
- **Description**: Used on the Reseller Dashboard to count active unpaid contracts.
- **Parameters**:
  - `resellerId` (Path Variable): The unique identifier for the reseller.
- **Response**:
  - `HTTP Status Code 200 OK` with an integer representing the count of active unpaid contracts.

### Total Due Amount of Client Active Contracts [Client Dashboard]
- **URL**: `GET /clients/{id}/total-due-amount`
- **Description**: Returns the sum (integer) of the client's unpaid active contracts.
- **Parameters**:
  - `id` (Path Variable): The unique identifier for the client.
- **Response**:
  - `HTTP Status Code 200 OK` with a `BigDecimal` representing the total due amount for the client.


### Postman Workspace

https://orange-comet-568586.postman.co/workspace/Research-%26-Capstone---Mongoose~51e037a9-5bb0-4615-82a1-08e74799080b/collection/21217716-59c730f4-671f-4ff1-8728-53354db1b23c?action=share&creator=21217716


### To the Future Devs Assign to this Project
Please Contact Keith Brylle Diamante For concerns and full review from the changes Contact Francis Manto ~ QA Jasper
### Future plans
1. App optimization[No Delays when using app]
2. Insaction Encryption
3. In BE, From Distrilink, Create new API-ENPOINT that check if the payment is confirmed. Should reflect in contract
4. In BE, need API-ENPOINT that use less storage in image capturing
5. System upgrade and Chat System CLI to COL