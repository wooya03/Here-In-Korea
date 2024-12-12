package kr.kro.hereinkorea.domain.event.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventEntity is a Querydsl query type for EventEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventEntity extends EntityPathBase<EventEntity> {

    private static final long serialVersionUID = 1634827882L;

    public static final QEventEntity eventEntity = new QEventEntity("eventEntity");

    public final kr.kro.hereinkorea.global.entity.QBaseEntity _super = new kr.kro.hereinkorea.global.entity.QBaseEntity(this);

    public final NumberPath<Integer> areacode = createNumber("areacode", Integer.class);

    public final StringPath arr1 = createString("arr1");

    public final StringPath arr2 = createString("arr2");

    public final NumberPath<Long> contentid = createNumber("contentid", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DateTimePath<java.util.Date> eventenddate = createDateTime("eventenddate", java.util.Date.class);

    public final DateTimePath<java.util.Date> eventstartdate = createDateTime("eventstartdate", java.util.Date.class);

    public final NumberPath<Double> mapx = createNumber("mapx", Double.class);

    public final NumberPath<Double> mapy = createNumber("mapy", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath tel = createString("tel");

    public final StringPath title = createString("title");

    public QEventEntity(String variable) {
        super(EventEntity.class, forVariable(variable));
    }

    public QEventEntity(Path<? extends EventEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventEntity(PathMetadata metadata) {
        super(EventEntity.class, metadata);
    }

}

