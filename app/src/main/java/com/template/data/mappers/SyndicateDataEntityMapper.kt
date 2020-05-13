package com.template.data.mappers

import com.template.data.entities.SyndicateData
import com.template.domain.common.Mapper
import com.template.domain.entities.SyndicateEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyndicateDataEntityMapper @Inject constructor() : Mapper<SyndicateData, SyndicateEntity>() {

    override fun mapFrom(from: SyndicateData): SyndicateEntity {
        return SyndicateEntity(
                id = from.id,
                updatedAt = from.updatedAt,
                createdAt = from.createdAt,
                email = from.email,
                address = from.address,
                createdBy = from.createdBy,
                descAr = from.descAr,
                descEn = from.descEn,
                fax = from.fax,
                governId = from.governId,
                level = from.level,
                logo = from.logo,
                mobile = from.mobile,
                parentId = from.parentId,
                phone = from.phone,
                updatedBy = from.updatedBy,
                subSyndicates = from.subSyndicates?.map { syndicateData -> SyndicateDataEntityMapper().mapFrom(syndicateData) }
        )
    }
}
