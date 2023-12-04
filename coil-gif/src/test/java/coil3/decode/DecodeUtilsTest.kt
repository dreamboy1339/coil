package coil3.decode

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import coil3.test.RobolectricTest
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import okio.buffer
import okio.source
import org.junit.Before
import org.junit.Test

class DecodeUtilsTest : RobolectricTest() {

    private lateinit var context: Context

    @Before
    fun before() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun `isGif true positive`() {
        val source = context.assets.open("animated.gif").source().buffer()
        assertTrue(DecodeUtils.isGif(source))
    }

    @Test
    fun `isGif true negative`() {
        val source = context.assets.open("normal.jpg").source().buffer()
        assertFalse(DecodeUtils.isGif(source))
    }

    @Test
    fun `isWebP true positive`() {
        val source = context.assets.open("static.webp").source().buffer()
        assertTrue(DecodeUtils.isWebP(source))
    }

    @Test
    fun `isWebP true negative`() {
        val source = context.assets.open("animated.gif").source().buffer()
        assertFalse(DecodeUtils.isWebP(source))
    }

    @Test
    fun `isAnimatedWebP true positive`() {
        val source = context.assets.open("animated.webp").source().buffer()
        assertTrue(DecodeUtils.isAnimatedWebP(source))
    }

    @Test
    fun `isAnimatedWebP true negative`() {
        val source = context.assets.open("static.webp").source().buffer()
        assertFalse(DecodeUtils.isAnimatedWebP(source))
    }

    @Test
    fun `isHeif true positive`() {
        val source = context.assets.open("static.heif").source().buffer()
        assertTrue(DecodeUtils.isHeif(source))
    }

    @Test
    fun `isHeif true negative`() {
        val source = context.assets.open("normal.jpg").source().buffer()
        assertFalse(DecodeUtils.isHeif(source))
    }

    @Test
    fun `isAnimatedHeif true positive`() {
        val source = context.assets.open("animated.heif").source().buffer()
        assertTrue(DecodeUtils.isAnimatedHeif(source))
    }

    @Test
    fun `isAnimatedHeif true negative`() {
        val source = context.assets.open("animated.webp").source().buffer()
        assertFalse(DecodeUtils.isAnimatedHeif(source))
    }
}