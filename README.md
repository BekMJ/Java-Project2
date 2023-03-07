# Project 2: Ethereum Blocks 

A blockchain is a database of transactions that is updated and shared across many computers in a network. Every time a new set of transactions is added, it’s called a “block” - hence the name blockchain. Most blockchains are public, and you can only add data, not remove. If someone wanted to alter any of the information or cheat the system, they’d need to do so on the majority of computers on the network. For this project, we will be using a dataset of 100 blocks in the Ethereum blockchain.

You will be updating your Blocks.java class to accomplish the tasks laid out by the 
[Project 2 pdf](./CS2334P2FA22.pdf) and Driver.java. 

## Updated Blocks UML

<img src=./BlocksUML.PNG width=50% height=50%>

Feel free to add your own helper methods as needed. 

## Methods

### Read File

You will need to update your `readFile()` method to read in the two new fields, timestamp and transaction_count. 
They are columns 17 and 18 respectively in the data file. 

### Constructors

Same as Project 1:

`Blocks()`: Initialize no fields. When printed using the toString() method it should return "Empty Block" exactly. 

`Blocks(int number)`: Initialize the Block number. When printed using the toString() method it should return "Block Number: `number`" exactly.

`Blocks(int number, String miner)`: Initialize the Block number and miner address. When printed using the toString() method it should return "Block Number: `number` Miner Address: `miner`" exactly. 

New for Project2:

`Blocks(int number, String miner, long timestamp, int transactions)`: Initialize the Block number, miner address, timestamp, and transaction_count. When printed using the toString() method it should return "Block Number: `number` Miner Address: `miner`" exactly.

### Getters

Same as Project 1:

`getNumber()`: Should return the Block number. 

`getMiner()`: Should return the miner address. 

`getBlocks()`: Should return a copy of the blocks ArrayList. Part of this project's grade will be making sure your blocks ArrayList is protected by encapsulation. 

New for Project 2:

`getTransactions()`: Should return the transactions.

`getDate()`: Should return the String representation of the date / time of timestamp according to the format in section 2.3 of the [project pdf](./CS2334P2FA22.pdf). 

### Other Methods

Same as Project 1:

`calUniqMiners()`: Should print to output the number of unique miners in the data, and a pair of lines for each one giving its miner address and the frequency at which it appears. 

`blockDiff(Blocks A, Blocks B)`: Should return the difference between A's and B's Block number. The result can be positive or negative depending on the order the Blocks are supplied.

`getBlockByNumber(int num)`: Should return the Blocks object you read from the file that corresponds to the given Block number. 

New for Project 2:

`timeDiff(Blocks first, Blocks second)`: Should take two Blocks as input, and print to the console the difference in their times in hours, minutes, and seconds. The order the Blocks are given in should not matter, the resulting difference in time should be the same. The sample output and format for this method is in section 2.3 of the [project pdf](./CS2334P2FA22.pdf).

`transactionDiff(Blocks first, Blocks second)`: Should take two Blocks as input, and calculate the total transactions of the Blocks between those two Blocks (not inclusive). 

For example, the data file spans from Block 15049308 to Block 15049407 in an unsorted order. The transactionDiff between those two Blocks would be the sum of the transaction_counts of all the Blocks between them (Blocks 15049309 - 15049406). 

Another example would be the transactionDiff between Block 15049310 and Block 15049315. In this case, you would add all the transactions from the Blocks between them (Block 15049311 to Block 15049314). Notice you should not be including the Blocks that are given in that calculation. 

Since transactionDiff needs to find the Blocks between certain Blocks based on the Block number, the data needs to be sorted first which you will do in the `sortBlocksByNumber()` method below. More info and sample output for this method is in section 2.4 of the [project pdf](./CS2334P2FA22.pdf). 


`sortBlocksByNumber()`: Should sort your blocks ArrayList in ascending order based on Block number. This can be done by implementing the comparable interface and overriding the compareTo method. 

## Grading

You will be graded on: 
* Zylabs Submission: 80 points possible
* at least 10 github commits: 10 points possible
* generated Javadocs: 10 points possible
* make sure Javadocs generates in the `doc` folder (this should be default)

There are no late submissions allowed.

