# Room Room
An app with implementation of [Room](https://developer.android.com/training/data-storage/room) database for Android platform

The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. In particular, Room provides the following benefits:

-   Compile-time verification of SQL queries.
-   Convenience annotations that minimize repetitive and error-prone boilerplate code.
-   Streamlined database migration paths.

To persist data with Room database you just need:


**1. Add Room dependencies (build.gradle)**
```python
plugins {  
  id 'kotlin-android-extensions'  
  id 'kotlin-kapt'  
}

dependencies {  
  def roomVersion = "2.4.1"  
  implementation "androidx.room:room-runtime:$roomVersion"  
  kapt "androidx.room:room-compiler:$roomVersion"
}
```
**2. Create your Entity**
```kotlin
@Entity   
data class User(  
  @PrimaryKey(autoGenerate = true) val uid: Int? = 0,  
  @ColumnInfo(name = "first_name") val firstName: String?,  
  @ColumnInfo(name = "last_name") val lastName: String?  
)
```
**3. Declare your data access object**
```kotlin
@Dao  
interface UserDao {  
    @Query("SELECT * FROM user")  
    fun getAll(): List<User>  
  
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")  
    fun loadAllByIds(userIds: IntArray): List<User>  
  
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +  
            "last_name LIKE :last LIMIT 1")  
    fun findByName(first: String, last: String): User  
  
    @Insert(onConflict = OnConflictStrategy.REPLACE)  
    fun insertAll(vararg users: User)  
  
    @Update
    fun updateUser(user: User)  
  
    @Delete  
	fun delete(user: User)  
}
```

We use a singleton pattern to call our database when needed. Since we are not using LiveData or Coroutines scopes, we need to use .allowMainThreadQueries(). Keep in mind that this approach is not the best answer 

**4. App database singleton**
```kotlin
@Database(entities = [User::class], version = 1)  
abstract class AppDatabase : RoomDatabase() {  
	abstract fun userDao(): UserDao

	companion object {  
        private var INSTANCE: AppDatabase? = null
		fun getDatabase(context: Context): AppDatabase {  
			if (INSTANCE == null) {
					synchronized(this) {  
						INSTANCE = Room.databaseBuilder(  
	                        context,  
							AppDatabase::class.java, "user_database")  
                            .allowMainThreadQueries()  
                            .build()
                    }  
			}  
            return INSTANCE!!  
        }  
    }  
}
```
Everything is ready, now just call our singleton and start to use

**5. Start your queries**
```kotlin
val db = AppDatabase.getDatabase(requireContext())  
val userDao = db.userDao()

val myUser = (null, "Void", "Hash")
userDao.insertAll(myUser)
userDao.updateUser(myUser)
userDao.getAll()
userDao.delete(myUser)
```

A piece of cake! Now, have fun!
