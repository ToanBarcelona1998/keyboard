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
    CUTE("Cute" , path = "funny_text/cute.txt",1),
    EXCITED("Exited" , path = "funny_text/excited.txt",1),
    SAD("Sad" , path = "funny_text/sad.txt",1),
    SURPRISE("Surprise" , path = "funny_text/surprise.txt",1),
    SHOCKED("Shocked" , path = "funny_text/shocked.txt",1),
    EMBARRASSED("Embarrassed" , path = "funny_text/embarrassed.txt",1),
    CRYING("Crying" , path = "funny_text/crying.txt",1),
    LAUGHING("Laughing" , path = "funny_text/laughing.txt",1),
    RELAXED("Relaxed" , path = "funny_text/relaxed.txt",1),
    ANGER("Anger" , path = "funny_text/anger.txt",1),
    FLOWER("Flower" , path = "funny_text/flower.txt",1),
    HEART("Heart" , path = "funny_text/heart.txt",1),
    BLUSH("Blush" , path = "funny_text/blush.txt",1),
    BEAR("Bear" , path = "funny_text/bear.txt",1),
    KISS("Kiss" , path = "funny_text/kiss.txt",1),
    LOVE("Love" , path = "funny_text/love.txt",1),
    CAT("Cat" , path = "funny_text/cat.txt",1),
    DOG("Dog" , path = "funny_text/dog.txt",1),
    FACE("Face" , path = "funny_text/face.txt",1),
    SPARKLES("Sparkles" , path = "funny_text/sparkles.txt",1),
    GIRL("Girl" , path = "funny_text/girl.txt",1),
    RUNNING("Running" , path = "funny_text/running.txt",1),
    HUG("Hug" , path = "funny_text/hug.txt",1),
    WAND("Wand" , path = "funny_text/wand.txt",1),
    BIG("Big" , path = "funny_text/big.txt",1),
    MUSIC("Music" , path = "funny_text/music.txt",1),
    STAR("Star" , path = "funny_text/star.txt",1),
    COOL("Cool" , path = "funny_text/cool.txt",1),
}