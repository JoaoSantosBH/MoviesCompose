package com.brq.hellocompose.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brq.hellocompose.local.dao.MovieDao
import com.brq.hellocompose.local.entities.FavoriteMovieEntity
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
    const val  DUMB_MOVIE_ID = 12345

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

    private fun createFakeMovie() = FavoriteMovieEntity(
        movieId = 12345
    )
}