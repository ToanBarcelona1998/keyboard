package com.frogobox.libkeyboard.ui.emoji

import android.content.Context

/**
 * Reads the emoji list at the given [path] and returns an parsed [MutableList]. If the
 * given file path does not exist, an empty [MutableList] is returned.
 *
 * @param context The initiating view's context.
 * @param path The path to the asset file.
 */
fun parseRawEmojiSpecsFile(context: Context, path: String): MutableList<String> {

    val emojis = mutableListOf<String>()
    var emojiEditorList: MutableList<String>? = null

    fun commitEmojiEditorList() {
        emojiEditorList?.let {
            // add only the base emoji for now, ignore the variations
            emojis.add(it.first())
        }
        emojiEditorList = null
    }

    context.assets.open(path).bufferedReader().useLines { lines ->
        for (line in lines) {
            if (line.startsWith("#")) {
                // Comment line
            } else if (line.startsWith("[")) {
                commitEmojiEditorList()
            } else if (line.trim().isEmpty()) {
                // Empty line
                continue
            } else {
                if (!line.startsWith("\t")) {
                    commitEmojiEditorList()
                }

                // Assume it is a data line
                val data = line.split(";")
                if (data.size == 3) {
                    val emoji = data[0].trim()
                    if (emojiEditorList != null) {
                        emojiEditorList!!.add(emoji)
                    } else {
                        emojiEditorList = mutableListOf(emoji)
                    }
                }
            }
        }
        commitEmojiEditorList()
    }
    
    return emojis
}

fun getSubEmojiCategory(id: Int) : List<SubEmojiCategory>  {
    val category =  listOf(
        SubEmojiCategory(SubEmojiCategoryType.GENERAL.icon, SubEmojiCategoryType.GENERAL.icon, SubEmojiCategoryType.GENERAL.path, SubEmojiCategoryType.GENERAL.parentId),
        SubEmojiCategory(SubEmojiCategoryType.SMILEYS_EMOTION.icon, SubEmojiCategoryType.SMILEYS_EMOTION.icon, SubEmojiCategoryType.SMILEYS_EMOTION.path, SubEmojiCategoryType.SMILEYS_EMOTION.parentId),
        SubEmojiCategory(SubEmojiCategoryType.PEOPLE_BODY.icon, SubEmojiCategoryType.PEOPLE_BODY.icon, SubEmojiCategoryType.PEOPLE_BODY.path,SubEmojiCategoryType.PEOPLE_BODY.parentId),
        SubEmojiCategory(SubEmojiCategoryType.ACTIVITIES.icon, SubEmojiCategoryType.ACTIVITIES.icon, SubEmojiCategoryType.ACTIVITIES.path,SubEmojiCategoryType.ACTIVITIES.parentId),
        SubEmojiCategory(SubEmojiCategoryType.ANIMAL_NATURE.icon, SubEmojiCategoryType.ANIMAL_NATURE.icon, SubEmojiCategoryType.ANIMAL_NATURE.path,SubEmojiCategoryType.ANIMAL_NATURE.parentId),
        SubEmojiCategory(SubEmojiCategoryType.FOOD_DRINK.icon, SubEmojiCategoryType.FOOD_DRINK.icon, SubEmojiCategoryType.FOOD_DRINK.path,SubEmojiCategoryType.FOOD_DRINK.parentId),
        SubEmojiCategory(SubEmojiCategoryType.FLAG.icon, SubEmojiCategoryType.FLAG.icon, SubEmojiCategoryType.FLAG.path,SubEmojiCategoryType.FLAG.parentId),
        SubEmojiCategory(SubEmojiCategoryType.OBJECTS.icon, SubEmojiCategoryType.OBJECTS.icon, SubEmojiCategoryType.OBJECTS.path,SubEmojiCategoryType.OBJECTS.parentId),
        SubEmojiCategory(SubEmojiCategoryType.TRAVEL_PLACES.icon, SubEmojiCategoryType.TRAVEL_PLACES.icon, SubEmojiCategoryType.TRAVEL_PLACES.path,SubEmojiCategoryType.TRAVEL_PLACES.parentId),
        SubEmojiCategory(SubEmojiCategoryType.SYMBOLS.icon, SubEmojiCategoryType.SYMBOLS.icon, SubEmojiCategoryType.SYMBOLS.path,SubEmojiCategoryType.SYMBOLS.parentId),
        SubEmojiCategory(SubEmojiCategoryType.HAPPY.icon, SubEmojiCategoryType.HAPPY.icon, SubEmojiCategoryType.HAPPY.path,SubEmojiCategoryType.HAPPY.parentId),
        SubEmojiCategory(SubEmojiCategoryType.ANGRY.icon, SubEmojiCategoryType.ANGRY.icon, SubEmojiCategoryType.ANGRY.path,SubEmojiCategoryType.ANGRY.parentId),
    )

    return category.filter {
        it.parentId == id
    }
}

fun getEmojiCategory() : List<EmojiCategory>{
    return listOf(
        EmojiCategory(EmojiCategoryType.Emoji.id,EmojiCategoryType.Emoji.name,EmojiCategoryType.Emoji.icon),
        EmojiCategory(EmojiCategoryType.FunnyText.id,EmojiCategoryType.FunnyText.name,EmojiCategoryType.FunnyText.icon),
    )
}