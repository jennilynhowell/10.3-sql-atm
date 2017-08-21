# 10.3-sql-atm
TIY Week 10, Day 3: A Java ATM using SQLite

#### Performance Objectives  
Manage state in a menu driven console application
Design a database schema to perform all appropriate banking tasks
Define your classes so that they can be mapped to tables in the database
Normal Mode  
Your application should include a user interface which is easy to use and looks interesting. Spend some time designing the layout. The user interface should make the functionality clear.

The banking tasks you must implement are as follows:

##### Deposit an amount
Withdraw an amount
Get a running total of account balance.
After each operation you should allow the user to choose another operation from the user interface. One of those options can be to quit the program.

##### A bit of advice  
This program may not sound too bad but it is INCREDIBLY DIFFICULT!!! You will have to make sure you're storing the transaction records appropriately to track if it is a withdraw or a deposit. That way you can calculate an account balance easier.

How you store the running total is up to you. In another table, A method on the model that calculates it on the fly.

A user should not be allowed to withdraw money if that withdraw will overdraft them.

##### One more thing  
You cannot remove any records from the ATM records. So I shouldn't be able to remove the fact that I just withdrew $10. Also you will need to accurately plan how to store your values as a Deposit is usually a + amount and a Withdraw is usually a - amount. (In other words, don't bother implementing DELETE functionality)
