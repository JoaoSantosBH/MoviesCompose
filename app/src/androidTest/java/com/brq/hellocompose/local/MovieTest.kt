package com.brq.hellocompose.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brq.hellocompose.core.data.local.AppDatabase
import com.brq.hellocompose.core.data.local.dao.MovieDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ReadAndWriteTest {

    
    private lateinit var dao: MovieDao
    private lateinit var db: AppDatabase
companion object {
}
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        dao = db.movieDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadInList() {
        val movieCreated = createFakeMovie()
        dao.insertFavoriteMovie(movieCreated)
        val expected = true
        val result = dao.checkIfisAFavoriteMovie(DUMB_MOVIE_ID)
        assertEquals(expected, result)
    }

    @Test
    @Throws(Exception::class)
    fun deleteFavorite() {
        dao.insertFavoriteMovie(createFakeMovie())
        val expected = dao.checkIfisAFavoriteMovie(12345)
        assertEquals(expected, true)
        val created = dao.getFavoriteMovieById(12345)
        dao.removeFavoriteMovie(created)
        val result = dao.checkIfisAFavoriteMovie(12345)
        assertEquals(result, false)
    }

    @Test
    @Throws(Exception::class)
    fun checkLists() {
        dao.insertFavoriteMovie(createFakeMovie())
        dao.insertFavoriteMovie(createFakeMovie())
        val expected = dao.getFavoriteMoviesList()
        assertEquals(expected.size, 2)

    }

}