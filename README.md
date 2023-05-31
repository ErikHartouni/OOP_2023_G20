# OOP_2023_G20


<p ><img src="sharif_logo.png" align="right" width="120" ></p>


This project is a code in `java.17` that provides a
restaurant managing system. Done in spring 2023 in
[Sharif University of Technology](https://en.sharif.edu/) ,
[Electrical Engineering](https://www.ee.sharif.edu/en/) department.

## Manual
The inputs that you give are case-sensitive.

In this Program you can be ether user , admin , poster. A User can do purchases and have restaurants ; to make restaurant , the user must send a request to admin and the admin must answer it. After purchasing your foods, your food will take time to be made, and then a poster must get the food and bring it to the buyer.

1. Create User

        CREATE USER < USERNAME > < PASSWORD >
   example :

        CREATE USER eeStudent 2023OOP
   * the `USERNAME` length must be at least 4 characters 
   * the `PASSWORD` length must be at least 6 characters 
   * you cannot create a user when you are logged in
2. Log in

         LOGIN  USER < USERNAME > < PASSWORD >
3. Creation Of Restaurant
         
         CREATE RESTAURANT < RESTAURANT NAME > < RESTAURANT TYPE >

      * your `RESTAURANT TYPE` can be one of these:
   
        * Italian
        * Iranian
        * MiddleEastern
        * FastFood
        * MolecularGastronomy
        * SeaFood
        
4. See Your Restaurants
    
    In order to see your owned restaurants input 
        
        SHOW MY RESTAURANTS
    * This will give a list of your owned restaurants' name and id.
5. Entering Restaurant

    Then you can use the restaurant id to enter your restaurant menu;

        CHOSE MY RESTAURANT < RESTAURANT ID >
6. Creating Food

    After you enter the restaurant menu, you can create your own foods

        ADD FOOD < FOOD NAME > < FOOD PRICE > < FOOD TYPE > < TIME TO MAKE >
    * `FOOD TYPE` can be one of these :
      * ColdDrink
      * HotDrink
      * Soup
      * Salad
      * Breakfast
      * MainDish
    * `TIME TO MAKE` is a time that first you write minutes then seconds like `05 45`
7. See Your Restaurant Menu

    In order to see and chose your foods and change its setting, write

        SHOW MY FOODS 
        CHOSE MY FOOD < FOOD ID > 
        
## CREATORS

| Name                                                                          | Email                    | 
|-------------------------------------------------------------------------------|--------------------------|
| [Erik Hartouni](https://github.com/ErikHartouni "GitHub")                     | erikhartouni20@gmail.com |
| [Ashkan Yousefina](https://github.com/Ashkan-Yousefnia "GitHub")              |                          |
| [Seyed Mohammad Sadegh Ahmadzadeh](https://github.com/SMSAhmadzadeh "GitHub") |                          |

