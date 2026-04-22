package com.smarttimetable.scheduler.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile AppDao _appDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `faculty` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `subject` TEXT NOT NULL, `availabilityCsv` TEXT NOT NULL, `avgLeavesPerMonth` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `subject` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `targetClassName` TEXT NOT NULL, `weeklyClassesRequired` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `classroom` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `roomName` TEXT NOT NULL, `capacity` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `class_group` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `className` TEXT NOT NULL, `sectionName` TEXT NOT NULL, `strength` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `timetable_entry` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `orderIndex` INTEGER NOT NULL, `day` TEXT NOT NULL, `period` INTEGER NOT NULL, `subjectId` INTEGER NOT NULL, `subjectName` TEXT NOT NULL, `subjectTargetClassName` TEXT NOT NULL, `facultyId` INTEGER NOT NULL, `facultyName` TEXT NOT NULL, `classroomId` INTEGER NOT NULL, `classroomName` TEXT NOT NULL, `classroomCapacity` INTEGER NOT NULL, `conflict` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8bb933b9f837edb55b1ac9edd2040973')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `faculty`");
        db.execSQL("DROP TABLE IF EXISTS `subject`");
        db.execSQL("DROP TABLE IF EXISTS `classroom`");
        db.execSQL("DROP TABLE IF EXISTS `class_group`");
        db.execSQL("DROP TABLE IF EXISTS `timetable_entry`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsFaculty = new HashMap<String, TableInfo.Column>(5);
        _columnsFaculty.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFaculty.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFaculty.put("subject", new TableInfo.Column("subject", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFaculty.put("availabilityCsv", new TableInfo.Column("availabilityCsv", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFaculty.put("avgLeavesPerMonth", new TableInfo.Column("avgLeavesPerMonth", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFaculty = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFaculty = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFaculty = new TableInfo("faculty", _columnsFaculty, _foreignKeysFaculty, _indicesFaculty);
        final TableInfo _existingFaculty = TableInfo.read(db, "faculty");
        if (!_infoFaculty.equals(_existingFaculty)) {
          return new RoomOpenHelper.ValidationResult(false, "faculty(com.smarttimetable.scheduler.data.FacultyEntity).\n"
                  + " Expected:\n" + _infoFaculty + "\n"
                  + " Found:\n" + _existingFaculty);
        }
        final HashMap<String, TableInfo.Column> _columnsSubject = new HashMap<String, TableInfo.Column>(4);
        _columnsSubject.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("targetClassName", new TableInfo.Column("targetClassName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("weeklyClassesRequired", new TableInfo.Column("weeklyClassesRequired", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSubject = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSubject = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSubject = new TableInfo("subject", _columnsSubject, _foreignKeysSubject, _indicesSubject);
        final TableInfo _existingSubject = TableInfo.read(db, "subject");
        if (!_infoSubject.equals(_existingSubject)) {
          return new RoomOpenHelper.ValidationResult(false, "subject(com.smarttimetable.scheduler.data.SubjectEntity).\n"
                  + " Expected:\n" + _infoSubject + "\n"
                  + " Found:\n" + _existingSubject);
        }
        final HashMap<String, TableInfo.Column> _columnsClassroom = new HashMap<String, TableInfo.Column>(3);
        _columnsClassroom.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassroom.put("roomName", new TableInfo.Column("roomName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassroom.put("capacity", new TableInfo.Column("capacity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysClassroom = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesClassroom = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoClassroom = new TableInfo("classroom", _columnsClassroom, _foreignKeysClassroom, _indicesClassroom);
        final TableInfo _existingClassroom = TableInfo.read(db, "classroom");
        if (!_infoClassroom.equals(_existingClassroom)) {
          return new RoomOpenHelper.ValidationResult(false, "classroom(com.smarttimetable.scheduler.data.ClassroomEntity).\n"
                  + " Expected:\n" + _infoClassroom + "\n"
                  + " Found:\n" + _existingClassroom);
        }
        final HashMap<String, TableInfo.Column> _columnsClassGroup = new HashMap<String, TableInfo.Column>(4);
        _columnsClassGroup.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassGroup.put("className", new TableInfo.Column("className", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassGroup.put("sectionName", new TableInfo.Column("sectionName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassGroup.put("strength", new TableInfo.Column("strength", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysClassGroup = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesClassGroup = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoClassGroup = new TableInfo("class_group", _columnsClassGroup, _foreignKeysClassGroup, _indicesClassGroup);
        final TableInfo _existingClassGroup = TableInfo.read(db, "class_group");
        if (!_infoClassGroup.equals(_existingClassGroup)) {
          return new RoomOpenHelper.ValidationResult(false, "class_group(com.smarttimetable.scheduler.data.ClassGroupEntity).\n"
                  + " Expected:\n" + _infoClassGroup + "\n"
                  + " Found:\n" + _existingClassGroup);
        }
        final HashMap<String, TableInfo.Column> _columnsTimetableEntry = new HashMap<String, TableInfo.Column>(13);
        _columnsTimetableEntry.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("orderIndex", new TableInfo.Column("orderIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("day", new TableInfo.Column("day", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("period", new TableInfo.Column("period", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("subjectId", new TableInfo.Column("subjectId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("subjectName", new TableInfo.Column("subjectName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("subjectTargetClassName", new TableInfo.Column("subjectTargetClassName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("facultyId", new TableInfo.Column("facultyId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("facultyName", new TableInfo.Column("facultyName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("classroomId", new TableInfo.Column("classroomId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("classroomName", new TableInfo.Column("classroomName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("classroomCapacity", new TableInfo.Column("classroomCapacity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimetableEntry.put("conflict", new TableInfo.Column("conflict", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTimetableEntry = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTimetableEntry = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTimetableEntry = new TableInfo("timetable_entry", _columnsTimetableEntry, _foreignKeysTimetableEntry, _indicesTimetableEntry);
        final TableInfo _existingTimetableEntry = TableInfo.read(db, "timetable_entry");
        if (!_infoTimetableEntry.equals(_existingTimetableEntry)) {
          return new RoomOpenHelper.ValidationResult(false, "timetable_entry(com.smarttimetable.scheduler.data.TimetableEntryEntity).\n"
                  + " Expected:\n" + _infoTimetableEntry + "\n"
                  + " Found:\n" + _existingTimetableEntry);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8bb933b9f837edb55b1ac9edd2040973", "c75058b6fd68fc9f538c4f7faea499cb");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "faculty","subject","classroom","class_group","timetable_entry");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `faculty`");
      _db.execSQL("DELETE FROM `subject`");
      _db.execSQL("DELETE FROM `classroom`");
      _db.execSQL("DELETE FROM `class_group`");
      _db.execSQL("DELETE FROM `timetable_entry`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AppDao.class, AppDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AppDao appDao() {
    if (_appDao != null) {
      return _appDao;
    } else {
      synchronized(this) {
        if(_appDao == null) {
          _appDao = new AppDao_Impl(this);
        }
        return _appDao;
      }
    }
  }
}
