package com.example.stanley.hi_fivolunteeringprototype.dummy;

import android.graphics.drawable.Drawable;
import android.media.Image;

import com.example.stanley.hi_fivolunteeringprototype.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Content {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Item> ITEMS = new ArrayList<Item>();

//    Array of strings
    public static final ArrayList<String> TITLES = new ArrayList<String>();
    public static final ArrayList<String> LOCATIONS = new ArrayList<String>();
    public static final ArrayList<String> DATES = new ArrayList<String>();
    public static final ArrayList<String> DESCRIPTIONS = new ArrayList<String>();

    public static final ArrayList<Integer> PICTURES = new ArrayList<Integer>();


    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Item> ITEM_MAP = new HashMap<String, Item>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createItem(i));
        }
    }

    static {
        addString("Football tournament");
        addString("Keep company to elderly in hospital");
        addString("Child education");
        addString("Toys collection");
        addString("Trip to Segovia with Santa Maria del Mar elderly house");
        addString("Food festival");
        /**/addString("Helping the elderly");
        addString("Refugee clothing");
        addString("Kids race");
        addString("Play games with elderly");

    }

    static {
        addLocation("Aluche");
        addLocation("Getafe");
        addLocation("La Latina");
        addLocation("Moncloa");
        addLocation("Segovia");
        addLocation("Barcelona");
        /**/addLocation("Lavapies");
        addLocation("Salamanca");
        addLocation("Valencia");
        addLocation("Valencia");


    }

    static {
        addDate("21.06");
        addDate("12.06");
        addDate("15.06");
        addDate("14.06");
        addDate("01.06");
        addDate("01.07");
      /**/  addDate("01.06 | 07.06");
        addDate("30.06");
        addDate("15.06");
        addDate("15.06");

    }

    static {
        addPicture(R.drawable.football);
        addPicture(R.drawable.hospital_event);
        addPicture(R.drawable.child);
        addPicture(R.drawable.toys_event);
        addPicture(R.drawable.trip_event);
        addPicture(R.drawable.food);
       /**/ addPicture(R.drawable.elderly_event);
        addPicture(R.drawable.clothes);
        addPicture(R.drawable.kids_run);
        addPicture(R.drawable.toys_event);

    }

    static {
        addDescription("Coaches needed for our youngest teams during the weekdays!");
        addDescription("Join this event and spend an afternoon doing different weekend activities with elderly people in the university hospital of Getafe.");
        addDescription("Do you want to help out our children that need some extra explanations? Weekend and Weekdays possible.");
        addDescription("Because every child deserves a present for his or her birthday we are collecting toys. Only in the morning.");
        addDescription("Every Saturday, the Santa Maria del Mar elderly house organizes a trip to a place around Madrid for its guests. This Saturday 01.06 we are going to Segovia and we are searching for people having some experience with elderly care.");
        addDescription("We organize a food festival for the homeless people and are looking for people to help share the food fairly. Mostly in the evening.");
        /**/   addDescription("In this elderly house we could always use help. No experience needed and both morning and evening during weekends and weekdays are possible.");
        addDescription("To collect refugee clothes we look for people that want to go and collect clothes at peoples houses. In the evening  and no experience.");
        addDescription("Coaches needed for our youngest teams! We are training on midday.");
        addDescription("We have a ton of fun games that you can play with out elderly. Experience in Skat would be great. Only during the weekdays.");

    }

    private static void addItem(Item item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static void addString(String string) {
        TITLES.add(string);
    }

    private static void addLocation(String string) {
        LOCATIONS.add(string);
    }

    private static void addDate(String string) {
        DATES.add(string);
    }

    private static void addDescription(String string) {
        DESCRIPTIONS.add(string);
    }

    private static void addPicture(Integer drawable) {
        PICTURES.add(drawable);
//        STRING_MAP.put(string, string);
    }

    private static Item createItem(int position) {
        return new Item(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Item {
        public final String id;
        public final String content;
        public final String details;


        public Item(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }

//    public static Image addImage() {
//
//    }
}
