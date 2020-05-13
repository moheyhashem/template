package com.template.data.db

import android.annotation.SuppressLint
import com.template.data.mappers.ProviderDataEntityMapper
import com.template.data.mappers.ProviderEntityDataMapper
import com.template.domain.TemplateCache
import com.template.domain.entities.ProviderEntity
import com.template.domain.entities.SyndicateEntity
import com.template.testing.OpenForTesting
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class RoomHistoryCache @Inject constructor(
        database: TemplateDb,
        private val providerDataEntityMapper: ProviderDataEntityMapper,
        private val providerEntityDataMapper: ProviderEntityDataMapper
) : TemplateCache {
    override fun getSyndicates(): Observable<List<SyndicateEntity>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun saveSyndicates(syndicates: List<SyndicateEntity>): Single<List<SyndicateEntity>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    private val providerDao: ProviderDao = database.providerDao()

    @SuppressLint("CheckResult")
    override fun addFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        return Single.create { emitter ->
            providerDao.saveProvider(providerEntityDataMapper.mapFrom(providerEntity))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe({
                        emitter.onSuccess(providerEntity)
                    }, {
                        emitter.onError(it)
                    })
        }

    }

    override fun removeFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        return Single.create { emitter ->
            providerDao.removeProvider(providerEntityDataMapper.mapFrom(providerEntity))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe({
                        emitter.onSuccess(providerEntity)
                    }, {
                        emitter.onError(it)
                    })
        }
    }

    override fun checkFavorite(providerEntity: ProviderEntity): Single<Boolean> {
        return Single.create { emitter ->
            providerDao.isFavorite(providerEntityDataMapper.mapFrom(providerEntity).id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe({
                        if (it.isEmpty())
                            emitter.onSuccess(false)
                        else
                            emitter.onSuccess(true)
                    }, {
                        emitter.onError(it)
                    })
        }
    }

    override fun getFavorites(): Observable<List<ProviderEntity>> {
        return Observable.create { emitter ->
            providerDao.getProviders()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe({
                        emitter.onNext (it.map { providerDataEntityMapper.mapFrom(it) })
                    }, {
                        emitter.onError(it)
                    })
        }

    }

    override fun clear() {
//        providerDao.clear()
    }

    override fun isEmpty(): Single<Boolean> {
        return Single.just(true)
    }
}