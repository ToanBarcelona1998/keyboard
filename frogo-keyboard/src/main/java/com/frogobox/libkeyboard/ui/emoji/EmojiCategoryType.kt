package com.frogobox.libkeyboard.ui.emoji

/**
 * Created by Faisal Amir on 24/10/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */

enum class EmojiCategoryType (val id : Int , val icon: String) {
    Emoji(id = 0 , icon = "\uD83D\uDE42"),
    FunnyText(id = 1 , icon = ":-)"),
    Sticker(id = 2 , icon = "\uD83D\uDE42"),
    Other(id = 3 , icon = "Gif"),
}


enum class SubEmojiCategoryType(val icon: String, val path: String,val parentId : Int) {
    GENERAL("🙂", "media/_emoji_general.txt", 0),
    ACTIVITIES("\uD83C\uDF83", "media/emoji_activities.txt",0),
    ANIMAL_NATURE("\uD83D\uDC35", "media/emoji_animal_nature.txt",0),
    FLAG("\uD83C\uDFC1", "media/emoji_flag.txt",0),
    FOOD_DRINK("\uD83C\uDF47", "media/emoji_food_drink.txt",0),
    OBJECTS("\uD83D\uDC53", "media/emoji_objects.txt",0),
    PEOPLE_BODY("\uD83D\uDC4B", "media/emoji_people_body.txt",0),
    SMILEYS_EMOTION("\uD83D\uDE00", "media/emoji_smileys_emotion.txt",0),
    SYMBOLS("✅", "media/emoji_symbols.txt",0),
    TRAVEL_PLACES("\uD83C\uDF0D", "media/emoji_travel_places.txt",0),
    //
    HAPPY("Happy" , path = "funny_text/happy.txt",1),
    ANGRY("Angry" , path = "funny_text/angry.txt",1)
}