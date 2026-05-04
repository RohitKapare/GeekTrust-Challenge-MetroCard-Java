
## Given information: 

A new metro train has been launched from the Central station to the Airport. It is a non-stop train, which means the train will stop only at the Airport with no intermediate stops. It is also possible to return from the Airport back to the Central station. This is also a non-stop journey.

### Tech stack info:

Java version 8,
Gradlew wrapper version 5.1.

### Two stations: 
**Central station** and **airport.**

---
### Train:
- Non-stop
- No intermediate stops
- Two way journey: Central station <--> Airport

---

### Metro card: 
- Only card to be used for traveling payment.
- Card can be recharged like digital wallet with money.
- Have unique number
- To travel by this train, one needs a MetroCard.
- If not enough balance, then the **remaining** cost need to be paid by recharging card with required **remaining** amount with **auto recharge**.
- **Auto recharge:** 
	- Loads only the required amount of money for the journey.
	- Station collects 2% service fee for the transaction.
	- If ADULT passenger has 50 balance on card and he travels as single trip costing 200, then his total charge be 50(From card) + 150(Auto recharge) + 3 (2% of 150) = 203.

---

### Travel charges:
Depends on passenger's age.

| Passenger type  | Charges |
| --------------- | ------- |
| ADULT           | 200     |
| SENITOR_CITIZEN | 100     |
| KIDS            | 50      |
Charges will be collected at station from where the passenger has left, if he left from Central Station -> Airport, then charges will be collected at Central Station. Same for discount.

---

### Journey types:
- **Single trip**
- **Return journey**
	- Discount of 50% on the travel charges for return journey.
	- E.g. if SENIOR_CITIZEN goes Central Station -> Airport : Charged 100, if travels back Airport -> Central Station: charged 50 for return journey, total charges 150. 
	- Return journey must be on same day.
	- If he again goes Central Station -> Airport : Charged 100, cause it will be treated as new journey. Hence, his total charges now be 150 + 100 = 250.

---

## Goal:
Build a solution that calculates various travel charges collected at each station and print the collection summary and passenger summary.

1. The collection summary should give a breakup of the total amount collected and the total discount given.
2. The passenger summary should display the total number of passengers traveled per type in **descending order of the passenger count**.
3. If any of the passenger type have same value for passenger count then display in the **ascending order** of the passenger type for that case. E.g. :If ADULT and KID has same value then display it as ADULT <no_of_passengers> KID <no_of_passengers>.
4. Basically **ascending order** is natural order so comparable can be used. For **Descending order** comparator could be used.

---

## Assumptions:
- All passengers should have a MetroCard.
- If a passenger does not have sufficient balance in the MetroCard, then the MetroCard needs to be recharged before taking up the journey.
- The service fee for doing the recharge is collected by the origin station of the journey.
- The passenger count is calculated based on journeys E.g. : if the same passenger travels twice, the count is 2.

---

## Input format will look like: 
BALANCE <METROCARD_NUMBER> <BALANCE_IN_THE_METROCARD>  

1. <METROCARD_NUMBER> is the identifier for a given MetroCard.
2. <BALANCE_IN_THE_METROCARD> is the amount of money available in the MetroCard for journeys.

CHECK_IN <METROCARD_NUMBER> <PASSENGER_TYPE> <FROM_STATION>  

The CHECK_IN command should deduct the appropriate amount of travel charge from the MetroCard of the passenger, depending on the passenger type. If the passenger has already made a single journey, then only 50% of the travel charge should be deducted from the MetroCard for their return journey.

Input will be a 'input.txt' file whose location will be passed as command line argument.
Sample input file : 
```
BALANCE MC1 600  
BALANCE MC2 500  
BALANCE MC3 50  
BALANCE MC4 50  
BALANCE MC5 200  
CHECK_IN MC1 ADULT CENTRAL  
CHECK_IN MC2 SENIOR_CITIZEN CENTRAL  
CHECK_IN MC1 ADULT AIRPORT  
CHECK_IN MC3 KID AIRPORT  
CHECK_IN MC4 ADULT AIRPORT  
CHECK_IN MC5 KID AIRPORT  
PRINT_SUMMARY
```
Sample output : 
```
TOTAL_COLLECTION CENTRAL 300 0 
PASSENGER_TYPE_SUMMARY 
ADULT 1 
SENIOR_CITIZEN 1 
TOTAL_COLLECTION AIRPORT 403 100 
PASSENGER_TYPE_SUMMARY 
ADULT 2 
KID 2
```

### 'input.txt' file be processed in main method as follows:

```java
public static void main(String[] args) {  
  
  try {  
    FileInputStream fis = new FileInputStream(args[0]);  
    Scanner sc = new Scanner(fis);  
    while (sc.hasNextLine()) {  
      commandInvoker.invoke(sc.nextLine());  
    }  
    sc.close();  
  } catch (IOException e) {  
    e.printStackTrace();  
  }  
  
}
```

---
