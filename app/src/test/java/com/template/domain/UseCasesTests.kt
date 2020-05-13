package com.template.domain

import com.template.DomainTestUtils
import com.template.domain.common.TestTransformer
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mockito

class UseCasesTests {

    @Test
    fun getWeather() { // rename test i.e. testGetWeatherUseCases_getWeather_Success ,testGetWeatherUseCases_getWeather_Error
        val weatherEntity = DomainTestUtils.getTestWeatherEntity()
        val weatherRepository = Mockito.mock(TemplateRepository::class.java)
        val getWeather = GetWeather(TestTransformer(), weatherRepository)

        Mockito.`when`(weatherRepository.getWeather(0.0 , 0.0)).thenReturn(Observable.just(weatherEntity))// try whenever

        getWeather.getWeather(0.0 , 0.0).test()
                .assertValue { returnedWeatherEntity ->
                    returnedWeatherEntity != null
                }
                .assertComplete()
    }
}