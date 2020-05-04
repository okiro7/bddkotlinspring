package com.dfco.restcliente.consultacliente.bus

import org.apache.kafka.common.serialization.Deserializer
import java.nio.charset.StandardCharsets
import java.util.*


class UuidDeserializer : Deserializer<UUID> {
    override fun configure(configs: Map<String?, *>?, isKey: Boolean) {
        // Empty
    }

    override fun deserialize(topic: String, data: ByteArray): UUID? {
        return if (data == null) null else UUID.fromString(String(data, StandardCharsets.UTF_8))
    }

    override fun close() {
        // Empty
    }
}