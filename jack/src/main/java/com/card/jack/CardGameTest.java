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

package com.card.jack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class CardGameTest {
    public static void main(String[] args) throws Exception {
        String baseUrl = "https://deckofcardsapi.com/api/deck";

        // Step 1: Get new deck
        JSONObject newDeck = getJson(baseUrl + "/new/");
        String deckId = newDeck.getString("deck_id");
        System.out.println("✅ API is reachable. Deck ID: " + deckId);

        // Step 2: Shuffle deck
        getJson(baseUrl + "/" + deckId + "/shuffle/");

        // Step 3: Draw 6 cards
        JSONObject draw = getJson(baseUrl + "/" + deckId + "/draw/?count=6");
        JSONArray cards = draw.getJSONArray("cards");

        List<JSONObject> player1 = new ArrayList<>();
        List<JSONObject> player2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) player1.add(cards.getJSONObject(i));
        for (int i = 3; i < 6; i++) player2.add(cards.getJSONObject(i));

        int score1 = calculateScore(player1);
        int score2 = calculateScore(player2);

        System.out.println("Player 1: " + getCardCodes(player1) + " → Score: " + score1);
        System.out.println("Player 2: " + getCardCodes(player2) + " → Score: " + score2);

        if (score1 == 21) {
            System.out.println("Player 1 has blackjack!");
        } else if (score2 == 21) {
            System.out.println("Player 2 has blackjack!");
        } else {
            System.out.println("No blackjack this round.");
        }
    }

    private static JSONObject getJson(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
            new InputStreamReader(conn.getInputStream())
        );
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) content.append(inputLine);
        in.close();
        conn.disconnect();

        return new JSONObject(content.toString());
    }

    private static int calculateScore(List<JSONObject> hand) {
        int score = 0;
        int aces = 0;
        for (JSONObject card : hand) {
            String value = card.getString("value");
            switch (value) {
                case "KING":
                case "QUEEN":
                case "JACK":
                    score += 10;
                    break;
                case "ACE":
                    score += 11;
                    aces++;
                    break;
                default:
                    score += Integer.parseInt(value);
            }
        }
        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }
        return score;
    }

    private static String getCardCodes(List<JSONObject> hand) {
        List<String> codes = new ArrayList<>();
        for (JSONObject card : hand) {
            codes.add(card.getString("code"));
        }
        return String.join(", ", codes);
    }
}
