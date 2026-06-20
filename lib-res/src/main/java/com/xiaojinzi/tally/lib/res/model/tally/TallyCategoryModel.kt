package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork
import com.xiaojinzi.support.ktx.newUUid
import com.xiaojinzi.tally.lib.res.model.user.UserInfoDto

@Keep
@ModelForNetwork
data class TallyRemoteCategoryRes(
    val id: String,
    val userId: String,
    val bookId: String,
    val parentId: String?,
    val type: String?,
    val name: String?,
    val iconName: String?,
    val sort: Long,
    val timeCreate: Long,
    val timeModify: Long,
    val isDeleted: Boolean,
) {

    companion object {

        const val CATEGORY_TYPE_INCOME = TallyCategoryDto.TYPE_INCOME
        const val CATEGORY_TYPE_SPENDING = TallyCategoryDto.TYPE_SPENDING

        fun createForOpenSource(): List<TallyRemoteCategoryRes> {

            val CURRENT_TIME = System.currentTimeMillis()

            // 一级消费类目 id (供二级引用)
            val cFood = newUUid()
            val cTransport = newUUid()
            val cShopping = newUUid()
            val cHousing = newUUid()
            val cLeisure = newUUid()
            val cMedical = newUUid()
            val cEducation = newUUid()
            val cSocial = newUUid()
            val cFamily = newUUid()
            val cOther = newUUid()

            // 构造一条分类的便捷方法
            fun cat(
                parentId: String?,
                icon: String,
                title: String,
                type: String = CATEGORY_TYPE_SPENDING,
                id: String = newUUid(),
            ) = TallyRemoteCategoryRes(
                id = id,
                userId = UserInfoDto.TEST_ID,
                bookId = TallyBookDto.TEST_ID,
                type = type,
                parentId = parentId,
                iconName = icon,
                name = title,
                sort = 1,
                timeCreate = CURRENT_TIME,
                timeModify = CURRENT_TIME,
                isDeleted = false,
            )

            return listOf(
                // 餐饮美食
                cat(null, "food1", "餐饮美食", id = cFood),
                cat(cFood, "rice1", "三餐"),
                cat(cFood, "transporter1", "外卖"),
                cat(cFood, "cocktail1", "饮料酒水"),
                cat(cFood, "candy1", "零食甜点"),
                cat(cFood, "coffee1", "咖啡奶茶"),
                cat(cFood, "vegetableBasket1", "食材生鲜"),
                cat(cFood, "chopsticksFork1", "聚餐请客"),
                // 交通出行
                cat(null, "road1", "交通出行", id = cTransport),
                cat(cTransport, "taxi1", "打车"),
                cat(cTransport, "subway1", "公共交通"),
                cat(cTransport, "gasStation1", "加油"),
                cat(cTransport, "parking1", "停车"),
                cat(cTransport, "repair1", "维修保养"),
                cat(cTransport, "airplane1", "火车机票"),
                // 购物
                cat(null, "shopping1", "购物", id = cShopping),
                cat(cShopping, "tShirt1", "服饰鞋包"),
                cat(cShopping, "lipstick1", "个护美妆"),
                cat(cShopping, "airpods1", "数码电器"),
                cat(cShopping, "watch1", "配饰腕表"),
                cat(cShopping, "nightstand1", "居家百货"),
                cat(cShopping, "printer1", "办公用品"),
                // 居住
                cat(null, "house1", "居住", id = cHousing),
                cat(cHousing, "home1", "房租房贷"),
                cat(cHousing, "waterElectricityCharge1", "水电燃气"),
                cat(cHousing, "key1", "物业"),
                cat(cHousing, "wifi1", "宽带通讯"),
                cat(cHousing, "washingMachine1", "家装家具"),
                // 休闲娱乐
                cat(null, "gamePad1", "休闲娱乐", id = cLeisure),
                cat(cLeisure, "movie1", "电影演出"),
                cat(cLeisure, "journey1", "旅行"),
                cat(cLeisure, "fitness1", "运动健身"),
                cat(cLeisure, "gameConsole1", "游戏"),
                cat(cLeisure, "vip1", "订阅会员"),
                cat(cLeisure, "chess1", "棋牌桌游"),
                // 医疗健康
                cat(null, "hospital1", "医疗健康", id = cMedical),
                cat(cMedical, "stethoscope1", "看病"),
                cat(cMedical, "pill1", "买药"),
                cat(cMedical, "cardioElectric1", "体检"),
                cat(cMedical, "firstAidKit1", "保健"),
                // 学习教育
                cat(null, "book2", "学习教育", id = cEducation),
                cat(cEducation, "book1", "书籍"),
                cat(cEducation, "school1", "课程培训"),
                cat(cEducation, "folder1", "文具"),
                // 人情社交
                cat(null, "gift1", "人情社交", id = cSocial),
                cat(cSocial, "redPacket1", "红包"),
                cat(cSocial, "gift2", "礼物"),
                cat(cSocial, "userMoney1", "孝敬长辈"),
                // 家庭亲子
                cat(null, "babyBottle1", "家庭亲子", id = cFamily),
                cat(cFamily, "babyBottle1", "母婴用品"),
                cat(cFamily, "toy1", "玩具"),
                cat(cFamily, "dog1", "宠物"),
                // 其他
                cat(null, "more2", "其他", id = cOther),
                // 收入
                cat(null, "wage1", "工资", type = CATEGORY_TYPE_INCOME),
                cat(null, "bonus1", "奖金", type = CATEGORY_TYPE_INCOME),
                cat(null, "partTimeJob1", "兼职外快", type = CATEGORY_TYPE_INCOME),
                cat(null, "income1", "借入", type = CATEGORY_TYPE_INCOME),
            )

        }
    }

}

@Keep
@ModelForNetwork
data class TallyRemoteCategoryReq(
    val id: String,
    val userId: String,
    val bookId: String,
    val parentId: String?,
    val type: String?,
    val name: String?,
    val iconName: String?,
    val sort: Long,
    val timeCreate: Long,
    val isDeleted: Boolean,
)

@Keep
data class TallyCategoryInsertDto(
    val id: String? = null,
    val userId: String,
    val bookId: String,
    val parentId: String? = null,
    val type: String?,
    val name: String?,
    val iconName: String?,
    val sort: Long = System.currentTimeMillis(),
    val timeCreate: Long = System.currentTimeMillis(),
    val timeModify: Long? = null,
    val isDeleted: Boolean = false,
    val isSync: Boolean = false,
)

@Keep
data class TallyCategoryDto(
    val id: String,
    val userId: String,
    val bookId: String,
    val parentId: String? = null,
    val type: TallyCategoryType,
    val name: String?,
    val iconName: String?,
    val sort: Long,
    val timeCreate: Long,
    val timeModify: Long?,
    val isDeleted: Boolean,
    val isSync: Boolean,
) {

    companion object {

        const val TYPE_INCOME = "income"
        const val TYPE_SPENDING = "spending"

        enum class TallyCategoryType(
            val moneyTransform: Int,
            val dbStr: String,
        ) {
            UNKNOW(
                moneyTransform = -1,
                dbStr = "",
            ),
            INCOME(
                moneyTransform = 1,
                dbStr = TYPE_INCOME,
            ),
            SPENDING(
                moneyTransform = -1,
                dbStr = TYPE_SPENDING,
            );

            companion object {
                fun fromDbStr(dbStr: String?): TallyCategoryType {
                    return when (dbStr) {
                        TYPE_INCOME -> INCOME
                        TYPE_SPENDING -> SPENDING
                        else -> UNKNOW
                    }
                }
            }
        }

    }

    val getAdapter: TallyCategoryDto?
        get() = if (isDeleted) {
            null
        } else {
            this
        }

}

fun TallyRemoteCategoryRes.toInsertDto(): TallyCategoryInsertDto = TallyCategoryInsertDto(
    id = this.id,
    userId = this.userId,
    bookId = this.bookId,
    name = this.name,
    type = this.type,
    parentId = this.parentId,
    iconName = this.iconName,
    sort = this.sort,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = true,
)

fun TallyCategoryDto.toInsertDto() = TallyCategoryInsertDto(
    id = id,
    userId = userId,
    bookId = bookId,
    parentId = parentId,
    type = type.dbStr,
    name = name,
    iconName = iconName,
    sort = sort,
    timeCreate = timeCreate,
    timeModify = timeModify,
    isDeleted = isDeleted,
    isSync = isSync,
)

fun TallyRemoteCategoryRes.toDto() = TallyCategoryDto(
    id = id,
    userId = userId,
    bookId = bookId,
    parentId = parentId,
    type = TallyCategoryDto.Companion.TallyCategoryType.fromDbStr(type),
    name = name,
    iconName = iconName,
    sort = sort,
    timeCreate = timeCreate,
    timeModify = timeModify,
    isDeleted = isDeleted,
    isSync = true,
)