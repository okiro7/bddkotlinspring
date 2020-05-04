package com.dfco.restcliente.consultacliente.bus

import org.apache.kafka.common.serialization.Serializer
import java.nio.charset.StandardCharsets
import java.util.*


class UuidSerializer : Serializer<UUID?> {
    override fun configure(configs: Map<String?, *>?, isKey: Boolean) {
        // Empty
    }

    override fun serialize(topic: String, data: UUID?): ByteArray {
        return data?.toString()?.toByteArray(StandardCharsets.UTF_8)!!
    }

    override fun close() {
        // Empty
    }
}