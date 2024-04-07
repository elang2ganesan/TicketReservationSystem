# TicketReservationSystem

# 1. Purchase Ticket API

API: http://localhost:8080/train/booking

Request Body:
{
    "trainNumber": "1234",
    "sourceStation": "London",
    "endStation": "France",
    "user": {
        "userId": "elango",
        "firstName": "Elangovan",
        "lastName": "Ganesan",
        "emailAddress": "eganesan@gmail.com"
    },
    "ticketFare": "2000"
}


Response: 200 https code with Ticket details as below
{
    "httpStatus": "OK",
    "message": "Ticket is reserved successfully",
    "data": {
        "ticketNumer": "131",
        "sourceStation": "London",
        "endStation": "France",
        "ticketPrice": 2000,
        "status": "RESERVED",
        "coachName": "sectionA",
        "seatNumber": 1,
        "trainNumber": 1234,
        "user": {
            "userId": "elango",
            "firstName": "Elangovan",
            "lastName": "Ganesan",
            "emailAddress": "eganesan@gmail.com"
        }
    }
}


# 2. Fetch Ticket Details based on the userId

API : http://localhost:8080/train/booking/elango

Response : 200 https code with below details,

{
    "data": {
        "ticketNumer": "131",
        "sourceStation": "London",
        "endStation": "France",
        "user": {
            "userId": "elango",
            "firstName": "Elangovan",
            "lastName": "Ganesan",
            "emailAddress": "eganesan@gmail.com"
        },
        "ticketPrice": 2000.0,
        "status": "RESERVED",
        "coachName": "sectionA",
        "seatNumber": 1,
        "trainNumber": 1234
    },
    "httpStatus": "OK",
    "message": "Ticket detail is fetched successfully"
}

# 3. Modify the ticket by passing userId

API : http://localhost:8080/train/booking

Request Body : {"userId":"elango"}

Response Body: 

{
    "data": {
        "ticketNumer": "131",
        "sourceStation": "London",
        "endStation": "France",
        "user": {
            "userId": "elango",
            "firstName": "Elangovan",
            "lastName": "Ganesan",
            "emailAddress": "eganesan@gmail.com"
        },
        "ticketPrice": 2000.0,
        "status": "RESERVED",
        "coachName": "sectionA",
        "seatNumber": 2,
        "trainNumber": 1234
    },
    "httpStatus": "OK",
    "message": "Ticket detail is updated successfully"
}
