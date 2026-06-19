package com.xiaojinzi.tally.module.base.theme

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * 主题插画风格. 不同主题分配不同构图, 实现"千题千面";
 * 所有绘制均使用当前主题的 [androidx.compose.material3.ColorScheme], 因此自动随主题配色与深浅色变化.
 */
enum class ThemeIllustrationStyle {
    /** 远山薄雾 (清雅/冷调主题) */
    MOUNTAIN,

    /** 暖阳层浪 (暖调主题) */
    WAVE,

    /** 几何天际线 (建筑/中性主题) */
    GEOMETRIC,
}

/** 暖阳固定用暖橙色(太阳本就是暖色), 不随主题强调色变成冷色. */
private val SunColor = Color(0xFFF2A23C)

// region 绘制工具

private fun DrawScope.polyPath(pts: List<Pair<Int, Int>>, sx: Float, sy: Float): Path {
    val p = Path()
    p.moveTo(pts[0].first * sx, pts[0].second * sy)
    for (i in 1 until pts.size) {
        p.lineTo(pts[i].first * sx, pts[i].second * sy)
    }
    p.close()
    return p
}

private fun DrawScope.waveBand(color: Color, baseY: Float, amp: Float, sy: Float) {
    val w = size.width
    val h = size.height
    val by = baseY * sy
    val a = amp * sy
    val seg = w / 4f
    val p = Path()
    p.moveTo(0f, by)
    p.quadraticBezierTo(seg * 0.5f, by - a, seg, by)
    p.quadraticBezierTo(seg * 1.5f, by + a, seg * 2f, by)
    p.quadraticBezierTo(seg * 2.5f, by - a, seg * 3f, by)
    p.quadraticBezierTo(seg * 3.5f, by + a, w, by)
    p.lineTo(w, h)
    p.lineTo(0f, h)
    p.close()
    drawPath(p, color)
}

// endregion

/**
 * 首页 / 账单页头部装饰插画 (横幅). 按 [style] 切换构图, 颜色取自当前主题.
 */
@Composable
fun ThemeHomeBanner(
    style: ThemeIllustrationStyle,
    modifier: Modifier = Modifier,
) {
    val cs = MaterialTheme.colorScheme
    Canvas(modifier = modifier) {
        val sx = size.width / 400f
        val sy = size.height / 150f
        fun px(v: Float) = v * sx
        fun py(v: Float) = v * sy
        when (style) {
            ThemeIllustrationStyle.MOUNTAIN -> {
                drawCircle(color = SunColor, radius = py(20f), center = Offset(px(320f), py(48f)))
                drawPath(
                    polyPath(listOf(0 to 95, 70 to 68, 150 to 92, 230 to 62, 310 to 90, 400 to 70, 400 to 150, 0 to 150), sx, sy),
                    cs.primaryContainer,
                )
                drawPath(
                    polyPath(listOf(0 to 118, 90 to 92, 170 to 116, 250 to 90, 330 to 114, 400 to 96, 400 to 150, 0 to 150), sx, sy),
                    cs.secondary,
                )
                drawPath(
                    polyPath(listOf(0 to 142, 80 to 122, 170 to 140, 260 to 120, 340 to 138, 400 to 124, 400 to 150, 0 to 150), sx, sy),
                    cs.primary,
                )
            }

            ThemeIllustrationStyle.WAVE -> {
                drawCircle(color = SunColor, radius = py(22f), center = Offset(px(308f), py(46f)))
                waveBand(cs.primaryContainer, baseY = 84f, amp = 16f, sy = sy)
                waveBand(cs.secondary, baseY = 106f, amp = 13f, sy = sy)
                waveBand(cs.primary, baseY = 126f, amp = 11f, sy = sy)
            }

            ThemeIllustrationStyle.GEOMETRIC -> {
                drawCircle(color = SunColor, radius = py(18f), center = Offset(px(344f), py(40f)))
                // (x, topY, color); 楼宽 44, 底到 150
                val buildings = listOf(
                    Triple(20, 92, cs.primaryContainer),
                    Triple(74, 62, cs.secondary),
                    Triple(132, 100, cs.primary),
                    Triple(190, 74, cs.primaryContainer),
                    Triple(248, 54, cs.secondary),
                    Triple(306, 86, cs.primary),
                    Triple(360, 70, cs.secondary),
                )
                buildings.forEach { (x, top, c) ->
                    drawRect(
                        color = c,
                        topLeft = Offset(px(x.toFloat()), py(top.toFloat())),
                        size = Size(px(44f), size.height - py(top.toFloat())),
                    )
                }
            }
        }
    }
}

/**
 * 统计页装饰插画: 柱状图 + 趋势线 + 暖阳. 颜色取自当前主题.
 */
@Composable
fun ThemeStatBanner(
    modifier: Modifier = Modifier,
) {
    val cs = MaterialTheme.colorScheme
    Canvas(modifier = modifier) {
        val sx = size.width / 400f
        val sy = size.height / 150f
        fun px(v: Float) = v * sx
        fun py(v: Float) = v * sy
        drawCircle(color = SunColor, radius = py(16f), center = Offset(px(340f), py(40f)))
        val bars = listOf(
            Triple(44, 92, cs.secondary),
            Triple(104, 70, cs.primary),
            Triple(164, 84, cs.secondary),
            Triple(224, 56, cs.primary),
            Triple(284, 74, cs.secondary),
        )
        bars.forEach { (x, top, c) ->
            drawRoundRect(
                color = c,
                topLeft = Offset(px(x.toFloat()), py(top.toFloat())),
                size = Size(px(40f), py(132f) - py(top.toFloat())),
                cornerRadius = CornerRadius(px(6f), px(6f)),
            )
        }
        drawLine(
            color = cs.primary.copy(alpha = 0.4f),
            start = Offset(px(36f), py(132f)),
            end = Offset(px(364f), py(132f)),
            strokeWidth = py(1.5f),
        )
        val pts = listOf(64 to 86, 124 to 64, 184 to 78, 244 to 50, 304 to 68)
        for (i in 0 until pts.size - 1) {
            drawLine(
                color = cs.tertiary,
                start = Offset(px(pts[i].first.toFloat()), py(pts[i].second.toFloat())),
                end = Offset(px(pts[i + 1].first.toFloat()), py(pts[i + 1].second.toFloat())),
                strokeWidth = py(2.5f),
                cap = StrokeCap.Round,
            )
        }
        pts.forEach {
            drawCircle(color = cs.primary, radius = py(3f), center = Offset(px(it.first.toFloat()), py(it.second.toFloat())))
        }
    }
}

/**
 * 空状态装饰插画: 茶盏 + 热气. 颜色取自当前主题.
 */
@Composable
fun ThemeEmptyIllustration(
    modifier: Modifier = Modifier,
) {
    val cs = MaterialTheme.colorScheme
    Canvas(modifier = modifier) {
        val sx = size.width / 200f
        val sy = size.height / 200f
        fun px(v: Float) = v * sx
        fun py(v: Float) = v * sy
        val outline = px(2.5f)
        // 茶托
        drawOval(color = cs.secondary, topLeft = Offset(px(54f), py(143f)), size = Size(px(92f), py(18f)))
        // 杯把
        drawArc(
            color = cs.primary,
            startAngle = -70f,
            sweepAngle = 150f,
            useCenter = false,
            topLeft = Offset(px(120f), py(98f)),
            size = Size(px(34f), py(36f)),
            style = Stroke(width = outline),
        )
        // 杯身
        drawPath(polyPath(listOf(72 to 90, 128 to 90, 122 to 140, 78 to 140), sx, sy), cs.primaryContainer)
        drawLine(cs.primary, Offset(px(72f), py(90f)), Offset(px(78f), py(140f)), strokeWidth = outline, cap = StrokeCap.Round)
        drawLine(cs.primary, Offset(px(128f), py(90f)), Offset(px(122f), py(140f)), strokeWidth = outline, cap = StrokeCap.Round)
        drawLine(cs.primary, Offset(px(78f), py(140f)), Offset(px(122f), py(140f)), strokeWidth = outline, cap = StrokeCap.Round)
        // 杯口
        drawOval(color = cs.primaryContainer, topLeft = Offset(px(70f), py(84f)), size = Size(px(60f), py(12f)))
        drawOval(color = cs.primary, topLeft = Offset(px(70f), py(84f)), size = Size(px(60f), py(12f)), style = Stroke(width = outline))
        // 热气
        val steam1 = Path().apply {
            moveTo(px(90f), py(82f))
            quadraticBezierTo(px(82f), py(74f), px(90f), py(66f))
            quadraticBezierTo(px(98f), py(58f), px(90f), py(50f))
        }
        val steam2 = Path().apply {
            moveTo(px(110f), py(82f))
            quadraticBezierTo(px(118f), py(74f), px(110f), py(66f))
            quadraticBezierTo(px(102f), py(58f), px(110f), py(50f))
        }
        drawPath(steam1, cs.secondary, style = Stroke(width = px(3f), cap = StrokeCap.Round))
        drawPath(steam2, cs.secondary, style = Stroke(width = px(3f), cap = StrokeCap.Round))
    }
}
