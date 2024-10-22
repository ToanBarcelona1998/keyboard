package com.frogobox.libkeyboard.ui.emoji

import androidx.annotation.Keep

/**
 * Created by Faisal Amir on 24/10/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */

@Keep
data class EmojiCategory(
    val id: Int,
    val name: String,
    val icon: String,
)

data class SubEmojiCategory(
    val name : String,
    val icon : String,
    val assetPath: String,
    val parentId : Int,
)
