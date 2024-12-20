package kr.kro.hereinkorea.domain.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFestivalImgEntity is a Querydsl query type for FestivalImgEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFestivalImgEntity extends EntityPathBase<FestivalImgEntity> {

    private static final long serialVersionUID = 1584564373L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFestivalImgEntity festivalImgEntity = new QFestivalImgEntity("festivalImgEntity");

    public final QFestivalEntity festival;

    public final StringPath firstimage = createString("firstimage");

    public final StringPath firstimage2 = createString("firstimage2");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QFestivalImgEntity(String variable) {
        this(FestivalImgEntity.class, forVariable(variable), INITS);
    }

    public QFestivalImgEntity(Path<? extends FestivalImgEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFestivalImgEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFestivalImgEntity(PathMetadata metadata, PathInits inits) {
        this(FestivalImgEntity.class, metadata, inits);
    }

    public QFestivalImgEntity(Class<? extends FestivalImgEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.festival = inits.isInitialized("festival") ? new QFestivalEntity(forProperty("festival")) : null;
    }

}

