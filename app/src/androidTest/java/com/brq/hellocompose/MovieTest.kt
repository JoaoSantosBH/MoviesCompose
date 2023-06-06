package com.brq.hellocompose

import android.content.Context
import androidx.movie.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brq.hellocompose.local.AppDatabase
import com.brq.hellocompose.local.dao.MovieDao
import com.brq.hellocompose.local.entities.FavoriteMovieEntity
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomReadAndWriteTest {

    
    private lateinit var dao: MovieDao
    private lateinit var db: AppDatabase
companion object {
    const val  DUMB_ROOM_ID = 12345

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
    fun writeRoomAndReadInList() {
        val movieCreated = createFakeMovie()
        dao.insertFavoriteMovie(movieCreated)
        val movieRead = dao.getFavoriteMovie(DUMB_ROOM_ID)
        MatcherAssert.assertThat(movieRead, CoreMatchers.equalTo(movieCreated))
    }

    @Test
    @Throws(Exception::class)
    fun deleteRoom() {
        val movieCreated = createFakeMovie()
        dao.removeFavoriteMovie(movieCreated)
        val movieWrited = movieCreated.id?.let { dao.getFavoriteMovie(it) }
        MatcherAssert.assertThat(movieWrited?, CoreMatchers.equalTo(movieCreated))
        val expectedDeleted = null
        if (movieWrited != null) {
            dao.removeFavoriteMovie(movieWrited)
        }
        val movieDeleted = movieWrited?.let { dao.getFavoriteMovie(it) }
        MatcherAssert.assertThat(movieDeleted, CoreMatchers.equalTo(expectedDeleted))
    }

    private fun createFakeMovie() = FavoriteMovieEntity(
        id = 12345
    )
}