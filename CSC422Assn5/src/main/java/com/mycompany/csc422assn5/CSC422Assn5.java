/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.csc422assn5;

import java.util.ArrayList;
/*
 * @author veltk
 */
public class CSC422Assn5 {

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
        
        // randomly generates array of survivors
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
        
        // each survivor randomly is assigned a weapon
        Weapon[] weaponArray = {new Weapon("Shotgun", 100, 100), 
            new Weapon("SMG", 200, 10), 
            new Weapon("AR", 80, 50), 
            new Weapon("Pistol", 30, 40),
            new Weapon("Axe", 30, 100),
            new Weapon("Crowbar", 20, 100),
            new Weapon("Frying Pan", 10, 100)
        };
        
        for (Survivor survivor : survivors) {
            int randomWeapon = (int)((Math.random() * (5 - 0)) + 0);
            survivor.setArms(weaponArray[randomWeapon]);
        }
        
        
        System.out.println("There are " + numSurvivors + " survivors trying to make it to safety. (" + numScientist + " scientists, " + numCivilian + " civilians, " + numSoldier + " soldiers)\n");
        
        System.out.println("But there are " + numInfected + " zombies waiting for them. (" + numCom + " common infected and " + numTank + " tanks)\n");
        
        
        int numSurvivorsLeft = numSurvivors;
               
        boolean survivorsLeft = true;       
        boolean infectedLeft = true;
        
        while (survivorsLeft && infectedLeft) {              
               
        // loop begins assuming none are left and detects any still alive    
        infectedLeft = false;
        survivorsLeft = false;
            
        for (Survivor surv : survivors) {
            for (Infected inf : infected) {
                
                int randomHit = (int)(Math.random() * 100);
                if (randomHit <= surv.getArms().getAccuracy()) {
                    if (surv.getHealth() > 0 && inf.getHealth() > 0) {
                        int health = inf.getHealth();
                        // damage done using weapon damage + survivor damage
                        inf.setHealth(health - (surv.getArms().getDamage() + surv.getAttack()));
                        if (inf.getHealth() <= 0) {
                           System.out.println(surv.getName() + " killed " + inf.getName() + " using " + surv.getArms().getName());
                        } 
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
                        System.out.println(inf.getName() + " killed " + surv.getName()); 
                        numSurvivorsLeft--;
                    }
                }   
            }
            
            if (inf.getHealth() > 0) {
                infectedLeft = true;
            }
        }       
        }
        System.out.println("");
        System.out.println("There are " + numSurvivorsLeft + " survivors left.");       
        
    }
}
