# -*- coding: utf-8 -*-
"""
Created on Sun Feb  6 22:03:51 2022

@author: veuge
"""

from random import randint
from Ship import Ship
import pandas as pd

#test = Ship(3, "horizontal", [2,3])

colSize = 10
rowSize = 10
ships = []
temp = []


print("Welcome to BattleShip!")
#TODO write rulese and such
def run():
    misses = 0
    lives = 10 #maybe get from user
    
    print(board.to_string())

    while(True):
        rowGuess = int(input("Column: ")) 
        colGuess = int(input("Row: ")) 
        guess = [rowGuess, colGuess]
        if(guess[1] >= board.shape[0] or guess[0] >= board.shape[0]):#if guess is out of bounds
            print("Out of bounds")
        elif(hit(guess)):#if guess is correct
            board.at[colGuess, rowGuess] = "X"
            print("Hit on " + str(colGuess) + ", " + str(rowGuess))
            print(board.to_string())
        if(not run):
            break
        
        else:#if guess is a miss
            board[colGuess][rowGuess] = "~"
            print("Miss on " + str(colGuess) + "," + str(rowGuess))
            print(board.to_string())
            misses += 1
            if(misses < lives):
                inp = input("New game: 'N'  || Quit: 'Q'")
                if inp.lower() == "q":
                    break
                
        print(board.to_string())
        
        
    


for k in range(10):
    temp.append(k)
    
board = pd.DataFrame(index = temp, columns = temp)
for k in range(10):
    for p in range(10):
        board.at[k,p] = "O"

        
def printBoardWShips(board, ships):
    tempBoard = board
    for ship in ships:
        if (ship.orient == "horizontal"):
            for i in range(ship.orgin[1], ship.last[1]+1):
                tempBoard.at[ship.orgin[0], i] = " "
                #print("at: "+ str(ship.orgin[0])+", "+str(i))
        else:
            for i in range(ship.orgin[0], ship.last[0]+1): 
                tempBoard.at[i, ship.orgin[1]] = " "
                #print("at: "+str(i)+", "+str(ship.orgin[1]))
    print(tempBoard.to_string())

def makeShip(size, boardSize):
    run = True
    while(run):
        orgin = []
        orientInt = randint(0, 1)# 0=horizontal, 1=verticle
        if(orientInt == 0):
            orgin.append(randint(0,boardSize-1))#row
            orgin.append(randint(0,boardSize-size))#colum
            ret = Ship(size,"horizontal", orgin)
        else:
            orgin.append(randint(0,boardSize-size))#row
            orgin.append(randint(0,boardSize-1))#column
            ret = Ship(size,"vertical", orgin)
            
        
        if(len(ships) == 0):
            break
        else:
            for ship in ships:
                if ret.interfere(ship):
                    run = True
                    continue
                else:
                    run = False
        
    return ret

def hit(guess):
    for ship in ships:
        #if they already guessed it
        #TODO is this check needed?
        for hit in ship.hits:
            if(hit == guess):
                print("You have already hit this coordinate")
                return False
        
        #if its a hit
        if ((guess[0] in range(ship.orgin[0]-1, ship.last[0]+1)) and (guess[1] in range(ship.orgin[1]-1, ship.last[1]+1))):
            gotHit(guess, ship)
            return True
        
    return False                           


def gotHit(loc, ship):
    ship.hits.append(loc)
    if (ship.hasSunk):
        ships.remove(ship)


    





#testShip1 = Ship(3, "horizontal", [9,1])
#testShip2 = Ship(3, "vertical", [3,3])
#ships.append(testShip1)
#ships.append(testShip2)
ships.append(makeShip(5, board.shape[0]))
ships.append(makeShip(4, board.shape[0]))
ships.append(makeShip(3, board.shape[0]))
ships.append(makeShip(2, board.shape[0]))

printBoardWShips(board, ships)
#print(hit([3,3]))
run()