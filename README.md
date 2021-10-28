# Splitwise

#App Setup:

1) Download and run the h2 database
   1) Download from https://www.h2database.com/html/main.html
    2) Run the DB server using the command [~downloads/h2/bin]**./h2.sh**
    3) if you face any permission issue during #2 then add permissions by running **chmod +x h2.sh**

2) Run mvn clean install
3) run the application using the command **java -jar target/splitwise-0.0.1-SNAPSHOT.jar**

## API Details:

1) **Add User API:**

HTTP METHOD: POST

URL: http://localhost:8080/splitwise/users

Payload:
{
"name": "user1",
"email": "user1@gmail.com"
}



2) **Add Group API:**

HTTP METHOD: POST

URL: http://localhost:8080/splitwise/groups

Payload:
{
"name": "group1"
}

3) **Add Expense API:**

HTTP METHOD: POST

URL: http://localhost:8080/splitwise/expenses

Payload:

{
"name": "Dinner",
"expenseType": "exact", // must be "equal" or "exact"
"paidBy": 21, // must be an existing userId
"amount": 1000,
"groupId": 24, // must be an existing groupId
"date": "29/11/2021",
"associatedUsers": [20, 21], // must be existing userIds
"individualShares": [600, 400] // number of individual shares must match number of associated users
}


4) **Add Get Users API:**

HTTP METHOD: GET

URL: http://localhost:8080/splitwise/users


5) **Add Get Groups API:**

HTTP METHOD: GET

URL: http://localhost:8080/splitwise/groups

6) **Add Get Users Group Expenses By Date API:**

HTTP METHOD: GET

URL: http://localhost:8080/splitwise/users/{userId}/groups/{groupId}/expenses?date=dd/mm/yyyy

Example: http://localhost:8080/splitwise/users/1/groups/2/expenses?date=16/08/2016

Sample output:

[
"User 21 spent Rupees 30.0 on 2021-11-29 with users 20 21 22  for Dinner",
"User 21 paid Rupees 1000.0 on 2021-11-29 with users 20 21 22 24  for Dinner",
"User 21 spent Rupees 25000.0 on 2021-11-29 with users 20 21 22 23  for Dinner",
"User 21 spent Rupees 1 on 2021-11-29 with users 20 21 22 23  for Dinner",
"User 21 spent Rupees 3 on 2021-11-29 with users 20 21 22 23  for Dinner",
"User 21 paid Rupees 80.0 on 2021-11-29 with users 20 21  for Dinner"
]

7) **Add Get All Expenses By Group API:**

HTTP METHOD: GET

URL: http://localhost:8080/splitwise/groups/{groupId}/expenses

Example: http://localhost:8080/splitwise/groups/24/expenses

Sample Output:
[
"User 20 gets back Rupees 24743.0 from user 21.",
"User 20 gets back Rupees 25033.0 from user 22.",
"User 20 gets back Rupees 25040.0 from user 23.",
"User 21 owes Rupees 24743.0 to user 20.",
"User 21 gets back Rupees 250.0 from user 22.",
"User 21 gets back Rupees 250.0 from user 24.",
"User 23 owes Rupees 25040.0 to user 20.",
"User 23 gets back Rupees 1.0 from user 21.",
"User 23 gets back Rupees 1.0 from user 22."
]




