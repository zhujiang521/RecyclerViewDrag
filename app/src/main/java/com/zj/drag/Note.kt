package com.zj.drag

/**
 * 版权：Zhujiang 个人版权
 *
 * @author zhujiang
 * 创建日期：4/12/21
 * 描述：RecyclerViewDrag
 *
 */
data class Note(val title: String, val content: String, val img: Int? = null)

fun getNotes(): ArrayList<Note> {
    val notes = arrayListOf<Note>()
    for (index in 0..20) {
        val imgList = when {
            index % 2 == 0 -> {
                R.drawable.one
            }
            index % 3 == 0 -> {
                R.drawable.two
            }
            else -> {
                null
            }
        }
        notes.add(
            Note(
                "第${index}个", "天青色等烟雨，而我在等你${"哈哈哈".repeat(index)}",
                imgList
            )
        )
    }

    return notes
}