package kr.kro.hereinkorea.domain.course.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCourseImageEntity is a Querydsl query type for CourseImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourseImageEntity extends EntityPathBase<CourseImageEntity> {

    private static final long serialVersionUID = -1440063129L;

    public static final QCourseImageEntity courseImageEntity = new QCourseImageEntity("courseImageEntity");

    public final NumberPath<Long> courseId = createNumber("courseId", Long.class);

    public final StringPath courseImageUrl = createString("courseImageUrl");

    public final NumberPath<Long> courseImgId = createNumber("courseImgId", Long.class);

    public QCourseImageEntity(String variable) {
        super(CourseImageEntity.class, forVariable(variable));
    }

    public QCourseImageEntity(Path<? extends CourseImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCourseImageEntity(PathMetadata metadata) {
        super(CourseImageEntity.class, metadata);
    }

}

