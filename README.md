# Sad Monkey NFT Marketplace

## My personal Java project for CPSC 210

#### Sad Monkey is a simple marketplace for NFTs where you can list your own collection, purchase from other collections and trade NFTs. The application will be similar to services such as *OpenSea* and *Binance NFTs Marketplace*. In order to purchase an NFT or receive payments, you need to transfer assets from your preferred wallet to your Sad Monkey account. I have chosen this project because I believe non-fungible tokens will play a big part in the digital world.

## **Features**:
- #### List your NFTs 
- #### Purchase NFTs
- #### Transfer assets To your MetaMask wallet
- #### View your Wallet



## User Stories:

- As a user, I want to be able to view different collections
- As a user, I want to be able to view my watchlist
- As a user, I want to be able to purchase an NFT
- As a user, I want to be able to add an NFT to my watchlist
- As a user, I want to be able to transfer assets to my wallet
- 
- As a user, I want to be abl to quit the app, knowing that all my data (ie: name, Email, wallet info, NFTs,...) are securely saved.
- As a User, I want to be able to login to my account and see the NFTs I watchlisted before.
- As a User, I want to be able to login to my account and see the NFTs I own.
- As a User, I want to be able to login to my account and see my current balance.


## phase 4, Task 2 (logs):
Tue Mar 29 13:46:18 PDT 2022
monkey 01 was added to the watchlist


Tue Mar 29 13:46:23 PDT 2022
monkey 02 was added to the watchlist


Tue Mar 29 13:46:46 PDT 2022
monkey 00 was added by you, Alex Lee


Tue Mar 29 13:46:56 PDT 2022
monkey 11 was added by you, Alex Lee


Tue Mar 29 13:47:31 PDT 2022
monkey 02 was purchased

## phase 4, Task 3 (Reflection):
if I had more time to work on thins project I would improve my design in the following ways:

- Try to make the whole project more cohesive focusing on ui classes (SDmGuiApp and SDMonkeyApp) since a
lot of methods in those 2 classes could be placed in a complete different class.

- Make super class for SDmGuiApp and SDMonkeyApp since both of these have a lot in common.
by doing so the coupling between the classes would be looser as well since only the super class would be highly 
coupled with classes like NFT.
- I would also use the Iterator design pattern to abstract out parts of the loops I do. More specifically,
since I am going through an NFT list (Watchlist, Collection, wallet) in many classes, I would make NFT iterable so
that I won't have to run two different for loops only to go through every single NFT object.
- As per my UML diagram, many classes are tightly coupled with the NFT class, if  I had more time to work on 
this project I would try to use the observer pattern, make NFT class the Observable and the classes that are 
highly coupled with it the observers, then I would use a single list of NFT that is shared between all these classes
- and can only be modified in NFT class, then the observer classes would have an update method which would handle 
whatever that needs to be done in that specific class.




## Citation: 
- Phase 1: Teller App, (https://github.students.cs.ubc.ca/CPSC210/TellerApp)
- Phase 2: JsonSerializationDemo (GitHub: )








