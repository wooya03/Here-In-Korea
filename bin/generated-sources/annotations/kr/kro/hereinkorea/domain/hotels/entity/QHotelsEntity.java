package kr.kro.hereinkorea.domain.hotels.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHotelsEntity is a Querydsl query type for HotelsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelsEntity extends EntityPathBase<HotelsEntity> {

    private static final long serialVersionUID = -1173797054L;

    public static final QHotelsEntity hotelsEntity = new QHotelsEntity("hotelsEntity");

    public final kr.kro.hereinkorea.global.entity.QBaseEntity _super = new kr.kro.hereinkorea.global.entity.QBaseEntity(this);

    public final StringPath addr1 = createString("addr1");

    public final StringPath addr2 = createString("addr2");

    public final NumberPath<Integer> areacode = createNumber("areacode", Integer.class);

    public final NumberPath<Long> contentid = createNumber("contentid", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Double> mapx = createNumber("mapx", Double.class);

    public final NumberPath<Double> mapy = createNumber("mapy", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath tel = createString("tel");

    public final StringPath title = createString("title");

    public QHotelsEntity(String variable) {
        super(HotelsEntity.class, forVariable(variable));
    }

    public QHotelsEntity(Path<? extends HotelsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHotelsEntity(PathMetadata metadata) {
        super(HotelsEntity.class, metadata);
    }

}

