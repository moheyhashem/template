package com.template.presentation.mappers

import com.template.domain.common.Mapper
import com.template.domain.entities.ProviderEntity
import com.template.presentation.entities.ProviderUI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProviderEntityUIMapper @Inject constructor() : Mapper<ProviderEntity, ProviderUI>() {

    override fun mapFrom(from: ProviderEntity): ProviderUI {
        return ProviderUI(
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
