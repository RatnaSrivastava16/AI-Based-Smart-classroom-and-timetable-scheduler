package com.smarttimetable.scheduler.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDao_Impl implements AppDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FacultyEntity> __insertionAdapterOfFacultyEntity;

  private final EntityInsertionAdapter<SubjectEntity> __insertionAdapterOfSubjectEntity;

  private final EntityInsertionAdapter<ClassroomEntity> __insertionAdapterOfClassroomEntity;

  private final EntityInsertionAdapter<ClassGroupEntity> __insertionAdapterOfClassGroupEntity;

  private final EntityInsertionAdapter<TimetableEntryEntity> __insertionAdapterOfTimetableEntryEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFacultyById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSubjectById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteClassroomById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteClassGroupById;

  private final SharedSQLiteStatement __preparedStmtOfClearFaculty;

  private final SharedSQLiteStatement __preparedStmtOfClearSubjects;

  private final SharedSQLiteStatement __preparedStmtOfClearClassrooms;

  private final SharedSQLiteStatement __preparedStmtOfClearClassGroups;

  private final SharedSQLiteStatement __preparedStmtOfClearTimetableEntries;

  public AppDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFacultyEntity = new EntityInsertionAdapter<FacultyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `faculty` (`id`,`name`,`subject`,`availabilityCsv`,`avgLeavesPerMonth`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FacultyEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getSubject() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSubject());
        }
        if (entity.getAvailabilityCsv() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAvailabilityCsv());
        }
        statement.bindLong(5, entity.getAvgLeavesPerMonth());
      }
    };
    this.__insertionAdapterOfSubjectEntity = new EntityInsertionAdapter<SubjectEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `subject` (`id`,`name`,`targetClassName`,`weeklyClassesRequired`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SubjectEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getTargetClassName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTargetClassName());
        }
        statement.bindLong(4, entity.getWeeklyClassesRequired());
      }
    };
    this.__insertionAdapterOfClassroomEntity = new EntityInsertionAdapter<ClassroomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `classroom` (`id`,`roomName`,`capacity`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ClassroomEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getRoomName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getRoomName());
        }
        statement.bindLong(3, entity.getCapacity());
      }
    };
    this.__insertionAdapterOfClassGroupEntity = new EntityInsertionAdapter<ClassGroupEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `class_group` (`id`,`className`,`sectionName`,`strength`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ClassGroupEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getClassName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getClassName());
        }
        if (entity.getSectionName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSectionName());
        }
        statement.bindLong(4, entity.getStrength());
      }
    };
    this.__insertionAdapterOfTimetableEntryEntity = new EntityInsertionAdapter<TimetableEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `timetable_entry` (`id`,`orderIndex`,`day`,`period`,`subjectId`,`subjectName`,`subjectTargetClassName`,`facultyId`,`facultyName`,`classroomId`,`classroomName`,`classroomCapacity`,`conflict`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TimetableEntryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getOrderIndex());
        if (entity.getDay() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDay());
        }
        statement.bindLong(4, entity.getPeriod());
        statement.bindLong(5, entity.getSubjectId());
        if (entity.getSubjectName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSubjectName());
        }
        if (entity.getSubjectTargetClassName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getSubjectTargetClassName());
        }
        statement.bindLong(8, entity.getFacultyId());
        if (entity.getFacultyName() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getFacultyName());
        }
        statement.bindLong(10, entity.getClassroomId());
        if (entity.getClassroomName() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getClassroomName());
        }
        statement.bindLong(12, entity.getClassroomCapacity());
        final int _tmp = entity.getConflict() ? 1 : 0;
        statement.bindLong(13, _tmp);
      }
    };
    this.__preparedStmtOfDeleteFacultyById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM faculty WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteSubjectById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM subject WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteClassroomById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM classroom WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteClassGroupById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM class_group WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearFaculty = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM faculty";
        return _query;
      }
    };
    this.__preparedStmtOfClearSubjects = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM subject";
        return _query;
      }
    };
    this.__preparedStmtOfClearClassrooms = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM classroom";
        return _query;
      }
    };
    this.__preparedStmtOfClearClassGroups = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM class_group";
        return _query;
      }
    };
    this.__preparedStmtOfClearTimetableEntries = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM timetable_entry";
        return _query;
      }
    };
  }

  @Override
  public Object insertFaculty(final FacultyEntity faculty,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFacultyEntity.insert(faculty);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertFacultyList(final List<FacultyEntity> faculties,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFacultyEntity.insert(faculties);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertSubject(final SubjectEntity subject,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSubjectEntity.insert(subject);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertSubjectList(final List<SubjectEntity> subjects,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSubjectEntity.insert(subjects);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertClassroom(final ClassroomEntity classroom,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfClassroomEntity.insert(classroom);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertClassroomList(final List<ClassroomEntity> classrooms,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfClassroomEntity.insert(classrooms);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertClassGroup(final ClassGroupEntity classGroup,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfClassGroupEntity.insert(classGroup);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertClassGroupList(final List<ClassGroupEntity> classGroups,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfClassGroupEntity.insert(classGroups);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTimetableEntries(final List<TimetableEntryEntity> entries,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTimetableEntryEntity.insert(entries);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFacultyById(final int facultyId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFacultyById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, facultyId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteFacultyById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSubjectById(final int subjectId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSubjectById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, subjectId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteSubjectById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteClassroomById(final int classroomId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteClassroomById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, classroomId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteClassroomById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteClassGroupById(final int classId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteClassGroupById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, classId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteClassGroupById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearFaculty(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearFaculty.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearFaculty.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearSubjects(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearSubjects.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearSubjects.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearClassrooms(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearClassrooms.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearClassrooms.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearClassGroups(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearClassGroups.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearClassGroups.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearTimetableEntries(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearTimetableEntries.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearTimetableEntries.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllFaculty(final Continuation<? super List<FacultyEntity>> $completion) {
    final String _sql = "SELECT * FROM faculty";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FacultyEntity>>() {
      @Override
      @NonNull
      public List<FacultyEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "subject");
          final int _cursorIndexOfAvailabilityCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "availabilityCsv");
          final int _cursorIndexOfAvgLeavesPerMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "avgLeavesPerMonth");
          final List<FacultyEntity> _result = new ArrayList<FacultyEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FacultyEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSubject;
            if (_cursor.isNull(_cursorIndexOfSubject)) {
              _tmpSubject = null;
            } else {
              _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
            }
            final String _tmpAvailabilityCsv;
            if (_cursor.isNull(_cursorIndexOfAvailabilityCsv)) {
              _tmpAvailabilityCsv = null;
            } else {
              _tmpAvailabilityCsv = _cursor.getString(_cursorIndexOfAvailabilityCsv);
            }
            final int _tmpAvgLeavesPerMonth;
            _tmpAvgLeavesPerMonth = _cursor.getInt(_cursorIndexOfAvgLeavesPerMonth);
            _item = new FacultyEntity(_tmpId,_tmpName,_tmpSubject,_tmpAvailabilityCsv,_tmpAvgLeavesPerMonth);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllSubjects(final Continuation<? super List<SubjectEntity>> $completion) {
    final String _sql = "SELECT * FROM subject";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SubjectEntity>>() {
      @Override
      @NonNull
      public List<SubjectEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTargetClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "targetClassName");
          final int _cursorIndexOfWeeklyClassesRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "weeklyClassesRequired");
          final List<SubjectEntity> _result = new ArrayList<SubjectEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SubjectEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpTargetClassName;
            if (_cursor.isNull(_cursorIndexOfTargetClassName)) {
              _tmpTargetClassName = null;
            } else {
              _tmpTargetClassName = _cursor.getString(_cursorIndexOfTargetClassName);
            }
            final int _tmpWeeklyClassesRequired;
            _tmpWeeklyClassesRequired = _cursor.getInt(_cursorIndexOfWeeklyClassesRequired);
            _item = new SubjectEntity(_tmpId,_tmpName,_tmpTargetClassName,_tmpWeeklyClassesRequired);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllClassrooms(final Continuation<? super List<ClassroomEntity>> $completion) {
    final String _sql = "SELECT * FROM classroom";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ClassroomEntity>>() {
      @Override
      @NonNull
      public List<ClassroomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoomName = CursorUtil.getColumnIndexOrThrow(_cursor, "roomName");
          final int _cursorIndexOfCapacity = CursorUtil.getColumnIndexOrThrow(_cursor, "capacity");
          final List<ClassroomEntity> _result = new ArrayList<ClassroomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ClassroomEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpRoomName;
            if (_cursor.isNull(_cursorIndexOfRoomName)) {
              _tmpRoomName = null;
            } else {
              _tmpRoomName = _cursor.getString(_cursorIndexOfRoomName);
            }
            final int _tmpCapacity;
            _tmpCapacity = _cursor.getInt(_cursorIndexOfCapacity);
            _item = new ClassroomEntity(_tmpId,_tmpRoomName,_tmpCapacity);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllClassGroups(final Continuation<? super List<ClassGroupEntity>> $completion) {
    final String _sql = "SELECT * FROM class_group";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ClassGroupEntity>>() {
      @Override
      @NonNull
      public List<ClassGroupEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "className");
          final int _cursorIndexOfSectionName = CursorUtil.getColumnIndexOrThrow(_cursor, "sectionName");
          final int _cursorIndexOfStrength = CursorUtil.getColumnIndexOrThrow(_cursor, "strength");
          final List<ClassGroupEntity> _result = new ArrayList<ClassGroupEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ClassGroupEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpClassName;
            if (_cursor.isNull(_cursorIndexOfClassName)) {
              _tmpClassName = null;
            } else {
              _tmpClassName = _cursor.getString(_cursorIndexOfClassName);
            }
            final String _tmpSectionName;
            if (_cursor.isNull(_cursorIndexOfSectionName)) {
              _tmpSectionName = null;
            } else {
              _tmpSectionName = _cursor.getString(_cursorIndexOfSectionName);
            }
            final int _tmpStrength;
            _tmpStrength = _cursor.getInt(_cursorIndexOfStrength);
            _item = new ClassGroupEntity(_tmpId,_tmpClassName,_tmpSectionName,_tmpStrength);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTimetableEntries(
      final Continuation<? super List<TimetableEntryEntity>> $completion) {
    final String _sql = "SELECT * FROM timetable_entry ORDER BY orderIndex ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TimetableEntryEntity>>() {
      @Override
      @NonNull
      public List<TimetableEntryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final int _cursorIndexOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "day");
          final int _cursorIndexOfPeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "period");
          final int _cursorIndexOfSubjectId = CursorUtil.getColumnIndexOrThrow(_cursor, "subjectId");
          final int _cursorIndexOfSubjectName = CursorUtil.getColumnIndexOrThrow(_cursor, "subjectName");
          final int _cursorIndexOfSubjectTargetClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "subjectTargetClassName");
          final int _cursorIndexOfFacultyId = CursorUtil.getColumnIndexOrThrow(_cursor, "facultyId");
          final int _cursorIndexOfFacultyName = CursorUtil.getColumnIndexOrThrow(_cursor, "facultyName");
          final int _cursorIndexOfClassroomId = CursorUtil.getColumnIndexOrThrow(_cursor, "classroomId");
          final int _cursorIndexOfClassroomName = CursorUtil.getColumnIndexOrThrow(_cursor, "classroomName");
          final int _cursorIndexOfClassroomCapacity = CursorUtil.getColumnIndexOrThrow(_cursor, "classroomCapacity");
          final int _cursorIndexOfConflict = CursorUtil.getColumnIndexOrThrow(_cursor, "conflict");
          final List<TimetableEntryEntity> _result = new ArrayList<TimetableEntryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TimetableEntryEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            final String _tmpDay;
            if (_cursor.isNull(_cursorIndexOfDay)) {
              _tmpDay = null;
            } else {
              _tmpDay = _cursor.getString(_cursorIndexOfDay);
            }
            final int _tmpPeriod;
            _tmpPeriod = _cursor.getInt(_cursorIndexOfPeriod);
            final int _tmpSubjectId;
            _tmpSubjectId = _cursor.getInt(_cursorIndexOfSubjectId);
            final String _tmpSubjectName;
            if (_cursor.isNull(_cursorIndexOfSubjectName)) {
              _tmpSubjectName = null;
            } else {
              _tmpSubjectName = _cursor.getString(_cursorIndexOfSubjectName);
            }
            final String _tmpSubjectTargetClassName;
            if (_cursor.isNull(_cursorIndexOfSubjectTargetClassName)) {
              _tmpSubjectTargetClassName = null;
            } else {
              _tmpSubjectTargetClassName = _cursor.getString(_cursorIndexOfSubjectTargetClassName);
            }
            final int _tmpFacultyId;
            _tmpFacultyId = _cursor.getInt(_cursorIndexOfFacultyId);
            final String _tmpFacultyName;
            if (_cursor.isNull(_cursorIndexOfFacultyName)) {
              _tmpFacultyName = null;
            } else {
              _tmpFacultyName = _cursor.getString(_cursorIndexOfFacultyName);
            }
            final int _tmpClassroomId;
            _tmpClassroomId = _cursor.getInt(_cursorIndexOfClassroomId);
            final String _tmpClassroomName;
            if (_cursor.isNull(_cursorIndexOfClassroomName)) {
              _tmpClassroomName = null;
            } else {
              _tmpClassroomName = _cursor.getString(_cursorIndexOfClassroomName);
            }
            final int _tmpClassroomCapacity;
            _tmpClassroomCapacity = _cursor.getInt(_cursorIndexOfClassroomCapacity);
            final boolean _tmpConflict;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfConflict);
            _tmpConflict = _tmp != 0;
            _item = new TimetableEntryEntity(_tmpId,_tmpOrderIndex,_tmpDay,_tmpPeriod,_tmpSubjectId,_tmpSubjectName,_tmpSubjectTargetClassName,_tmpFacultyId,_tmpFacultyName,_tmpClassroomId,_tmpClassroomName,_tmpClassroomCapacity,_tmpConflict);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
