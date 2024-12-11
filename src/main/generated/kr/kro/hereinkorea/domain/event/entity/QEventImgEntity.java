package kr.kro.hereinkorea.domain.event.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventImgEntity is a Querydsl query type for EventImgEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventImgEntity extends EntityPathBase<EventImgEntity> {

    private static final long serialVersionUID = -1843278433L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventImgEntity eventImgEntity = new QEventImgEntity("eventImgEntity");

    public final QEventEntity event;

    public final StringPath firstimage = createString("firstimage");

    public final StringPath firstimage2 = createString("firstimage2");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEventImgEntity(String variable) {
        this(EventImgEntity.class, forVariable(variable), INITS);
    }

    public QEventImgEntity(Path<? extends EventImgEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventImgEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventImgEntity(PathMetadata metadata, PathInits inits) {
        this(EventImgEntity.class, metadata, inits);
    }

    public QEventImgEntity(Class<? extends EventImgEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEventEntity(forProperty("event")) : null;
    }

}

