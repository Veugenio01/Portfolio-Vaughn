# -*- coding: utf-8 -*-
"""
Created on Sun Feb  6 22:27:06 2022

@author: veuge
"""

class Ship:
    
    def __init__(self, size, orient, orgin):
        self.size = size
        self.orient = orient
        self.orgin = orgin
        if(orient=="horizontal"):  
            self.last = [self.orgin[0], (self.orgin[1] + self.size - 1)]
        else: 
            self.last = [(self.orgin[0] + self.size - 1), self.orgin[1]]
        self.hits = []
        
    def hasSunk(self):
        return(self.size - len(self.hits) == 0)
        
    #Finds right most "starting" ship
    def findRight(self, ship1):
         if(self.orgin[0] < ship1.orgin[0]):
             return ship1
         else:
             return self   
    
    #Finds left most "starting" ship
    def findLeft(self, ship1):
         if(self.orgin[0] < ship1.orgin[0]):
             return self
         else:
             return ship1
        
    #Finds top most "starting" ship
    def findTop(self, ship1):
         if(self.orgin[1] < ship1.orgin[1]):
             return self
         else:
             return ship1
         
    #Finds left most "starting" ship
    def findBottom(self, ship1):
         if(self.orgin[1] < ship1.orgin[1]):
             return ship1
         else:
             return self        
             
    #returns true if passed in ship interferes with another ship
    def interfere(self, ship1):
        left = self.findLeft(ship1)
        right = self.findRight(ship1)        
        top = self.findTop(ship1)
        bottom = self.findBottom(ship1)  
        
        
        if(ship1.orient == "horizontal"):
            if(self.orient == "horizontal"):
                return(left.last[1] < right.orgin[1])
            elif(ship1.orgin[0] in range(self.orgin[0],self.last[0]) and self.orgin[1] in range(ship1.orgin[1], ship1.last[1])):
                    return True
        else:
            if(self.orient == "veritical"):
                return(top.last[0] < bottom.orgin[0])
            else:
                if(self.orgin[0] in range(ship1.orgin[0],ship1.last[0]) and ship1.orgin[1] in range(self.orgin[1], self.last[1])):
                    return True
        return False
        
                
           
            
        
            
            
            