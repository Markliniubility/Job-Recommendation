package com.laioffer.job.recommendation;

import com.laioffer.job.db.MySQLConnection;
import com.laioffer.job.entity.Item;
import com.laioffer.job.external.GitHubClient;

import java.util.*;

public class Recommendation {

    public List<Item> recommendItems(String userId, double lat, double lon) {
        List<Item> recommendItems = new ArrayList<>();
        //step 1: get connections
        MySQLConnection connection = new MySQLConnection();
        Set<String> favoritedItemIds = connection.getFavoriteItemIds(userId);
        //step2 get all the keywords
        Map<String,Integer> allKeywords = new HashMap<>();
        for (String itemId: favoritedItemIds) {
            Set<String> keywords = connection.getKeywords(itemId);
            for (String keyword: keywords) {
                allKeywords.put(keyword,allKeywords.getOrDefault(keyword,0) + 1);
            }
        }
        connection.close();
        List<Map.Entry<String,Integer>> keywordList = new ArrayList<>(allKeywords.entrySet());
        keywordList.sort((Map.Entry<String,Integer> m1,Map.Entry<String,Integer> m2) -> Integer.compare(m1.getValue(), m2.getValue()));
        if (keywordList.size() > 3) {
            keywordList = keywordList.subList(0,3);
        }
        //step3 search based on key words. filter out favorite items
        Set<String> visitedItemIds = new HashSet<>();
        GitHubClient client = new GitHubClient();
        for (Map.Entry<String, Integer> keyword : keywordList) {
            List<Item> items = client.search(lat, lon, keyword.getKey());

            for (Item item : items) {
                if (!favoritedItemIds.contains(item.getId()) && !visitedItemIds.contains(item.getId())) {
                    recommendItems.add(item);
                    visitedItemIds.add(item.getId());
                }
            }
        }
        //step4 return
        return recommendItems;
    }
}
