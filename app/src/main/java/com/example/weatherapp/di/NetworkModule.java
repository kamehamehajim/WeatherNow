package com.example.weatherapp.di;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.R;
import com.example.weatherapp.data.api.OpenWeatherApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;
import java.io.IOException;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OpenWeatherApi provideOpenWeatherApi(Retrofit retrofit) {
        return retrofit.create(OpenWeatherApi.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient httpClient) {
        String url = new Uri.Builder()
                .scheme("https")
                .encodedAuthority("api.openweathermap.org")
                .path("data/2.5")
                .build()
                .toString();
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(url + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Context context) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(interceptor);
            clientBuilder.addNetworkInterceptor(new AuthInterceptor(context));
        }

        return clientBuilder.build();
    }

    private static class AuthInterceptor implements Interceptor {

        private final Context mContext;

        private AuthInterceptor(Context context) {
            mContext = context;
        }

        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            String apiKey = mContext.getString(R.string.open_weather_api_key);
            HttpUrl url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("appid", apiKey)
                    .build();
            Request request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(request);
        }
    }
}
