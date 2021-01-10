# CashRegister
Cash Register application that accepts $20, $10, $5, $2 and $1 bills

Example usage:

start program, waiting for a command
```
> java Main ...
ready
```


show current state, including total and each denomination:

$Total #$20 #$10 #$5 #$2 #$1

for example, $68 1 2 3 4 5 means:

Total=$68 $20x1 $10x2 $5x3 $2x4 $1x5
```
> show
$68 1 2 3 4 5
```


put bills in each denomination: #$20 #$10 #$5 #$2 #$1

and show current state
```
> put 1 2 3 0 5
$128 2 4 6 4 10
```


take bills in each denomination: #$20 #$10 #$5 #$2 #$1

and show current state
```
> take 1 4 3 0 10
$43 1 0 3 4 0
```


show requested change in each denomination: #$20 #$10 #$5 #$2 #$1

and remove money from cash register
```
> change 11
0 0 1 3 0
> show
$32 1 0 2 1 0
```


show error if there are insufficient funds or no change can be made
```
> change 14
sorry
```


exit program
```
> quit
Bye
```
