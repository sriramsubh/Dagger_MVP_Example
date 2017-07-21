/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.aspiresys.android.deezfoodz.dagger;

import com.aspiresys.android.deezfoodz.app.Constants;
import com.aspiresys.android.deezfoodz.network.UsdaApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
  private static final String NAME_BASE_URL = "NAME_BASE_URL";

  //DI to resolve the Base)Url dependency required by the network module
  @Provides
  @Named(NAME_BASE_URL)
  String provideBaseUrlString() {
    return Constants.BASE_URL;
  }

  //DI to resolve the Gson Converter Factory
  @Provides
  @Singleton
  Converter.Factory provideGsonConverter() {
    return GsonConverterFactory.create();
  }

  /**
   * provideRetrofit method to build the network client with URL and Converter
   *
   * @param converter
   * @param baseUrl
   * @return
   */
  @Provides
  @Singleton
  Retrofit provideRetrofit(Converter.Factory converter, @Named(NAME_BASE_URL) String baseUrl) {
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converter)
        .build();
  }

    /**
     * provideUsdaApi method to provide the API Database required by the application
     * @param retrofit
     * @return
     */
  @Provides
  @Singleton
  UsdaApi provideUsdaApi(Retrofit retrofit) {
    return retrofit.create(UsdaApi.class);
  }
}
