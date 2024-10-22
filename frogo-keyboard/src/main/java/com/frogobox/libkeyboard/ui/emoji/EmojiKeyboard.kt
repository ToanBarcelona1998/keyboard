package com.frogobox.libkeyboard.ui.emoji

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.emoji2.text.EmojiCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.frogobox.libkeyboard.R
import com.frogobox.libkeyboard.common.core.BaseKeyboard
import com.frogobox.libkeyboard.databinding.ItemFunnyTextBinding
import com.frogobox.libkeyboard.databinding.ItemFunnyTextCategoryBinding
import com.frogobox.libkeyboard.databinding.ItemKeyboardEmojiBinding
import com.frogobox.libkeyboard.databinding.KeyboardEmojiBinding
import com.frogobox.libkeyboard.ui.main.OnKeyboardActionListener
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.recycler.ext.injectorBinding

/**
 * Created by Faisal Amir on 11/12/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */

class EmojiKeyboard(
    context: Context,
    attrs: AttributeSet?,
) : BaseKeyboard<KeyboardEmojiBinding>(context, attrs) {

    private var emojiCompatMetadataVersion = 0

    var mOnKeyboardActionListener: OnKeyboardActionListener? = null

    override fun setupViewBinding(): KeyboardEmojiBinding {
        return KeyboardEmojiBinding.inflate(LayoutInflater.from(context), this, true)
    }

    override fun onCreate() {
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun openEmojiPalette() {
        setupEmojiCategory()
        setupSubEmojiCategory(EmojiCategoryType.Emoji.id)
        setupEmojis(SubEmojiCategoryType.GENERAL.path)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupEmojiCategory() {
        val emojiAdapterCallback =
            object : IFrogoBindingAdapter<EmojiCategory, ItemKeyboardEmojiBinding> {

                override fun onItemClicked(
                    binding: ItemKeyboardEmojiBinding,
                    data: EmojiCategory,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<EmojiCategory>
                ) {

                    when(data.id){
                        0 -> {
                            setupSubEmojiCategory(0)
                            val firstEmoji = getSubEmojiCategory(0)[0]

                            setupEmojis(firstEmoji.assetPath)
                        }
                        1 -> {
                            setupSubFunnyTextCategory(1)
                            val firstEmoji = getSubEmojiCategory(1)[0]

                            setupFunnyText(firstEmoji.assetPath)
                        }
                    }
                }

                override fun areContentsTheSame(
                    oldItem: EmojiCategory,
                    newItem: EmojiCategory
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areItemsTheSame(
                    oldItem: EmojiCategory,
                    newItem: EmojiCategory
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun setViewBinding(parent: ViewGroup): ItemKeyboardEmojiBinding {
                    return ItemKeyboardEmojiBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                }

                override fun setupInitComponent(
                    binding: ItemKeyboardEmojiBinding,
                    data: EmojiCategory,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<EmojiCategory>
                ) {
                    val processed = EmojiCompat.get().process(data.icon)
                    binding.tvEmoji.text = processed
                }
            }

        binding?.apply {
            emojiCategoryList.injectorBinding<EmojiCategory, ItemKeyboardEmojiBinding>()
                .addData(getEmojiCategory())
                .addCallback(emojiAdapterCallback)
                .createLayoutLinearHorizontal(false)
                .build()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupSubEmojiCategory(categoryId: Int) {
        val subEmojiAdapter =
            object : IFrogoBindingAdapter<SubEmojiCategory, ItemKeyboardEmojiBinding> {
                override fun areContentsTheSame(
                    oldItem: SubEmojiCategory,
                    newItem: SubEmojiCategory
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areItemsTheSame(
                    oldItem: SubEmojiCategory,
                    newItem: SubEmojiCategory
                ): Boolean {
                    return oldItem == newItem
                }

                override fun onItemClicked(
                    binding: ItemKeyboardEmojiBinding,
                    data: SubEmojiCategory,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<SubEmojiCategory>
                ) {
                    setupEmojis(data.assetPath)
                }

                override fun setViewBinding(parent: ViewGroup): ItemKeyboardEmojiBinding {
                    return ItemKeyboardEmojiBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                }

                override fun setupInitComponent(
                    binding: ItemKeyboardEmojiBinding,
                    data: SubEmojiCategory,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<SubEmojiCategory>
                ) {
                    val processed = EmojiCompat.get().process(data.icon)
                    binding.tvEmoji.text = processed
                }

            }
        binding?.apply {
            subEmojiCategoryList.injectorBinding<SubEmojiCategory, ItemKeyboardEmojiBinding>()
                .addData(getSubEmojiCategory(categoryId))
                .addCallback(subEmojiAdapter)
                .createLayoutLinearHorizontal(false)
                .build()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupSubFunnyTextCategory(categoryId: Int) {
        val subEmojiAdapter =
            object : IFrogoBindingAdapter<SubEmojiCategory, ItemFunnyTextCategoryBinding> {
                override fun areContentsTheSame(
                    oldItem: SubEmojiCategory,
                    newItem: SubEmojiCategory
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areItemsTheSame(
                    oldItem: SubEmojiCategory,
                    newItem: SubEmojiCategory
                ): Boolean {
                    return oldItem == newItem
                }

                override fun onItemClicked(
                    binding: ItemFunnyTextCategoryBinding,
                    data: SubEmojiCategory,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<SubEmojiCategory>
                ) {
                    setupFunnyText(data.assetPath)
                }

                override fun setViewBinding(parent: ViewGroup): ItemFunnyTextCategoryBinding {
                    return ItemFunnyTextCategoryBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                }

                override fun setupInitComponent(
                    binding: ItemFunnyTextCategoryBinding,
                    data: SubEmojiCategory,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<SubEmojiCategory>
                ) {
                    binding.tvFunnyTextCategory.text = data.name
                }

            }
        binding?.apply {
            subEmojiCategoryList.injectorBinding<SubEmojiCategory, ItemFunnyTextCategoryBinding>()
                .addData(getSubEmojiCategory(categoryId))
                .addCallback(subEmojiAdapter)
                .createLayoutLinearHorizontal(false)
                .build()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupEmojis(path: String) {
        ensureBackgroundThread {
            val fullEmojiList = parseRawEmojiSpecsFile(context, path)

            val systemFontPaint = Paint().apply {
                typeface = Typeface.DEFAULT
            }

            val emojis = fullEmojiList.filter { emoji ->
                systemFontPaint.hasGlyph(emoji) || (EmojiCompat.get().loadState == EmojiCompat.LOAD_STATE_SUCCEEDED
                        && EmojiCompat.get().getEmojiMatch(
                    emoji,
                    emojiCompatMetadataVersion
                ) == EmojiCompat.EMOJI_SUPPORTED)
            }

            Handler(Looper.getMainLooper()).post {
                setupEmojiAdapter(emojis)
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupFunnyText(path: String) {
        ensureBackgroundThread {
            val fullEmojiList = parseRawEmojiSpecsFile(context, path)

            Handler(Looper.getMainLooper()).post {
                setupFunnyTextAdapter(fullEmojiList)
            }

        }
    }

    private fun setupEmojiAdapter(emojis: List<String>) {

        val adapterCallback = object : IFrogoBindingAdapter<String, ItemKeyboardEmojiBinding> {
            override fun onItemClicked(
                binding: ItemKeyboardEmojiBinding,
                data: String,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<String>
            ) {
                mOnKeyboardActionListener!!.onText(data)
            }

            override fun onItemLongClicked(
                binding: ItemKeyboardEmojiBinding,
                data: String,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<String>
            ) {
                mOnKeyboardActionListener!!.onText(data)
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun setViewBinding(parent: ViewGroup): ItemKeyboardEmojiBinding {
                return ItemKeyboardEmojiBinding.inflate(LayoutInflater.from(context), parent, false)
            }

            override fun setupInitComponent(
                binding: ItemKeyboardEmojiBinding,
                data: String,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<String>
            ) {
                val processed = EmojiCompat.get().process(data)
                binding.tvEmoji.text = processed
            }
        }
        val emojiItemWidth = context.resources.getDimensionPixelSize(R.dimen.emoji_item_size)

        val mLayoutManager = AutoGridLayoutManager(context, emojiItemWidth)

        binding?.apply {
            emojiList.injectorBinding<String, ItemKeyboardEmojiBinding>()
                .addCallback(adapterCallback)
                .addData(emojis)
                .build()

            emojiList.layoutManager = mLayoutManager
        }
    }

    private fun setupFunnyTextAdapter(funnyTexts: List<String>){
        val adapterCallback = object : IFrogoBindingAdapter<String, ItemFunnyTextBinding> {
            override fun onItemClicked(
                binding: ItemFunnyTextBinding,
                data: String,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<String>
            ) {
                mOnKeyboardActionListener!!.onText(data)
            }

            override fun onItemLongClicked(
                binding: ItemFunnyTextBinding,
                data: String,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<String>
            ) {
                mOnKeyboardActionListener!!.onText(data)
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun setViewBinding(parent: ViewGroup): ItemFunnyTextBinding {
                return ItemFunnyTextBinding.inflate(LayoutInflater.from(context), parent, false)
            }

            override fun setupInitComponent(
                binding: ItemFunnyTextBinding,
                data: String,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<String>
            ) {
                binding.tvFunnyText.text = data
            }
        }


        binding?.apply {
            emojiList.injectorBinding<String, ItemFunnyTextBinding>()
                .addCallback(adapterCallback)
                .addData(funnyTexts)
                .build()

            emojiList.layoutManager = FunnyTextGridLayoutManager(context)
        }
    }

    private fun ensureBackgroundThread(callback: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Thread {
                callback()
            }.start()
        } else {
            callback()
        }
    }

}