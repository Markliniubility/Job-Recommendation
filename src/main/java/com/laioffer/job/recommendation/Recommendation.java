package com.laioffer.job.recommendation;

import com.laioffer.job.db.MySQLConnection;
import com.laioffer.job.entity.Item;

import java.util.*;

public class Recommendation {
    public List<Item> recommendItems(String userId, double lat, double lon) {
        List<Item> recommendItems = new ArrayList<>();
        //step 1: get connections
        MySQLConnection connection = new MySQLConnection();
        Set<String> favoritedItemIds = connection.getFavoriteItemIds(userId);
        //step2 get all the keywords\
        Map<String,Integer> allKeywords = new HashMap<>();
        for (String itemId: favoritedItemIds) {
            Set<String> keywords = connection.getKeywords(itemId);
            for (String keyword: keywords) {
                allKeywords.put(keyword,allKeywords.getOrDefault(keyword,0) + 1);
            }
        }
        //step3 search based on key words. filter out favorite items
        //step4 return
        return recommendItems;
    }
}
