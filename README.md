# Collectiy Java Springboot Back-end
![Capstone 1 Recap](https://github.com/kbdiamondes/collectify-backend/assets/68324799/64fdcd15-6100-4251-8126-d6a7df201073)
_This backend is officially used for the Mobile Application "Collectify", a mobile application aimed to ease payment recording and transaction of MSME's_

## Original & Current Developer

> Their days and weeks worth of work is less than 5 hours to me.
Keith Brylle Diamante

## Features

### First Increment

- [NEW] Contract Listing
- [2.1] Due Payments (Customer)

### Second Increment
- [3.1] Collect Payments (Individual) 
- [4.3] Collector Collections (Reseller) 


### Third Increment
### Fourth Increment
- To be determined on October 5th (Daylight)

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
- **URL**: `GET /client/{clientId}/unpaid-contracts`
- **Description**: Retrieve a client's information along with their unpaid contracts.
- **Parameters**:
  - `clientId` (Path Variable): The unique identifier for the client.
- **Response**:
  - `HTTP Status Code 200 OK` with JSON representing the client and unpaid contracts if the client exists.
  - `HTTP Status Code 404 Not Found` if the client does not exist.

### Get Client with Paid Contracts
- **URL**: `GET /client/{clientId}/paid-contracts`
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


### Postman Workspace

https://orange-comet-568586.postman.co/workspace/Research-%26-Capstone---Mongoose~51e037a9-5bb0-4615-82a1-08e74799080b/collection/21217716-59c730f4-671f-4ff1-8728-53354db1b23c?action=share&creator=21217716
