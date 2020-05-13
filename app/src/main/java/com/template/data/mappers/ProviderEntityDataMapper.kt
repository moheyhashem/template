package com.template.data.mappers

import com.template.data.entities.ProviderData
import com.template.domain.common.Mapper
import com.template.domain.entities.ProviderEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProviderEntityDataMapper @Inject constructor() : Mapper<ProviderEntity, ProviderData>() {

    override fun mapFrom(from: ProviderEntity): ProviderData {
        return ProviderData(
                id = from.id,
                address = from.address,
                name = from.name,
                governId = from.governId,
                updatedBy = from.updatedBy,
                createdBy = from.createdBy,
                createdAt = from.createdAt,
                updatedAt = from.updatedAt,
                areaId = from.areaId,
                emails = from.emails,
                phones = from.phones,
                type = from.type
        )
    }
}
