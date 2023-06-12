/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.zombieproject;

import java.util.ArrayList;

/**
 
 * Part 2: Implement your team solution to the Zombie War. You must use 
 * GitHub and pull request. Also, please have other team members review your pull requests.

Tasks:

Write the zombie character classes (common infected, tank)
Write the survivor character classes (scientist, civilian, soldier)
Write the program as described below
Create several releases (see sample runs and descriptions below)
 

Release 1.0's sample run:

We have 17 survivors trying to make it to safety.

But there are 8 zombies waiting for them.

It seems 6 have made it to safety.

 

Release 2.0's sample run:

We have 5 survivors trying to make it to safety (0 scientist, 3 civilians, 2 soldiers)

But there are 9 zombies waiting for them (2 common infected, 7 tanks)

   Mercenary 1 killed CommonInfected 0

   Mercenary 1 killed CommonInfected 1

   Tank 2 killed Civilian 0

   Tank 2 killed Civilian 1

   Tank 2 killed Civilian 2

   Tank 4 killed Soldier 0

   Tank 4 killed Soldier 1

None of the survivors made it.
 * @author veltk
 */
public class ZombieProject {

    public static void main(String[] args) {
        // randomly generate array of zombies
        int numInfected = (int)((Math.random() * (10 - 3)) + 3);
        
        ArrayList<Infected> infected = new ArrayList<>();
        
        int numCom = 0;
        int numTank = 0;
        
        for (int i = 0; i < numInfected; i++) {

            int infectedSel = (int)((Math.random() * (3 - 1)) + 1);
            if (infectedSel == 1) {
                numCom++;
                infected.add(new Infected("Common " + numCom, 5, 30));
            }
            else if (infectedSel == 2) {
                numTank++;
                infected.add(new Infected("Tank " + numTank, 20, 150));
            }
        }
        
        // randomly generate array of survivors
        int numSurvivors = (int)((Math.random() * (10 - 3)) + 3);
        
        ArrayList<Survivor> survivors = new ArrayList<>();
        int numScientist = 0;
        int numCivilian = 0;
        int numSoldier = 0;
        
        for (int i = 0; i < numSurvivors; i++) {
            int survivorSel = (int)((Math.random() * (3 - 1)) + 1);
            if (survivorSel == 1) {
                numScientist++;
                survivors.add(new Survivor("Scientist " + numScientist, 2, 20));
            }
            else if (survivorSel == 2) {
                numCivilian++;
                survivors.add(new Survivor("Civilian " + numCivilian, 5, 50));
            }
            else if (survivorSel == 3) {
                numSoldier++;
                survivors.add(new Survivor("Soldier " + numSoldier, 10, 100));
            }
        }
        
        System.out.println("There are " + numSurvivors + " survivors trying to make it to safety. ");//(" + numScientist + " scientists, " + numCivilian + " civilians, " + numSoldier + " soldiers)\n");
        
        System.out.println("But there are " + numInfected + " zombies waiting for them.");// (" + numCom + " common infected and " + numTank + " tanks)\n");
        
//        for (Survivor sur : survivors) {
//            System.out.println(sur.getName());
//        }
//        for (Infected in : infected) {
//            System.out.println(in.getName());
//        }
        int numSurvivorsLeft = numSurvivors;
               
        boolean survivorsLeft = true;       
        boolean infectedLeft = true;
        while (survivorsLeft && infectedLeft) {              
               
        infectedLeft = false;
        survivorsLeft = false;
            
        for (Survivor surv : survivors) {
            for (Infected inf : infected) {
                
                if (surv.getHealth() > 0 && inf.getHealth() > 0) {
                    int health = inf.getHealth();
                    inf.setHealth(health - surv.getAttack());
                    if (inf.getHealth() <= 0) {
                       //System.out.println(surv.getName() + " killed " + inf.getName());
                    } 
                }

            }
            
            if (surv.getHealth() > 0) {
                survivorsLeft = true;
            }
        }       

               
        for (Infected inf : infected) {
            for (Survivor surv : survivors) {
                if (inf.getHealth() > 0 && surv.getHealth() > 0) {
                    int health = surv.getHealth();
                    surv.setHealth(health - inf.getAttack());   
                    if (surv.getHealth() <= 0) {
                        //System.out.println(inf.getName() + " killed " + surv.getName()); 
                        numSurvivorsLeft--;
                    }
                }

                
            }
            
            if (inf.getHealth() > 0) {
                infectedLeft = true;
            }
        }       
        }
        //System.out.println("");
        System.out.println("\nIt seems " + numSurvivorsLeft + " have made it to safety.");       
        // print remaining survivors and zombies?
        
        
    }
}
