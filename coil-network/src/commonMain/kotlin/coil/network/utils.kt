package coil.network

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.isEmpty
import io.ktor.utils.io.core.readFully
import okio.BufferedSink

/** Write a [ByteReadChannel] to [sink] using streaming. */
internal suspend fun ByteReadChannel.readFully(sink: BufferedSink) {
    val buffer = ByteArray(OKIO_BUFFER_SIZE)

    while (!isClosedForRead) {
        val packet = readRemaining(buffer.size.toLong())
        if (packet.isEmpty) break

        val bytesRead = packet.remaining.toInt()
        packet.readFully(buffer, 0, bytesRead)
        sink.write(buffer, 0, bytesRead)
    }
}

// Okio uses 8 KB internally.
private const val OKIO_BUFFER_SIZE: Int = 8 * 1024