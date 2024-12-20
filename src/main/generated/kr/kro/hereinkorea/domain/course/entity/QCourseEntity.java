package kr.kro.hereinkorea.domain.course.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourseEntity is a Querydsl query type for CourseEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourseEntity extends EntityPathBase<CourseEntity> {

    private static final long serialVersionUID = -1233681926L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourseEntity courseEntity = new QCourseEntity("courseEntity");

    public final kr.kro.hereinkorea.global.entity.QBaseEntity _super = new kr.kro.hereinkorea.global.entity.QBaseEntity(this);

    public final StringPath courseContent = createString("courseContent");

    public final NumberPath<Long> courseId = createNumber("courseId", Long.class);

    public final StringPath courseImageUrl = createString("courseImageUrl");

    public final NumberPath<Integer> courseLikes = createNumber("courseLikes", Integer.class);

    public final StringPath courseName = createString("courseName");

    public final StringPath courseTag = createString("courseTag");

    public final StringPath courseTitle = createString("courseTitle");

    public final NumberPath<Integer> courseViews = createNumber("courseViews", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final kr.kro.hereinkorea.domain.member.Entity.QMemberEntity memId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public QCourseEntity(String variable) {
        this(CourseEntity.class, forVariable(variable), INITS);
    }

    public QCourseEntity(Path<? extends CourseEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourseEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourseEntity(PathMetadata metadata, PathInits inits) {
        this(CourseEntity.class, metadata, inits);
    }

    public QCourseEntity(Class<? extends CourseEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memId = inits.isInitialized("memId") ? new kr.kro.hereinkorea.domain.member.Entity.QMemberEntity(forProperty("memId")) : null;
    }

}

