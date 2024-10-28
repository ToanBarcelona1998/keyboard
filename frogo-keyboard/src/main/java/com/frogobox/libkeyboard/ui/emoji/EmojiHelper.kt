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
        SubEmojiCategory(SubEmojiCategoryType.CUTE.icon, SubEmojiCategoryType.CUTE.icon, SubEmojiCategoryType.CUTE.path,SubEmojiCategoryType.CUTE.parentId),
        SubEmojiCategory(SubEmojiCategoryType.EXCITED.icon, SubEmojiCategoryType.EXCITED.icon, SubEmojiCategoryType.EXCITED.path,SubEmojiCategoryType.EXCITED.parentId),
        SubEmojiCategory(SubEmojiCategoryType.SAD.icon, SubEmojiCategoryType.SAD.icon, SubEmojiCategoryType.SAD.path,SubEmojiCategoryType.SAD.parentId),
        SubEmojiCategory(SubEmojiCategoryType.SURPRISED.icon, SubEmojiCategoryType.SURPRISED.icon, SubEmojiCategoryType.SURPRISED.path,SubEmojiCategoryType.SURPRISED.parentId),
        SubEmojiCategory(SubEmojiCategoryType.SHOCKED.icon, SubEmojiCategoryType.SHOCKED.icon, SubEmojiCategoryType.SHOCKED.path,SubEmojiCategoryType.SHOCKED.parentId),
        SubEmojiCategory(SubEmojiCategoryType.EMBARRASSED.icon, SubEmojiCategoryType.EMBARRASSED.icon, SubEmojiCategoryType.EMBARRASSED.path,SubEmojiCategoryType.EMBARRASSED.parentId),
        SubEmojiCategory(SubEmojiCategoryType.CRYING.icon, SubEmojiCategoryType.CRYING.icon, SubEmojiCategoryType.CRYING.path,SubEmojiCategoryType.CRYING.parentId),
        SubEmojiCategory(SubEmojiCategoryType.LAUGHING.icon, SubEmojiCategoryType.LAUGHING.icon, SubEmojiCategoryType.LAUGHING.path,SubEmojiCategoryType.LAUGHING.parentId),
        SubEmojiCategory(SubEmojiCategoryType.RELAXED.icon, SubEmojiCategoryType.RELAXED.icon, SubEmojiCategoryType.RELAXED.path,SubEmojiCategoryType.RELAXED.parentId),
        SubEmojiCategory(SubEmojiCategoryType.ANGER.icon, SubEmojiCategoryType.ANGER.icon, SubEmojiCategoryType.ANGER.path,SubEmojiCategoryType.ANGER.parentId),
        SubEmojiCategory(SubEmojiCategoryType.FLOWER.icon, SubEmojiCategoryType.FLOWER.icon, SubEmojiCategoryType.FLOWER.path,SubEmojiCategoryType.FLOWER.parentId),
        SubEmojiCategory(SubEmojiCategoryType.HEART.icon, SubEmojiCategoryType.HEART.icon, SubEmojiCategoryType.HEART.path,SubEmojiCategoryType.HEART.parentId),
        SubEmojiCategory(SubEmojiCategoryType.BLUSH.icon, SubEmojiCategoryType.BLUSH.icon, SubEmojiCategoryType.BLUSH.path,SubEmojiCategoryType.BLUSH.parentId),
        SubEmojiCategory(SubEmojiCategoryType.BEAR.icon, SubEmojiCategoryType.BEAR.icon, SubEmojiCategoryType.BEAR.path,SubEmojiCategoryType.BEAR.parentId),
        SubEmojiCategory(SubEmojiCategoryType.KISS.icon, SubEmojiCategoryType.KISS.icon, SubEmojiCategoryType.KISS.path,SubEmojiCategoryType.KISS.parentId),
        SubEmojiCategory(SubEmojiCategoryType.LOVE.icon, SubEmojiCategoryType.LOVE.icon, SubEmojiCategoryType.LOVE.path,SubEmojiCategoryType.LOVE.parentId),
        SubEmojiCategory(SubEmojiCategoryType.CAT.icon, SubEmojiCategoryType.CAT.icon, SubEmojiCategoryType.CAT.path,SubEmojiCategoryType.CAT.parentId),
        SubEmojiCategory(SubEmojiCategoryType.DOG.icon, SubEmojiCategoryType.DOG.icon, SubEmojiCategoryType.DOG.path,SubEmojiCategoryType.DOG.parentId),
        SubEmojiCategory(SubEmojiCategoryType.FACE.icon, SubEmojiCategoryType.FACE.icon, SubEmojiCategoryType.FACE.path,SubEmojiCategoryType.FACE.parentId),
        SubEmojiCategory(SubEmojiCategoryType.SPARKLES.icon, SubEmojiCategoryType.SPARKLES.icon, SubEmojiCategoryType.SPARKLES.path,SubEmojiCategoryType.SPARKLES.parentId),
        SubEmojiCategory(SubEmojiCategoryType.GIRL.icon, SubEmojiCategoryType.GIRL.icon, SubEmojiCategoryType.GIRL.path,SubEmojiCategoryType.GIRL.parentId),
        SubEmojiCategory(SubEmojiCategoryType.RUNNING.icon, SubEmojiCategoryType.RUNNING.icon, SubEmojiCategoryType.RUNNING.path,SubEmojiCategoryType.RUNNING.parentId),
        SubEmojiCategory(SubEmojiCategoryType.HUG.icon, SubEmojiCategoryType.HUG.icon, SubEmojiCategoryType.HUG.path,SubEmojiCategoryType.HUG.parentId),
        SubEmojiCategory(SubEmojiCategoryType.WAND.icon, SubEmojiCategoryType.WAND.icon, SubEmojiCategoryType.WAND.path,SubEmojiCategoryType.WAND.parentId),
        SubEmojiCategory(SubEmojiCategoryType.BIG.icon, SubEmojiCategoryType.BIG.icon, SubEmojiCategoryType.BIG.path,SubEmojiCategoryType.BIG.parentId),
        SubEmojiCategory(SubEmojiCategoryType.MUSIC.icon, SubEmojiCategoryType.MUSIC.icon, SubEmojiCategoryType.MUSIC.path,SubEmojiCategoryType.MUSIC.parentId),
        SubEmojiCategory(SubEmojiCategoryType.STAR.icon, SubEmojiCategoryType.STAR.icon, SubEmojiCategoryType.STAR.path,SubEmojiCategoryType.STAR.parentId),
        SubEmojiCategory(SubEmojiCategoryType.COOL.icon, SubEmojiCategoryType.COOL.icon, SubEmojiCategoryType.COOL.path,SubEmojiCategoryType.COOL.parentId),
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