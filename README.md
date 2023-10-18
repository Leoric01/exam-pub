# exam-pub
Pub simulation
Struktura api: 
1. GET /users 
Vraci vsechny objekty User s atributy: 
a. Id 
b. Name 
c. IsActive 
d. IsAdult 
e. Pocket 
2. GET /users/{id} 
Vraci User s danym id a jeho objednavky: 
a. Id 
b. Name 
c. IsActive 
d. IsAdult 
e. Pocket 
f. Orders 
i. Id 
ii. ProductName 
iii. Amount 
iv. Price 
3. GET /drink-menu 
Vraci napojovy listek, drinky maji tyto atributy: 
a. Id 
b. ProductName 
c. Price 
d. IsForAdult 
4. POST /buy //Validace zdali je uživatel plnoletý a zdali má dostatek financí v peněžence 
a. UserId 
b. ProductId
c. Price 
5. GET /summary 
a. /summary/all 
Vraci vsechny objednavky daneho produktu/drinku pro vsechny drinky a jejich celkovy soucet: 
i. Product 
ii. Amount 
iii. UnitPrice 
iv. SummaryPrice 
b. /summary/product 
Vraci vsechny objednavky daneho drinku pro kazdy drink: 
i. UserId 
ii. Amount 
iii. Price 
c. /summary/user 
Vraci vsechny objednavky daneho uzivatele pro vsechny uzivatele: i. UserId 
ii. Product 
iii. Price

