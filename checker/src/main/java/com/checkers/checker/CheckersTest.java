/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.checkers.checker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckersTest {
    public static void main(String[] args) {
    	System.setProperty("webdriver.chrome.driver","C:\\Users\\pc\\Downloads\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.gamesforthebrain.com/game/checkers/");

        try {
            WebElement board = driver.findElement(By.id("board"));
            System.out.println("Site is up and board is loaded.");

            // Perform moves
            
            makeMove(driver, "space02", "space13");
            makeMove(driver, "space11", "space02");
            makeMove(driver, "space62", "space73");
            makeMove(driver, "space71", "space62");
            Thread.sleep(2000);


            System.out.println("Moves completed.");

            // Restart the game
            driver.findElement(By.xpath("//a[@href='./']")).click();           
            Thread.sleep(2000);

            // Confirm restart
            WebElement cell = driver.findElement(By.name("space02"));
            if (cell.getAttribute("class").contains("orange")) {
                System.out.println("Successfully restarted the Game.");
            } else {
                System.out.println("Encountered fail while Restart.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    private static void makeMove(WebDriver driver, String fromId, String toId) throws InterruptedException {
        driver.findElement(By.name(fromId)).click();
        driver.findElement(By.name(toId)).click();
        Thread.sleep(2000);
    }
}
