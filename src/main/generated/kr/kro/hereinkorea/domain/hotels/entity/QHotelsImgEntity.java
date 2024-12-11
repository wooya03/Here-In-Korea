package kr.kro.hereinkorea.domain.hotels.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotelsImgEntity is a Querydsl query type for HotelsImgEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelsImgEntity extends EntityPathBase<HotelsImgEntity> {

    private static final long serialVersionUID = 964113863L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelsImgEntity hotelsImgEntity = new QHotelsImgEntity("hotelsImgEntity");

    public final StringPath firstimage = createString("firstimage");

    public final StringPath firstimage2 = createString("firstimage2");

    public final QHotelsEntity hotels;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QHotelsImgEntity(String variable) {
        this(HotelsImgEntity.class, forVariable(variable), INITS);
    }

    public QHotelsImgEntity(Path<? extends HotelsImgEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHotelsImgEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHotelsImgEntity(PathMetadata metadata, PathInits inits) {
        this(HotelsImgEntity.class, metadata, inits);
    }

    public QHotelsImgEntity(Class<? extends HotelsImgEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotels = inits.isInitialized("hotels") ? new QHotelsEntity(forProperty("hotels")) : null;
    }

}

