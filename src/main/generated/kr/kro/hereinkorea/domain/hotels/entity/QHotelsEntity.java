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

    public final StringPath address = createString("address");

    public final NumberPath<Integer> area_code = createNumber("area_code", Integer.class);

    public final StringPath checkIn = createString("checkIn");

    public final StringPath checkOut = createString("checkOut");

    public final StringPath detail = createString("detail");

    public final BooleanPath hasParking = createBoolean("hasParking");

    public final StringPath homepage = createString("homepage");

    public final NumberPath<Double> hotelMapx = createNumber("hotelMapx", Double.class);

    public final NumberPath<Double> hotelMapy = createNumber("hotelMapy", Double.class);

    public final StringPath hotelTag = createString("hotelTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath tel = createString("tel");

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

