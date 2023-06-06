//package com.brq.hellocompose
//
//import android.content.Context
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.brq.hellocompose.local.AppDatabase
//import com.brq.hellocompose.local.dao.MovieDao
//import org.hamcrest.CoreMatchers
//import org.hamcrest.MatcherAssert
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import java.io.IOException
//
//@RunWith(AndroidJUnit4::class)
//class RoomReadAndWriteTest {
//
//    private lateinit var dao: MovieDao
//    private lateinit var db: AppDatabase
//
//    @Before
//    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(
//            context, AppDatabase::class.java).build()
//        dao = db.roomDao()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun writeRoomAndReadInList() {
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomRead = dao.getRoom(DUMB_ROOM_ID)
//        MatcherAssert.assertThat(roomRead, CoreMatchers.equalTo(roomCreated))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomIdRoom(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(idRoom = 13)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.idRoom, CoreMatchers.equalTo(roomUpdated?.idRoom))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomOrderNumber(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(orderNumber = "M123Cre")
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(
//            newRoom?.orderNumber,
//            CoreMatchers.equalTo(roomUpdated?.orderNumber)
//        )
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRooKind(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(kind = 33)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.kind, CoreMatchers.equalTo(roomUpdated?.kind))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomNote(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(note = "MamamamTi tyi iii titi ti")
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.note, CoreMatchers.equalTo(roomUpdated?.note))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomWallIsNew(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(wallIsNew = true)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.wallIsNew, CoreMatchers.equalTo(roomUpdated?.wallIsNew))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomDoorNumber(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(doorNumber = 66)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.doorNumber, CoreMatchers.equalTo(roomUpdated?.doorNumber))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomDoorKind(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(doorKind = 0)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.doorKind, CoreMatchers.equalTo(roomUpdated?.doorKind))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomWindowNumber(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(windowNumber = 3)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(
//            newRoom?.windowNumber,
//            CoreMatchers.equalTo(roomUpdated?.windowNumber)
//        )
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomWindowKind(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(windowKind = 11)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.windowKind, CoreMatchers.equalTo(roomUpdated?.windowKind))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomClosetNumber(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(closetNumber = 33)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(
//            newRoom?.closetNumber,
//            CoreMatchers.equalTo(roomUpdated?.closetNumber)
//        )
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRooClosetKind(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(closetKind = 22)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.closetKind, CoreMatchers.equalTo(roomUpdated?.closetKind))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomMirrorNumber(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(mirrorNumber = 11)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(
//            newRoom?.mirrorNumber,
//            CoreMatchers.equalTo(roomUpdated?.mirrorNumber)
//        )
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRooMirrorKind(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(mirrorKind = 88)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.mirrorKind, CoreMatchers.equalTo(roomUpdated?.mirrorKind))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomDemaos(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(demaos = 22)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.demaos, CoreMatchers.equalTo(roomUpdated?.demaos))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomWidth(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(width = 1F)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.width, CoreMatchers.equalTo(roomUpdated?.width))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRooHeight(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(height = 2F)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.height, CoreMatchers.equalTo(roomUpdated?.height))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRooLenght(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(lenght = 3F)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.lenght, CoreMatchers.equalTo(roomUpdated?.lenght))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomTeto(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(teto = 13F)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.teto, CoreMatchers.equalTo(roomUpdated?.teto))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomBase(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(base =5F)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(newRoom?.base, CoreMatchers.equalTo(roomUpdated?.base))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateRoomTotalSquareMETER(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        val newRoom = roomWrited?.copy(totalSquareMETER = 13F)
//        if (newRoom != null) { dao.updateRoom(newRoom) }
//        val roomUpdated = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(
//            newRoom?.totalSquareMETER,
//            CoreMatchers.equalTo(roomUpdated?.totalSquareMETER)
//        )
//    }
//
//
//    @Test
//    @Throws(Exception::class)
//    fun deleteRoom(){
//        val roomCreated = createRoom()
//        dao.insertRoom(roomCreated)
//        val roomWrited = roomCreated.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(roomWrited?.idRoom, CoreMatchers.equalTo(roomCreated.idRoom))
//        val expectedDeleted = null
//        if (roomWrited != null) { dao.deleteRoom(roomWrited)}
//        val roomDeleted = roomWrited?.id?.let { dao.getRoom(it) }
//        MatcherAssert.assertThat(roomDeleted, CoreMatchers.equalTo(expectedDeleted))
//    }
//}