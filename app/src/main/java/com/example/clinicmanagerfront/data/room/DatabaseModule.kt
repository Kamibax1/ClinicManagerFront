package com.example.clinicmanagerfront.data.room

import android.content.Context
import androidx.room.Room
import com.example.clinicmanagerfront.data.room.dao.MyAppointmentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : MyAppointmentDatabase {
        return Room.databaseBuilder(
            context,
            MyAppointmentDatabase::class.java,
            "my_appointment_database"
        ).build()
    }

    @Provides
    fun provideMyAppointmentDao(database: MyAppointmentDatabase) : MyAppointmentDao {
        return database.myAppointmentDao()
    }
}